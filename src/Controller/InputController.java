package Controller;

import Model.SimulationModel;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InputController implements Initializable {

    @FXML
    public Button testButton;

    @FXML
    private TextField sampleTextField;

    @FXML
    private Slider alphaSlider;
    @FXML
    private Slider betaSlider;
    @FXML
    private Slider initialInfectedSlider;

    @FXML
    private ComboBox modelComboBox;

    private SimulationModel model;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // TODO (add Listeners here).

        // Adding Listener to value property.
        alphaSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                //testTextField.setText("New value: " + newValue);
            }
        });



    }

    // When user click on myButton
    // this method will be called.
    public void testButtonMethod(ActionEvent event)
    {
        /*System.out.println("Button Clicked!");

        String test = "Test";

        // Show in VIEW
        testTextField.setText(test);*/

    }

}
