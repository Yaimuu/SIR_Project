package Model;

import java.util.Vector;

public class SEIR extends SIR
{
    // TODO : vérification, je ne sais pas trop si on pourrait utiliser les héritages mieux ^^'

    protected double S;
    protected double E;
    protected double I;
    protected double R;

    public Vector<Double> calculateStep()
    {
        Vector<Double> res = new Vector<Double>();

        res.set(0, -super.beta * S * I);
        res.set(1, super.beta * S * I - super.alpha*E);
        res.set(2, super.alpha*E-super.gamma*I);
        res.set(3, super.gamma*I);

        //je sais pas si tu veux qu'on change les SIR directement
        //S = res.get(0);
        //E = res.get(1);
        //I = res.get(2);
        //R = res.get(3);

        return res;
    }
}
