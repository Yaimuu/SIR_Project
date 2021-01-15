package Model;

import java.util.List;
import java.util.Vector;

public class SIR extends SimulationModel
{
    protected double S;
    protected double I;
    protected double R;



    public Vector<Double> calculateStep()
    {
        Vector<Double> res = new Vector<Double>();

        // TODO : Calculer la prochaine étape

        return res;
    }


    @Override
    public List<List<Double>> calculateStep(List<List<Double>> yModel, int i, int j) {
        return null;
    }
}
