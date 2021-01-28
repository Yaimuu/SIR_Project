package Model;

import java.util.List;
<<<<<<< HEAD

=======
>>>>>>> 4e68a5ef84c49a62e1c11d487d126eaa4362b4bc
import java.util.Vector;

public class SEIR extends SIR
{
<<<<<<< HEAD

=======
>>>>>>> 4e68a5ef84c49a62e1c11d487d126eaa4362b4bc
    protected double E;
    // TODO : SEIR

    @Override
    protected Vector<Double> calculateStep() {
        Vector<Double> res = new Vector<Double>();

        res.add(-super.beta * S * I);
        res.add(super.beta * S * I - super.alpha*E);
        res.add(super.alpha*E-super.gamma*I);
        res.add(super.gamma*I);

        //je sais pas si tu veux qu'on change les SIR directement
        S = res.get(0);
        E = res.get(1);
        I = res.get(2);
        R = res.get(3);
<<<<<<< HEAD
=======

>>>>>>> 4e68a5ef84c49a62e1c11d487d126eaa4362b4bc

        return res;
    }
}
