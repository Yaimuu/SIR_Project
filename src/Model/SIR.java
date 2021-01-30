/**
 *
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Model;

import Controller.SettingsController;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class SIR extends SimulationModel
{
    protected double S;
    protected double I;
    protected double R;
    protected double I0;
    protected double R0;

    public SIR()
    {
        super();
        this.I0 = 1;
        this.R0 = 0;
        this.resetValues();

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

    /**
     *
     * @return
     */
    @Override
    protected Vector<Double> calculateStep()
    {
        // TODO : Calculer la prochaine étape
        Vector<Double> res = new Vector<Double>();
        res.add(S + (-super.beta * S * I));
        res.add(I + (super.beta * S * I - super.gamma * I));
        res.add(R + (super.gamma * I));

        S = res.get(0);
        I = res.get(1);
        R = res.get(2);

        return res;
    }

    /**
     *
     * @return
     */
    @Override
    protected Vector<Double> initialValues() {
        this.resetValues();
        Vector<Double> res = new Vector<Double>();
        res.add(S);
        res.add(I);
        res.add(R);
        return res;
    }

    /**
     *
     */
    @Override
    protected void resetValues()
    {
        this.I = this.I0;
        this.R = this.R0;
        this.S = super.N - (this.I + this.R);
    }

    /**
     *
     * @param p1
     * @param p2
     * @return
     */
    @Override
    protected Person.State spreadInfection(Person p1, Person p2)
    {
        Person.State state = p1.getState();
        if(p2.getState() == Person.State.Infected  && p1.getState() == Person.State.Safe && !p1.isQuarantained())
        {
            Random r = new Random();
            double min = 0d;
            double max = 1d;
            double randRatio = min + (max - min) * r.nextDouble();
            double contamination = super.beta;
            if(p1.isMasked())
                contamination = contamination/10;

            if(contamination >= randRatio)
            {
                state = Person.State.Infected;
                p1.setState(Person.State.Infected);
                p1.setStateChangedTime(p1.getCurrentTime());
            }
        }
        return state;
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
        if(p.getState() == Person.State.Infected)
        {
            Random r = new Random();
            double min = -50d;
            double max = 50d;
            double randRatio = min + (max - min) * r.nextDouble();
            double infectedTime = Math.abs((double)p.getCurrentTime() - (double)p.getStateChangedTime());

            if(infectedTime >= super.gamma + randRatio)
            {
                state = Person.State.Recovered;
                p.setState(Person.State.Recovered);
                p.setStateChangedTime(p.getCurrentTime());
            }
        }
        return state;
    }

    /**
     *
     * @return
     */
    public double getS() {
        return S;
    }

    /**
     *
     * @return
     */
    public double getI() {
        return I;
    }

    /**
     *
     * @param i
     */
    public void setI(double i) {
        I = i;
        this.S = super.N - (this.I + this.R);
    }

    public double getR() {
        return R;
    }

    /**
     *
     * @param r
     */
    public void setR(double r) {
        R = r;
        this.S = super.N - (this.I + this.R);
    }

    /**
     *
     * @return
     */
    public double getI0() {
        return I0;
    }

    /**
     *
     * @param i0
     */
    public void setI0(double i0) {
        I0 = i0;
    }
}
