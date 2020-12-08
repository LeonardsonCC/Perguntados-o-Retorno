package controller;

import dao.sqlite.UserDaoSQLite;
import domain.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class RankingController {

    @FXML
    private TableView<UserRanking> tblRanking;

    @FXML
    private TableColumn<UserRanking, Number> colPosicao;

    @FXML
    private TableColumn<UserRanking, Number> colPontuacao;

    @FXML
    private TableColumn<UserRanking, String> colNome;

    private ArrayList<UserRanking> userRankingList = new ArrayList<>();

    public void initialize() {
        this.buildRankingList();
    }

    @FXML
    void btVoltar() {
        try {
            AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btAtualizar() {
        this.buildRankingList();
    }

    @FXML
    void btExportarHTML() {
        String filePath = "ranking.html";

        try {
            File file = new File(filePath);
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write("<html>");
            fw.write("<head>");
            fw.write("<title>Ranking</title>");
            fw.write("</head>");
            fw.write("<body>");
            fw.write("<table>");
            fw.write("<th>#</td>");
            fw.write("<th>Nome<td>");
            fw.write("<th>Pontuação<td>");
            for (int i=0; i<this.userRankingList.size(); i++) {
                fw.write("<tr>");
                fw.write("<td>"+ i +"</td>");
                fw.write("<td>"+ this.userRankingList.get(i).getUser().getName() +"</td>");
                fw.write("<td>"+ this.userRankingList.get(i).getUser().getScore() +"</td>");
                fw.write("</tr>");
            }
            fw.write("</table>");
            fw.write("</body>");
            fw.write("</html>");
            fw.close();

            ProcessBuilder pb = new ProcessBuilder();
            pb.command("firefox", filePath);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class UserRanking {
        private User user = null;
        private IntegerProperty position = new SimpleIntegerProperty(0);

        public User getUser() {
            return user;
        }

        public UserRanking setUser(User user) {
            this.user = user;
            return this;
        }

        public int getPosition() {
            return position.get();
        }

        public void setPosition(int position) {
            this.position.set(position);
        }

        public IntegerProperty positionProperty() {
            return position;
        }
    }

    private void buildRankingList() {
        colNome.setCellValueFactory(cellData -> cellData.getValue().getUser().nameProperty());
        colPosicao.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        colPontuacao.setCellValueFactory(cellData -> cellData.getValue().getUser().scoreProperty());

        ArrayList<User> userList = new UserDaoSQLite().getAllWithPontuation(10);
        this.userRankingList = new ArrayList<>();
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                UserRanking tmpUserRanking = new UserRanking();
                tmpUserRanking.setUser(userList.get(i));
                tmpUserRanking.setPosition(i + 1);
                userRankingList.add(tmpUserRanking);
            }
            tblRanking.setItems(FXCollections.observableArrayList(userRankingList));
        }
    }
}
