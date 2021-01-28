package Model;

import com.sun.javafx.geom.Vec2d;

import com.sun.webkit.Timer;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;

public class Person implements Model
{
    private double radius = 30;
    private Vec2d position;
    private Vec2d speed;
    private double direction;
    private boolean collision = false;

    private enum State
    {
        Safe,
        Infected,
        Recovered
    }

    public Person()
    {
        //this.radius = 10;
        double ranPosX = this.radius + Math.random() * (500 - this.radius);
        double ranPosY = this.radius + Math.random() * (200 - this.radius);
        double ranSpeedX = 1 + Math.random() * (50 - 1);
        double ranSpeedY = 1 + Math.random() * (50 - 1);
        double ranDir = 0 + Math.random() * (360 - 0);

        this.direction = ranDir;
        this.position = new Vec2d(ranPosX, ranPosY);
        //this.speed = new Vec2d((double)(ranSpeedX/100), (double)(ranSpeedY/100));
        this.speed = new Vec2d(0.7d, 0.7d);
    }

    public Person(float x, float y)
    {
        //this.radius = 10;

        double ranDir = 0 + Math.random() * (360 - 0);
        this.direction = ranDir;
        this.position = new Vec2d(x, y);
        this.speed = new Vec2d(1, 1);
    }

    public void OnCollisionEnter()
    {
        this.speed.x *= -1;
        this.speed.y *= -1;
    }

    public void isCollidingPerson(Person p)
    {
        if(p != this)
        {
            double dx = this.getPosition().x - p.getPosition().x;
            double dy = this.getPosition().y - p.getPosition().y;
            //double rSum = this.getRadius() + p.getRadius();

            if( Math.sqrt(dx * dx + dy * dy) < this.getRadius() && !collision)
            {
//                System.out.println("collision");
                this.direction -= 90;
                //this.OnCollisionEnter();
                this.collision = true;
            }
        }
    }

    public void isCollidingBounds(double width, double heigth)
    {
        if( this.getPosition().x >= width || this.getPosition().x - this.getRadius() <= 0 )
        {
            this.direction = 180 - this.direction;
        }

        if( this.getPosition().y >= heigth || this.getPosition().y - this.getRadius() <= 0 )
        {
            this.direction = 360 - this.direction;
        }
    }

    public void move()
    {
        this.position.x += this.speed.x *  Math.cos(this.direction * Math.PI /180);
        this.position.y += this.speed.y * Math.sin(this.direction * Math.PI /180);
    }

    /*public void ChangeState(State newState)
    {

    }*/

    private void resetCollision()
    {

        this.collision = false;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Vec2d getPosition() {
        return position;
    }

    public void setPosition(Vec2d position) {
        this.position = position;
    }

    public Vec2d getSpeed() {
        return speed;
    }
    
    public void setSpeed(Vec2d speed) {
        this.speed = speed;
    }

}
