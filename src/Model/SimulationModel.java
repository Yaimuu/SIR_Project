package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public abstract class SimulationModel implements Model
{
    protected int step;
    protected boolean spatialization;
    protected double intialPopInfected;

    protected double alpha;
    protected double beta;
    protected double gamma;
    protected double N;

    protected double tSpan;
    protected List<Vector<Double>> y;

    public SimulationModel()
    {
        this.alpha = 0.25;
        this.beta = 0.1;
        this.gamma = 0.3;
        this.N = 100;
        this.tSpan = 50;
        this.y = new LinkedList<>();
    }

    /**
     *
     * @return Une liste de l'évolution des vecteurs de S,I,R
     */
    public List<Vector<Double>> calculateModel()
    {
        List<Vector<Double>> yModel = new LinkedList<>();

        for (int j = 0; j < this.tSpan; j++)
        {
            yModel.add(this.calculateStep());
        }

        this.y = yModel;
        return yModel;
    }

    public void displayResult()
    {
        for(int i = 0; i < this.y.size(); i++)
        {
            String line = "";
            for (int j = 0; j < this.y.get(i).size(); j++)
            {
                line += this.y.get(i).get(j).toString() + " ";
            }
            System.out.println(line);
        }
    }

    protected abstract Vector<Double> calculateStep();

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

    public List<Vector<Double>> getY() {
        return y;
    }

    public void setY(List<Vector<Double>> y) {
        this.y = y;
    }
}
