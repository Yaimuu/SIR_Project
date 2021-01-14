package Model;

import java.util.Vector;

public class SIR extends SimulationModel
{
    protected double S;
    protected double I;
    protected double R;

    enum State
    {
        Safe,
        Infected,
        Recovered
    }

    public Vector<Double> calculateStep()
    {
        Vector<Double> res = new Vector<Double>();

        // TODO : Calculer la prochaine étape

        return res;
    }



}
