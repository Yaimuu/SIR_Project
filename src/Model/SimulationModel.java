package Model;

import java.util.LinkedList;
import java.util.List;

public abstract class SimulationModel implements Model
{
    protected int step;
    protected boolean spatialization;
    protected double N; //population totale
    protected double alpha;
    protected double beta;
    protected double gamma;

    protected double intialPopInfected;
    protected double tSpan;
    protected double equationCount;

    protected List<List<Double>> calculateModel()
    {
        List<List<Double>> yModel = new LinkedList<>();

        for (int i = 0; i < equationCount + 1; i++)
        {
            for (int j = 0; j < this.tSpan; j++)
            {
                yModel = this.calculateStep(yModel, i, j);
            }
        }

        return yModel;
    }

    public abstract List<List<Double>> calculateStep(List<List<Double>> yModel, int i, int j);
}
