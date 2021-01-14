package Controller;

import Model.Model;
import Model.Person;
import View.PersonView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class BehaviourController implements Controller
{
    public int numberPeople = 10;
    public List<PersonView> people = new LinkedList<PersonView>();
    public Canvas canvas;

    public BehaviourController()
    {
        this.people = this.GeneratePeople(this.numberPeople);
    }

    public BehaviourController(Canvas c)
    {
        this.canvas = c;
        this.people = this.GeneratePeople(this.numberPeople);
    }

    public BehaviourController(int nPeople, Canvas c)
    {
        this.numberPeople = nPeople;
        this.canvas = c;
    }

    public BehaviourController(List<PersonView> newPeople, Canvas c)
    {
        this.people = newPeople;
        this.canvas = c;
    }

    private List<PersonView> GeneratePeople(int nbPeople)
    {
        List<PersonView> pv = new LinkedList<PersonView>();
        List<Person> p = new LinkedList<Person>();

        for (int i = 0; i < nbPeople; i++)
        {
            Person person = new Person();
            pv.add(new PersonView(person, this.canvas));
        }
        return pv;
    }

}
