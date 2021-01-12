package Controller;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InputController implements Initializable {

    @FXML
    public Button testButton;

    @FXML
    private TextField testTextField;

    @FXML
    private Slider testSlider;

    void InputController()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).
        // Adding Listener to value property.
        testSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {

                testTextField.setText("New value: " + newValue);
            }
        });

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
