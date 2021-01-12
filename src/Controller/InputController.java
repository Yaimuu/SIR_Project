package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InputController implements Initializable {

    @FXML
    public Button testButton;

    @FXML
    private TextField testTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }

    // When user click on myButton
    // this method will be called.
    public void testButtonMethod(ActionEvent event) {
        System.out.println("Button Clicked!");

        String test = "Test";

        // Show in VIEW
        testTextField.setText(test);

    }


}
