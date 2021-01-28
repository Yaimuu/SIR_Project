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

        this.canvas.getGraphicsContext2D().setFill(personColor);

        this.canvas.getGraphicsContext2D().fillOval(this.person.getPosition().x - this.person.getRadius(), this.person.getPosition().y - this.person.getRadius(), this.person.getRadius()*2, this.person.getRadius()*2);
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
