/**
 * ChartView : Displaying of the simulation on a line chart
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package View;

import Controller.MainController;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.List;
import java.util.Vector;

public class ChartView implements View
{
    public ChartView()
    {}

    public void draw(LineChart<Number,Number> chartSIR)
    {
        MainController.model.calculateModel();
        List<Vector<Double>> res = MainController.model.getEtatPopulation();
        int jours = res.size();

        try
        {
            chartSIR.getData().clear();
            for(int i = 0; i < MainController.model.getModelLabels().size(); i++)
            {
                XYChart.Series series = new XYChart.Series();
                series.setName(MainController.model.getModelLabels().get(i));

                for(int j = 0; j < jours-1; j++)
                {
                    series.getData().add(new XYChart.Data(j+"", res.get(j).get(i)) );
//                    series.getData().add(new XYChart.Data(j+"", j*i ) );
                }
                chartSIR.getData().add(series);
            }
        }
        catch (Exception e)
        {
            System.out.println("Values are not computable");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void draw() {}
}
