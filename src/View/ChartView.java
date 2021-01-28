package View;

import Model.SIR;
import Model.SimulationModel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ChartView
{
    private LineChart<Number,Number> modelChart;
    private List<XYChart.Series> series;

    private String title;

    SimulationModel model = new SIR();


    public void draw(LineChart chartSIR)
    {
        model.calculateModel();
        List<Vector<Double>> res = model.getY();

        //final NumberAxis xAxis = new NumberAxis();
        //final NumberAxis yAxis = new NumberAxis();
        //xAxis.setLabel("Nombre de jours");

        XYChart.Series series = new XYChart.Series();

        int j = 30; //nombre de jours

        for(int i =0; i< j; i++){
            series.getData().add(new XYChart.Data(i, res.get(i).get(0)));
            series.getData().add(new XYChart.Data(i, res.get(i).get(1)));
            series.getData().add(new XYChart.Data(i, res.get(i).get(2)));
        }

        //chartSIR.getData().add(series);
        chartSIR.getData();

    }


}
