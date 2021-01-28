package Model;

import java.util.List;
import java.util.Vector;

public class SEIRBorn extends SEIR
{
    private Double eta; //Bornrate
    private Double mu;  //Deathrate
    protected double S;
    protected double E;
    protected double I;
    protected double R;

    // TODO : SEIR avec gestion des naissances

    @Override
    public Vector<Double> calculateStep()
    {
        Vector<Double> res = new Vector<Double>();

        res.set(0, -super.beta * S * I + eta*super.N-mu*S);
        res.set(1, super.beta * S * I - super.alpha*E - mu*E);
        res.set(2, super.alpha*E-super.gamma*I -mu*I);
        res.set(3, super.gamma*I -mu*R);

        //je sais pas si tu veux qu'on change les SIR directement
        //S = res.get(0);
        //E = res.get(1);
        //I = res.get(2);
        //R = res.get(3);

        return res;
    }
}