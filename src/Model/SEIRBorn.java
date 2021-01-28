package Model;

import java.util.List;
import java.util.Vector;

public class SEIRBorn extends SEIR
{
    private Double eta; //Bornrate
    private Double mu;  //Deathrate

    // TODO : SEIR avec gestion des naissances

    /**
     * Calcul la prochaine étape de S,I et R
     * @return un vecteur contenant les S,I et R actuels
     */
    @Override
    protected Vector<Double> calculateStep() {
        Vector<Double> res = new Vector<Double>();

        res.add(-super.beta * S * I + eta*super.N-mu*S);
        res.add(super.beta * S * I - super.alpha*E - mu*E);
        res.add(super.alpha*E-super.gamma*I -mu*I);
        res.add(super.gamma*I -mu*R);

        //je sais pas si tu veux qu'on change les SIR directement
        S = res.get(0);
        E = res.get(1);
        I = res.get(2);
        R = res.get(3);

        return res;
    }
}