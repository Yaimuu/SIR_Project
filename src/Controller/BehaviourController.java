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
    private int numberPeople = 10;
    private int initialInfected = 1;
    private List<PersonView> people;
    private Canvas canvas;

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

    public void resetPeople()
    {
        this.people = this.GeneratePeople(this.numberPeople);
    }

    public void resetPeople(int nbPeople)
    {
        this.people = this.GeneratePeople(nbPeople);
    }

    private List<PersonView> GeneratePeople(int nbPeople)
    {
        List<PersonView> pvs = new LinkedList<PersonView>();

        for (int i = 0; i < nbPeople; i++)
        {
            Person person = new Person();
            if(i < this.initialInfected)
            {
                person.setState(Person.State.Infected);
            }
            PersonView pv = new PersonView(person, this.canvas);
            pvs.add(pv);
        }
        return pvs;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }

    public List<PersonView> getPeople() {
        return people;
    }

    public void setPeople(List<PersonView> people) {
        this.people = people;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
