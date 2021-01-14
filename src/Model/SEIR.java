package Model;

public class SEIR extends SIR
{
    enum State
    {
        Safe,
        Exposed,
        Infected,
        Recovered
    }
}
