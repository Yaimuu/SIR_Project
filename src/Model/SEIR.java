/**
 *
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Model;

import java.util.LinkedList;
import java.util.List;

import java.util.Random;
import java.util.Vector;

public class SEIR extends SIR
{
    protected double E;
    // TODO : SEIR

    public SEIR()
    {
        super();
        this.E = 0;
        this.modelLabels = new LinkedList<>();
        if(this.modelLabels.size() < 4)
        {
            this.modelLabels.add("Sains");
            this.modelLabels.add("Exposé");
            this.modelLabels.add("Infectés");
            this.modelLabels.add("Guéris");
        }
        else
        {
            this.modelLabels.set(0, "Sains");
            this.modelLabels.set(1, "Exposé");
            this.modelLabels.set(2, "Infectés");
            this.modelLabels.set(3, "Guéris");
        }
    }

    @Override
    protected Vector<Double> calculateStep() {
        Vector<Double> res = new Vector<Double>();

        res.add(-super.beta * S * I);
        res.add(super.beta * S * I - super.gamma*E);
        res.add(super.gamma*E-super.alpha*I);
        res.add(super.alpha*I);

        S = res.get(0);
        E = res.get(1);
        I = res.get(2);
        R = res.get(3);

        return res;
    }

    @Override
    protected Person.State spreadInfection(Person p1, Person p2)
    {
        Person.State state = p1.getState();
        if(p2.getState() == Person.State.Infected && p1.getState() == Person.State.Safe )
        {
            Random r = new Random();
            double min = 0d;
            double max = 1d;
            double randRatio = min + (max - min) * r.nextDouble();
            if(super.beta >= randRatio)
            {
                state = Person.State.Exposed;
                p1.setState(Person.State.Exposed);
            }
        }
        return state;
    }

    @Override
    protected Person.State updatePersonState(Person p)
    {
        Person.State state = p.getState();

        Random r = new Random();
        double min = 0d;
        double max = 1d;
        double randRatio = min + (max - min) * r.nextDouble();

        if(p.getState() == Person.State.Infected)
        {
            if(super.alpha >= randRatio)
            {
                state = Person.State.Recovered;
                p.setState(Person.State.Recovered);
            }
        }
        else if(p.getState() == Person.State.Exposed)
        {
            if(super.gamma >= randRatio)
            {
                state = Person.State.Infected;
                p.setState(Person.State.Infected);
            }
        }
        return state;
    }

    public double getE() {
        return E;
    }

    public void setE(double e) {
        E = e;
    }
}
