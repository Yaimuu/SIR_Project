package Model;

import java.util.List;

public class SEIRBorn extends SEIR
{
    private Double bornRate;

    // TODO : SEIR avec gestion des naissances

    @Override
    public List<List<Double>> calculateStep(List<List<Double>> yModel, int i, int j) {
        return null;
    }
}