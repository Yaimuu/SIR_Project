/**
 * BehaviourController : Handles the behaviour of the population
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Controller;

import Model.Person;
import View.PersonView;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.Canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.LinkedList;
import java.util.List;

public class BehaviourController implements Controller
{
    private int numberPeople = SettingsController.defaultPopulation;
    private int initialInfected = SettingsController.defaultPopulationInfected;
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
            double ranPosX = person.getRadius() + Math.random() * (this.canvas.getWidth() - person.getRadius() * 2);
            double ranPosY = person.getRadius() + Math.random() * (this.canvas.getHeight() - person.getRadius() * 2);
            person.setPosition(new Vec2d(ranPosX, ranPosY));
            if(i < this.initialInfected)
            {
                person.setState(Person.State.Infected);
            }
            PersonView pv = new PersonView(person, this.canvas);
            pvs.add(pv);
        }
        return pvs;
    }

    public void confinePeople(boolean active)
    {
        int notAffectedPeople = (int)(this.people.size() * (1 - SettingsController.amountOfConfined));
        for (int i = 0; i < this.people.size(); i++)
        {
            if(active && i <= this.people.size() - notAffectedPeople)
            {
                this.people.get(i).getPerson().setConfined(true);
            }
            if(!active)
            {
                this.people.get(i).getPerson().setConfined(false);
            }
        }
    }

    public void maskPeople(boolean active)
    {
        for (int i = 0; i < this.people.size(); i++)
        {
            if(active)
            {
                this.people.get(i).getPerson().setMasked(true);
            }
            if(!active)
            {
                this.people.get(i).getPerson().setMasked(false);
            }
        }
    }

    public void quarantinePeople(boolean active)
    {
        int notAffectedPeople = (int)(this.people.size() * (1 - SettingsController.amountOfConfined));
        for (int i = 0; i < this.people.size(); i++)
        {
            if(active && i <= this.people.size() - notAffectedPeople)
            {
                this.people.get(i).getPerson().setQuarantained(true);
            }
            if(!active)
            {
                this.people.get(i).getPerson().setQuarantained(false);
            }
        }
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

    public int getInitialInfected() {
        return initialInfected;
    }

    public void setInitialInfected(int initialInfected) {
        this.initialInfected = initialInfected;
    }
}
