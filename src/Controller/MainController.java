package Controller;

import Model.SIR;
import Model.SimulationModel;
import View.SpatialisationView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Button startButton;
    @FXML
    public Button resetButton;

    @FXML
    private ToggleButton toggleSimulation;

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
    private Pane graphPanel;
    @FXML
    private Pane mapPanel;
    @FXML
    private Pane graphInputPanel;
    @FXML
    private Pane mapInputPanel;

    @FXML
    private Canvas canvas;

    public static SimulationModel model = new SIR();
    private SpatialisationView spatialisationView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // TODO : Gestion des inputs

        this.spatialisationView = new SpatialisationView(canvas);
        this.spatialisationView.canvasInitialization();

        this.mapPanel.setVisible(false);
        this.mapInputPanel.setVisible(false);

        // Adding Listener to value property.
        this.alphaSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                System.out.println("New value: " + newValue);
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
    }

    public void updateSimulationModel()
    {
    }

    public void toggleModelType()
    {
        if(this.toggleSimulation.isSelected())
        {
            this.graphPanel.setVisible(false);
            this.graphInputPanel.setVisible(false);

            this.mapPanel.setVisible(true);
            this.mapInputPanel.setVisible(true);

            this.toggleSimulation.setText("Graph Visualization");
        }
        else
        {
            this.graphPanel.setVisible(true);
            this.graphInputPanel.setVisible(true);

            this.mapPanel.setVisible(false);
            this.mapInputPanel.setVisible(false);

            this.spatialisationView.stop();

            this.toggleSimulation.setText("Map Visualization");
        }
    }

}
