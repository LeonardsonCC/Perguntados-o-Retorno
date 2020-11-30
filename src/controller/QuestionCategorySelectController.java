package controller;

import dao.sqlite.CategoryDaoSQLite;
import domain.Category;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.util.ArrayList;

public class QuestionCategorySelectController {

    public static Category selectedCategory = null;
    @FXML
    private TableView<Category> tbCategorias;
    @FXML
    private TableColumn<Category, String> tbColName;
    @FXML
    private TableColumn<Category, Number> tbColQuantity;

    @FXML
    void btPlay() {
        selectedCategory = tbCategorias.getSelectionModel().getSelectedItem();

        try {
            AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Question.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        tbColName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tbColQuantity.setCellValueFactory(cellData -> cellData.getValue().questionsQuantityProperty());

        ArrayList<Category> categoryList = new CategoryDaoSQLite().getAllWithQuantity();
        if (categoryList.size() > 0) {
            tbCategorias.setItems(FXCollections.observableArrayList(categoryList));
        }
    }

}
