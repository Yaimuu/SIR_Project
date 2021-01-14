package Controller;

import Model.SimulationModel;
import View.SpatialisationView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Button startButton;
    @FXML
    public Button resetButton;

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

    @FXML
    private Panel graphPanel;
    @FXML
    private Panel mapPanel;

    @FXML
    private Canvas canvas;


    private SimulationModel model;
    private SpatialisationView spatialisationView;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.spatialisationView = new SpatialisationView(canvas);
        spatialisationView.canvasInitialization();

        // Adding Listener to value property.
        this.alphaSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                //sampleTextField.setText("New value: " + newValue);
            }
        });

        this.startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(!spatialisationView.isStart())
                {
                    spatialisationView.setStart(true);
                    startButton.textProperty().set("Stop Simulation");
                    spatialisationView.start();
                }
                else
                {
                    spatialisationView.setStart(false);
                    startButton.textProperty().set("Start Simulation");
                    spatialisationView.stop();
                }
            }
        });

        this.resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                spatialisationView.reset();
            }
        });

        //this.sampleTextField.onActionProperty().addListener();
    }

    // When user click on myButton
    // this method will be called.
    public void startButtonMethod(ActionEvent event)
    {
        /*System.out.println("Button Clicked!");

        String test = "Test";

        // Show in VIEW
        testTextField.setText(test);*/

    }

    public void toggleModelisationType()
    {
        if(this.modelComboBox.isPressed())
        {

        }
        else
        {

        }
    }

}
