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

        // TODO : love sur tes fesses Yanis, dis moi si c'est pas ce que tu voulais

        res.set(0, -super.beta * S * I);
        res.set(1, super.beta*S*I-super.gamma*I);
        res.set(2, super.gamma*I);

        //je sais pas si tu veux qu'on change les SIR directement
        //S = res.get(0);
        //I = res.get(1);
        //R = res.get(2);



        return res;
    }


    @Override
    public List<List<Double>> calculateStep(List<List<Double>> yModel, int i, int j) {
        return null;
    }
}
