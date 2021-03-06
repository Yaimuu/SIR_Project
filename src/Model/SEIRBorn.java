/**
 *  SEIRBorn : Class processing the SEIR model with deaths and new borns
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

    /**
     *
     */
    public SEIRBorn()
    {
        super();
        this.eta = 0.1;
        this.mu = 0.075;
    }

    /**
     *
     * @return
     */
    @Override
    protected Vector<Double> calculateStep()
    {
        Vector<Double> res = new Vector<Double>();

        res.add(S + (-super.beta * S * I + eta * super.N - mu * S));
        res.add(E + (super.beta * S * I - super.alpha * E - mu * E));
        res.add(I + (super.alpha * E - super.gamma * I -mu * I));
        res.add(R + (super.gamma * I -mu * R));

        S = res.get(0);
        E = res.get(1);
        I = res.get(2);
        R = res.get(3);

        return res;
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    protected Person.State updatePersonState(Person p)
    {
        Person.State state = p.getState();
        Person.State startState = p.getState();

        Random r = new Random();
        double min = 0d;
        double max = 1d;
        double randRatio = min + (max - min) * r.nextDouble();
        double randRatioDead = min + (max - min) * r.nextDouble();
        double infectedTime = Math.abs((double)p.getCurrentTime() - (double)p.getStateChangedTime());

        if(p.getState() == Person.State.Infected)
        {
            if(infectedTime >= super.gamma + randRatio)
            {
                state = Person.State.Recovered;
                p.setState(Person.State.Recovered);
            }
            else if(this.mu/100 >= randRatioDead)
            {
                state = Person.State.Dead;
                p.setState(Person.State.Dead);
            }
        }
        else if(p.getState() == Person.State.Exposed)
        {
            if(infectedTime >= super.alpha + randRatio)
            {
                state = Person.State.Infected;
                p.setState(Person.State.Infected);
            }
        }

        if(startState != state)
        {
            p.setStateChangedTime(p.getCurrentTime());
        }

        return state;
    }

    /**
     *
     * @return
     */
    public Double getEta() {
        return eta;
    }

    /**
     *
     * @param eta
     */
    public void setEta(Double eta) {
        this.eta = eta;
    }

    /**
     *
     * @return
     */
    public Double getMu() {
        return mu;
    }

    /**
     *
     * @param mu
     */
    public void setMu(Double mu) {
        this.mu = mu;
    }
}