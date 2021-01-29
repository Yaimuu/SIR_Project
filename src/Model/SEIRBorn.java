/**
 *
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Model;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class SEIRBorn extends SEIR
{
    private Double eta; //Bornrate
    private Double mu;  //Deathrate

    // TODO : SEIR avec gestion des naissances

    public SEIRBorn()
    {
        super();
        this.eta = 0.1;
        this.mu = 0.075;
    }

    @Override
    protected Vector<Double> calculateStep()
    {
        Vector<Double> res = new Vector<Double>();

        res.add(-super.beta * S * I + eta*super.N-mu*S);
        res.add(super.beta * S * I - super.gamma*E - mu*E);
        res.add(super.gamma*E-super.alpha*I -mu*I);
        res.add(super.alpha*I -mu*R);

        S = res.get(0);
        E = res.get(1);
        I = res.get(2);
        R = res.get(3);

        return res;
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
        else if(p.getState() == Person.State.Infected)
        {
            if(this.mu >= randRatio)
            {
                state = Person.State.Dead;
                p.setState(Person.State.Dead);
            }
        }
        return state;
    }

    public Double getEta() {
        return eta;
    }

    public void setEta(Double eta) {
        this.eta = eta;
    }

    public Double getMu() {
        return mu;
    }

    public void setMu(Double mu) {
        this.mu = mu;
    }
}