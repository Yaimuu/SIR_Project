/**
 * SimulationModel : Abstract class of SIR models
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public abstract class SimulationModel implements Model
{
    protected int step;
    protected boolean spatialization;

    protected double N; //population totale
    protected double alpha;
    protected double beta;
    protected double gamma;

    protected double tSpan;
    protected List<Vector<Double>> etatPopulation;
    protected List<String> modelLabels;

    public SimulationModel()
    {
        this.alpha = 0.02;
        this.beta = 0.005;
        this.gamma = 0.3;
        this.N = 100;
        this.tSpan = 100;
        this.etatPopulation = new LinkedList<>();
        this.modelLabels = new LinkedList<>();
    }

    /**
     * Method used to compute values of the model and store it in a list of vectors
     * @return The result of the simulation in a list of vectors
     */
    public List<Vector<Double>> calculateModel()
    {
        List<Vector<Double>> yModel = new LinkedList<>();
        yModel.add(this.initialValues());
        for (int j = 0; j < this.tSpan; j++)
        {
            yModel.add(this.calculateStep());
        }

        this.etatPopulation = yModel;
        return yModel;
    }

    /**
     * Display the simulation's result in the console
     */
    public void displayResult()
    {
        for(int i = 0; i < this.etatPopulation.size(); i++)
        {
            String line = "";
            for (int j = 0; j < this.etatPopulation.get(i).size(); j++)
            {
                line += this.etatPopulation.get(i).get(j).toString() + " ";
            }
            System.out.println(line);
        }
    }

    protected abstract Vector<Double> calculateStep();
    protected abstract Vector<Double> initialValues();
    protected abstract void resetValues();

    protected abstract Person.State spreadInfection(Person p1, Person p2);
    protected abstract Person.State updatePersonState(Person p);



    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getN() {
        return N;
    }

    public void setN(double n) {
        N = n;
    }

    public double gettSpan() {
        return tSpan;
    }

    public void settSpan(double tSpan) {
        this.tSpan = tSpan;
    }

    public List<Vector<Double>> getEtatPopulation() {
        return etatPopulation;
    }

    public void setEtatPopulation(List<Vector<Double>> etatPopulation) {
        this.etatPopulation = etatPopulation;
    }

    public List<String> getModelLabels() {
        return modelLabels;
    }

    public void setModelLabels(List<String> modelLabels) {
        this.modelLabels = modelLabels;
    }
}
