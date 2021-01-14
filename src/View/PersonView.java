package View;

import Model.Person;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PersonView implements View
{
    private Person person;
    private Circle circle;
    private Canvas canvas;

    public PersonView(Person person)
    {
        this.person = person;
        this.circle = new Circle(this.person.getPosition().x, this.person.getPosition().y, this.person.getRadius());
    }

    public PersonView(Person person, Canvas canvas)
    {
        this.person = person;
        this.canvas = canvas;

        this.circle = new Circle(this.person.getPosition().x, this.person.getPosition().y, this.person.getRadius(), Color.WHITE);
    }

    @Override
    public void Draw()
    {
        this.person.Move();

        this.canvas.getGraphicsContext2D().fillOval(this.person.getPosition().x - this.person.getRadius(), this.person.getPosition().y - this.person.getRadius(), this.person.getRadius(), this.person.getRadius());
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
