

package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class SIR extends SimulationModel
{
    protected double S;
    protected double I;
    protected double R;

    public SIR()
    {
        super();
        this.I = 1;
        this.R = 0;
        this.S = super.N - (this.I + this.R);
        this.modelLabels = new LinkedList<>();
        if(this.modelLabels.size() < 3)
        {
            this.modelLabels.add("Sains");
            this.modelLabels.add("Infectés");
            this.modelLabels.add("Guéris");
        }
        else
        {
            this.modelLabels.set(0, "Sains");
            this.modelLabels.set(1, "Infectés");
            this.modelLabels.set(2, "Guéris");
        }
    }

    @Override
    protected Vector<Double> calculateStep()
    {
        // TODO : Calculer la prochaine étape
        Vector<Double> res = new Vector<Double>();
        res.add(S + (-super.beta * S * I));
        res.add(I + (super.beta*S*I-super.gamma*I));
        res.add(R + (super.gamma*I));

        S = res.get(0);
        I = res.get(1);
        R = res.get(2);

        return res;
    }

    @Override
    protected Person.State spreadInfection(Person p1, Person p2)
    {
        Person.State state = p1.getState();
        if(p2.getState() == Person.State.Infected  && p1.getState() == Person.State.Safe)
        {
            Random r = new Random();
            double min = 0d;
            double max = 1d;
            double randRatio = min + (max - min) * r.nextDouble();
            if(super.beta >= randRatio)
            {
                state = Person.State.Infected;
                p1.setState(Person.State.Infected);
            }
        }
        return state;
    }

    @Override
    protected Person.State updatePersonState(Person p)
    {
        Person.State state = p.getState();
        if(p.getState() == Person.State.Infected)
        {
            Random r = new Random();
            double min = 0d;
            double max = 1d;
            double randRatio = min + (max - min) * r.nextDouble();
            if(super.alpha >= randRatio)
            {
                state = Person.State.Recovered;
                p.setState(Person.State.Recovered);
            }
        }
        return state;
    }
}
