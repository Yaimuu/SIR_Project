/**
 *
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Controller;

import Model.SEIR;
import Model.SEIRBorn;
import Model.SIR;
import Model.SimulationModel;
import View.ChartView;
import View.Main;
import View.SpatialisationView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    /**
     * Button
     */
    @FXML
    public Button startButton;
    @FXML
    public Button resetButton;

    /**
     * Toggles
     */
    @FXML
    private ToggleButton toggleSimulation;

    /**
     * Sliders
     */
    @FXML
    private Slider alphaSlider;
    @FXML
    private Slider betaSlider;
    @FXML
    private Slider gammaSlider;
    @FXML
    private Slider bornSlider;
    @FXML
    private Slider deathSlider;
    @FXML
    private Slider infectedSlider;
    @FXML
    private Slider exposedSlider;

    /**
     * TextFields
     */
    @FXML
    private TextField alphaTextField;
    @FXML
    private TextField betaTextField;
    @FXML
    private TextField gammaTextField;
    @FXML
    private TextField bornTextField;
    @FXML
    private TextField deathTextField;
    @FXML
    private TextField infectedTextField;
    @FXML
    private TextField exposedTextField;

    /**
     * ComboBox
     */
    @FXML
    private ComboBox modelComboBox;

    /**
     * Panes
     */
    @FXML
    private Pane graphPanel;
    @FXML
    private Pane mapPanel;
    @FXML
    private Pane graphInputPanel;
    @FXML
    private Pane mapInputPanel;

    /**
     * Canvas
     */
    @FXML
    private Canvas canvas;

    /**
     * Line Chart
     */
    @FXML
    public LineChart<Number, Number> chartSIR;

    /**
     * Model
     */
    public static SimulationModel model = new SIR();
    protected List<String> modelStrings = new LinkedList<>();

    /**
     * Views
     */
    private SpatialisationView spatialisationView;
    private ChartView chartView;

    /**
     * Initialisation of the project
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        /**
         * Inputs initialisation
         */

        this.spatialisationView = new SpatialisationView(canvas);
        this.spatialisationView.canvasInitialization();

        this.mapPanel.setVisible(false);
        this.mapInputPanel.setVisible(false);

        this.chartView = new ChartView();
        this.chartView.draw(this.chartSIR);

        this.modelStrings.add("Modèle SIR");
        this.modelStrings.add("Modèle SEIR");
        this.modelStrings.add("Modèle SEIR avec naissance");
        ObservableList<String> data = FXCollections.observableArrayList(this.modelStrings.get(0), this.modelStrings.get(1), this.modelStrings.get(2));
        this.modelComboBox.setItems(data);

        this.modelComboBox.getSelectionModel().select(0);

        // TODO : Sliders
        try
        {
            this.alphaSlider.setValue(MainController.model.getAlpha());
            this.alphaTextField.setText(MainController.model.getAlpha()+"");

            this.betaSlider.setValue(MainController.model.getBeta());
            this.betaTextField.setText(MainController.model.getBeta()+"");

            this.gammaSlider.setValue(MainController.model.getGamma());
            this.gammaTextField.setText(MainController.model.getGamma()+"");

            this.infectedSlider.setValue( ((SIR)MainController.model).getI() );
            this.infectedTextField.setText( ((SIR)MainController.model).getI() + "" );

            this.exposedSlider.setValue( ((SEIR)MainController.model).getE() );
            this.exposedTextField.setText( ((SEIR)MainController.model).getE() + "");

            this.bornSlider.setValue( ((SEIRBorn)MainController.model).getEta() );
            this.bornTextField.setText( ((SEIRBorn)MainController.model).getEta() + "");

            this.deathSlider.setValue( ((SEIRBorn)MainController.model).getMu() );
            this.deathTextField.setText( ((SEIRBorn)MainController.model).getMu() + "");
        }
        catch (Exception e) {
            // An exception is launched because the model is setup as an SIR model, we do not need to process the exception
        }

        /**
         * -------------- Event Listeners
         */

            /**
             * Slider Listeners
             */
        this.alphaSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                MainController.model.setAlpha((double)newValue);
                alphaTextField.setText(newValue.toString());
                update();
            }
        });

        this.betaSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                MainController.model.setBeta((double)newValue);
                betaTextField.setText(newValue.toString());
                update();
            }
        });

        this.gammaSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                MainController.model.setGamma((double)newValue);
                gammaTextField.setText(newValue.toString());
                update();
            }
        });

        this.infectedSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                ((SIR)MainController.model).setI((double)newValue);
                infectedTextField.setText(newValue.toString());
                update();
            }
        });

        this.exposedSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                ((SEIR)((SIR)MainController.model)).setE((double)newValue);
                exposedTextField.setText(newValue.toString());
                update();
            }
        });

        this.bornSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                ((SEIRBorn)MainController.model).setEta((double)newValue);
                bornTextField.setText(newValue.toString());
                update();
            }
        });

        this.deathSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                ((SEIRBorn)MainController.model).setMu((double)newValue);
                deathTextField.setText(newValue.toString());
                update();
            }
        });

            /**
             *  Button Listeners
             */
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

    /**
     * Updates the simulation model
     */
    public void updateSimulationModel()
    {
        int modelChoosed = this.modelComboBox.getSelectionModel().getSelectedIndex();
        switch (modelChoosed)
        {
            case 0:
                MainController.model = new SIR();
                break;
            case 1:
                MainController.model = new SEIR();
                break;
            case 2:
                MainController.model = new SEIRBorn();
                break;
        }
        this.chartView.draw(this.chartSIR);
        this.spatialisationView.reset();
    }

    /**
     * Toggles from the chart's view to the spatialization view
     */
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

    /**
     * Updates project
     */
    private void update()
    {
        if(this.toggleSimulation.isSelected())
        {
            this.spatialisationView.reset();
        }
        else
        {
            MainController.model.calculateModel();
            this.chartView.draw(this.chartSIR);
        }
    }
}
