/**
 * MainController : Handles Main.fxml
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Controller;

import Model.SEIR;
import Model.SEIRBorn;
import Model.SIR;
import Model.SimulationModel;
import View.ChartView;
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

import javax.imageio.IIOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable, Controller {

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

    @FXML
    private ToggleButton maskToggle;

    @FXML
    private ToggleButton confinementToggle;

    @FXML
    private ToggleButton quarantineToggle;

    @FXML
    private ToggleButton vaccineToggle;


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
    @FXML
    private Slider populationSlider;

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
    @FXML
    private TextField populationTextField;

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
    private Pane publicPoliciesPanel;
    @FXML
    private Pane mapInputPanel;

    /**
     * Canvas
     */
    @FXML
    private Canvas canvas;

    /**
     * Labels
     */
    @FXML
    private Label timeLabel;

    /**
     * Line Chart
     */
    @FXML
    public LineChart<Number, Number> chartSIR;

    /**
     * Model
     */
    public static SimulationModel model;
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
        this.spatialisationView = new SpatialisationView(this.canvas);
        this.chartView = new ChartView();

        this.modelStrings.add("Modèle SIR");
        this.modelStrings.add("Modèle SEIR");
        this.modelStrings.add("Modèle SEIR avec naissance");
        ObservableList<String> data = FXCollections.observableArrayList(this.modelStrings.get(0), this.modelStrings.get(1), this.modelStrings.get(2));
        this.modelComboBox.setItems(data);
        this.modelComboBox.getSelectionModel().select(0);

        this.updateSimulationModel();

        this.mapPanel.setVisible(false);
        this.mapInputPanel.setVisible(false);
        this.publicPoliciesPanel.setVisible(false);

        this.spatialisationView.canvasInitialization();
        this.spatialisationView.setTimeLabel(this.timeLabel);
        this.chartView.draw(this.chartSIR);

        /**
         * Setup Sliders
         */
        try
        {
            this.populationSlider.setValue( SettingsController.defaultPopulation );
            this.populationTextField.setText( SettingsController.defaultPopulation + "");

            this.alphaSlider.setValue( MainController.model.getAlpha() );
            this.alphaTextField.setText( MainController.model.getAlpha()+"" );

            this.betaSlider.setValue(MainController.model.getBeta());
            this.betaTextField.setText(MainController.model.getBeta()+"");

            this.gammaSlider.setValue(MainController.model.getGamma());
            this.gammaTextField.setText(MainController.model.getGamma()+"");

            this.infectedSlider.setValue( ((SIR)MainController.model).getI0() );
            this.infectedTextField.setText( ((SIR)MainController.model).getI0() + "" );

            this.exposedSlider.setValue( ((SEIR)MainController.model).getE() );
            this.exposedTextField.setText( ((SEIR)MainController.model).getE() + "");

            this.bornSlider.setValue( ((SEIRBorn)MainController.model).getEta() );
            this.bornTextField.setText( ((SEIRBorn)MainController.model).getEta() + "");

            this.deathSlider.setValue( ((SEIRBorn)MainController.model).getMu() );
            this.deathTextField.setText( ((SEIRBorn)MainController.model).getMu() + "");
        }
        catch (Exception e) {
            // An exception is launched because the model is setup as an SIR model, we do not need to process the exception
            System.out.println(e.getMessage());
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
                ((SIR)MainController.model).setI0((double)newValue);
                infectedTextField.setText(newValue.toString());
                spatialisationView.setPopulationInfected( (int)((double)newValue) );
                update();
            }
        });

        this.exposedSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                ((SEIR)((SIR)MainController.model)).setE0((double)newValue);
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

        this.populationSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                spatialisationView.setPopulationSize( (int)((double)newValue) );
                populationTextField.setText(newValue.toString());
                infectedSlider.setMax(populationSlider.getValue());
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
                this.alphaSlider.setDisable(true);
                this.exposedSlider.setDisable(true);

                this.bornSlider.setDisable(true);
                this.deathSlider.setDisable(true);
                break;
            case 1:
                MainController.model = new SEIR();
                this.alphaSlider.setDisable(false);
                this.exposedSlider.setDisable(false);

                this.bornSlider.setDisable(true);
                this.deathSlider.setDisable(true);
                break;
            case 2:
                MainController.model = new SEIRBorn();
                this.alphaSlider.setDisable(false);
                this.exposedSlider.setDisable(false);
                this.bornSlider.setDisable(false);
                this.deathSlider.setDisable(false);
                break;
        }
        if(this.toggleSimulation.isSelected())
        {
            this.exposedSlider.setDisable(true);
            this.bornSlider.setDisable(true);
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
            this.publicPoliciesPanel.setVisible(true);

            this.mapPanel.setVisible(true);
            this.mapInputPanel.setVisible(true);

            // Beta slider
            this.betaSlider.setMin(SettingsController.minBetaMap);
            this.betaSlider.setMax(SettingsController.maxBetaMap);

            // Infected slider
            this.infectedSlider.setMax(this.populationSlider.getValue());
            this.infectedSlider.setBlockIncrement(1);

            // Gamma slider
            this.gammaSlider.setMin(SettingsController.minRecoveryTimeMap);
            this.gammaSlider.setMax(SettingsController.maxRecoveryTimeMap);
            this.gammaSlider.setValue(SettingsController.defaultRecoveryTimeMap);

            // Alpha slider
            this.alphaSlider.setMin(SettingsController.minAlphaMap);
            this.alphaSlider.setMax(SettingsController.maxAlphaMap);
            this.alphaSlider.setValue(SettingsController.defaultAlphaMap);

            this.toggleSimulation.setText("Graph Visualization");
        }
        else
        {
            this.graphPanel.setVisible(true);
            this.publicPoliciesPanel.setVisible(false);

            this.mapPanel.setVisible(false);
            this.mapInputPanel.setVisible(false);

            // Beta slider
            this.betaSlider.setMin(SettingsController.minBetaGraph);
            this.betaSlider.setMax(SettingsController.maxBetaGraph);

            // Infected slider
            this.infectedSlider.setMax(100);
            this.infectedSlider.setBlockIncrement(0.1);

            // Gamma slider
            this.gammaSlider.setMin(SettingsController.minRecoveryTimeGraph);
            this.gammaSlider.setMax(SettingsController.maxRecoveryTimeGraph);
            this.gammaSlider.setValue(SettingsController.defaultRecoveryTimeGraph);

            // Alpha slider
            this.alphaSlider.setMin(SettingsController.minAlphaGraph);
            this.alphaSlider.setMax(SettingsController.maxAlphaGraph);
            this.alphaSlider.setValue(SettingsController.defaultAlphaGraph);

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

    /**
     * Exports datas to csv file
     * @throws IIOException
     */
    public void exportCsv() throws IIOException
    {
        try {
            MainController.model.exportCsv();
        }
        catch (Exception e) {}
    }

    public void togglePolicies()
    {
        this.spatialisationView.setConfinementPolicie(this.confinementToggle.isSelected());
        this.spatialisationView.setVaccinationPolicie(this.vaccineToggle.isSelected());
        this.spatialisationView.setMaskPolicie(this.maskToggle.isSelected());
        this.spatialisationView.setQuarantinePolicie(this.quarantineToggle.isSelected());
    }
}
