package Main.controller.Forms;

import Main.Main;
import Main.model.City;
import Main.model.Entry;
import Main.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPersonForm implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField familyTXT;

    @FXML
    private TextField birthYearTXT;

    @FXML
    private ChoiceBox<City.Job> jobCBOX;

    @FXML
    private ChoiceBox<String> genderCBOX;

    @FXML
    void clearAction(ActionEvent event) {
        nameTXT.setText("");
        familyTXT.setText("");
        birthYearTXT.setText("");
        errorLBL.setText("");
        jobCBOX.getSelectionModel().select(0);
        genderCBOX.getSelectionModel().select(0);
    }

    @FXML
    void doneAction() {
        try {
            Main.getCityPage().addPerson(new Person(nameTXT.getText(), familyTXT.getText(), Entry.getCity().getName(),
                    Integer.parseInt(birthYearTXT.getText()), jobCBOX.getValue(), genderCBOX.getValue()));
            errorLBL.setText("");
        } catch (NumberFormatException ex) {
            errorLBL.setText("Invalid Parameters");
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @FXML
    void limitTextField(KeyEvent event) {
        int maxLength = 12;
        TextField textField = (TextField) event.getSource();
        if (textField.getText().length() > maxLength)
            textField.deletePreviousChar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLBL.setFocusTraversable(true);
        errorLBL.setText("");

        jobCBOX.getItems().addAll(City.Job.values());
        jobCBOX.getSelectionModel().select(0);
        genderCBOX.getItems().addAll("Male", "Female");
        genderCBOX.getSelectionModel().select(0);
    }

}
