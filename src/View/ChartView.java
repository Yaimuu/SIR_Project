/**
 *
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package View;

import Controller.MainController;
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
    public ChartView()
    {}

    public void draw(LineChart<Number,Number> chartSIR)
    {
        MainController.model.calculateModel();
        List<Vector<Double>> res = MainController.model.getY();
        int jours = res.size();

        try
        {
            chartSIR.getData().clear();
            for(int i = 0; i < MainController.model.getModelLabels().size(); i++)
            {
                XYChart.Series series = new XYChart.Series();
                series.setName(MainController.model.getModelLabels().get(i));

                for(int j =0; j < jours; j++)
                {
                    series.getData().add(new XYChart.Data(j+"", res.get(i).get(j)) );
//                    series.getData().add(new XYChart.Data(j+"", j*i ) );
                }
                chartSIR.getData().add(series);
            }
        }
        catch (Exception e)
        {
            System.out.println("Values are not computable");
        }

    }


}
