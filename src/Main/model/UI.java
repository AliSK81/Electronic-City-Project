package Main.model;

import Main.Main;
import Main.model.Exceptions.Alerts;
import Main.model.Exceptions.WrongAmount;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UI {

    public static <T> void setTable(Pane pane, TableView<T> table, List<T> items) {
        if (items != null)
            table.getItems().addAll(items);
        pane.getChildren().add(0, table);
    }

    public static <T> void setTable(Tab tab, TableView<T> table, List<T> items) {
        setTable((Pane) tab.getContent(), table, items);
    }

    public static <T> void setTable(TableView<T> table, List<T> items) {
        if (items != null) {
            table.getItems().clear();
            table.getItems().addAll(items);
        }
    }

    public static <T> void setLabel(Label lbl, T value) {
        lbl.setText(value.toString());
    }

    public static <T> void setText(TextField txt, T value) {
        txt.setText(value.toString());
    }


    public static void addToLabel(Label lbl) {
        int value = Integer.parseInt(lbl.getText());
        lbl.setText(String.valueOf(value + 1));
    }

    public static void subFromLabel(Label lbl) {
        int value = Integer.parseInt(lbl.getText());
        lbl.setText(String.valueOf(value - 1));
    }

    public static <T> void addToTable(TableView<T> table, T item) {
        table.getItems().add(item);
        Alerts.showInfo(item + " added");
    }

    public static <T> void addToTable(TableView<T> table, List<T> items) {
        table.getItems().addAll(items);
        Alerts.showInfo(items + " added");
    }

    public static <T> void removeFromTable(TableView<T> table, T item) {
        table.getItems().remove(item);
        Alerts.showInfo(item + " removed");
    }

    public static <T> void removeFromTable(TableView<T> table, List<T> items) {
        table.getItems().removeAll(items);
    }

    public static <T> T getSelectedItem(TableView<T> table) {
        T item = table.getSelectionModel().getSelectedItem();
        if (item == null)
            throw new WrongAmount("No item is selected");
        return item;
    }

    public static <T> List<T> getSelectedItems(TableView<T> table) {
        ObservableList<T> items = table.getSelectionModel().getSelectedItems();
        if (items.isEmpty())
            throw new WrongAmount("No item is selected");
        return items;
    }

    public static void setSpinner(Spinner<Integer> spinner, int min, int max, int init, int step) {
        spinner.setValueFactory(new IntegerSpinnerValueFactory(min, max, init, step));
    }

    public static void setSpinner(Spinner<Double> spinner, double min, double max, double init, double step) {
        spinner.setValueFactory(new DoubleSpinnerValueFactory(min, max, init, step));
    }

    public static <T> void addToCBOX(ComboBox<T> comboBox, List<T> items) {
        T item = getSelectedItem(comboBox);
        clearCBOX(comboBox);
        comboBox.getItems().addAll(items);
        if (item != null)
            comboBox.getSelectionModel().select(item);
    }

    public static <T> void clearCBOX(ComboBox<T> comboBox) {
        comboBox.getItems().clear();
    }

    public static <T> T getSelectedItem(ComboBox<T> comboBox) {
        return comboBox.getSelectionModel().getSelectedItem();
    }

    private static <T> T loadFXML(String URL) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/" + URL));
        try {
            loader.load();
        } catch (IOException ex) {
//            Alerts.showError("Error in loading data");
            ex.printStackTrace();
            System.exit(1);
        }
        return loader.getRoot();
    }

    public static <T> T loadTable(String name) {
        return loadFXML("Tables/" + name);
    }

    public static <T> T loadPage(String name) {
        return loadFXML("Pages/" + name);
    }

    public static void showForm(String name) {
        Stage stage = new Stage();
        stage.setScene(new Scene(loadFXML("Forms/" + name)));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        stage.show();
    }

    public void select(RadioButton radioButton) {
        radioButton.setSelected(true);
    }

    public void deselect(RadioButton radioButton) {
        radioButton.setSelected(true);
    }

    public void enable(Button button) {
        button.setDisable(false);
    }

    public void disable(Button button) {
        button.setDisable(true);
    }

    public Stage showPage(String name) {
        Stage stage = new Stage();
        stage.setScene(new Scene(loadPage(name)));
        stage.show();
        return stage;
    }
}
