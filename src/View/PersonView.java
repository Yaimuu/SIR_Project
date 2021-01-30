/**
 *  PersonView : Class rendering each person individually
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package View;

import Model.Person;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PersonView implements View
{
    private Person person;
    private Canvas canvas;

    public PersonView(Person person)
    {
        this.person = person;
    }

    public PersonView(Person person, Canvas canvas)
    {
        this.person = person;
        this.canvas = canvas;
    }

    @Override
    public void draw()
    {
        this.person.move();

        Paint personColor = Color.WHITE;

        switch (this.person.getState())
        {
            case Exposed:
                personColor = Color.BLUE;
                break;
            case Infected:
                personColor = Color.RED;
                break;
            case Recovered:
                personColor = Color.GREEN;
                break;
            case Dead:
                personColor = Color.BLACK;
                break;
        }

        if(this.person.isVaccined())
            personColor = Color.CYAN;

        this.canvas.getGraphicsContext2D().setFill(personColor);
        this.canvas.getGraphicsContext2D().fillOval(this.person.getPosition().x - this.person.getRadius(),
                this.person.getPosition().y - this.person.getRadius(),
                this.person.getRadius()*2, this.person.getRadius()*2);

        if(this.person.isMasked())
        {
            this.canvas.getGraphicsContext2D().setFill(Color.CYAN);
            double dist = Math.sqrt(Math.pow(this.person.getPosition().x - (this.person.getPosition().x - (this.person.getRadius()/2)) , 2) + Math.pow(this.person.getPosition().y - (this.person.getPosition().y - (this.person.getRadius()/2)), 2));
            this.canvas.getGraphicsContext2D().fillRect(this.person.getPosition().x + (this.person.getRadius()/2),
                    this.person.getPosition().y + (this.person.getRadius()/2),
                    this.person.getRadius()/2, this.person.getRadius()/2);
        }

        if(this.person.isConfined() || this.person.isQuarantained())
        {
            this.canvas.getGraphicsContext2D().setFill(Color.BLACK);
            this.canvas.getGraphicsContext2D().strokeOval(this.person.getPosition().x - this.person.getRadius()*1.5,
                    this.person.getPosition().y - this.person.getRadius()*1.5,
                    this.person.getRadius()*3, this.person.getRadius()*3);
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
