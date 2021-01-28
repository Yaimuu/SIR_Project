package Model;

import java.util.List;
import java.util.Vector;

public class SIR extends SimulationModel
{
    protected double S;
    protected double I;
    protected double R;

    public SIR()
    {
        this.I = 1;
        this.R = 0;
        this.S = super.N - (this.I + this.R);
    }

    @Override
    protected Vector<Double> calculateStep()
    {
<<<<<<< HEAD
        // TODO : Calculer la prochaine étape
        Vector<Double> res = new Vector<Double>();
        res.add(S + (-super.beta * S * I));
        res.add(I + (super.beta*S*I-super.gamma*I));
        res.add(R + (super.gamma*I));

        //je sais pas si tu veux qu'on change les SIR directement
        S = res.get(0);
        I = res.get(1);
        R = res.get(2);

        return res;
=======

        Vector<Double> res = new Vector<Double>();

        // TODO : love sur tes fesses Yanis, dis moi si c'est pas ce que tu voulais

        res.set(0, -super.beta * S * I);
        res.set(1, super.beta*S*I-super.gamma*I);
        res.set(2, super.gamma*I);

        //je sais pas si tu veux qu'on change les SIR directement
        //S = res.get(0);
        //I = res.get(1);
        //R = res.get(2);
        return null;
>>>>>>> 5198610d9cd7a523677728fc5f5370ed7ffca15b
    }
}
