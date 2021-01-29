package Model;

import Controller.MainController;
import Controller.SettingsController;
import com.sun.javafx.geom.Vec2d;

import com.sun.webkit.Timer;
import javafx.animation.AnimationTimer;

public class Person implements Model
{

    private double radius = SettingsController.defaultPersonRadius;
    private Vec2d position;
    private Vec2d speed;
    private double direction;
    private State state = State.Safe;

    private boolean masked = true;
    private boolean vaccined = false;
    private boolean confined = false;
    private boolean quarantained = false;

    public enum State
    {
        Safe,
        Exposed,
        Infected,
        Recovered,
        Dead
    }

    public Person()
    {
        double ranPosX = this.radius + Math.random() * (100 - this.radius);
        double ranPosY = this.radius + Math.random() * (100 - this.radius);
        double ranSpeedX = 1 + Math.random() * (50 - 1);
        double ranSpeedY = 1 + Math.random() * (50 - 1);
        double ranDir = 0 + Math.random() * (360 - 0);

        this.direction = ranDir;
        this.position = new Vec2d(ranPosX, ranPosY);
        //this.speed = new Vec2d((double)(ranSpeedX/100), (double)(ranSpeedY/100));
        this.speed = new Vec2d(1d, 1d);
    }

    public Person(float x, float y)
    {
        double ranDir = 0 + Math.random() * (360 - 0);
        this.direction = ranDir;
        this.position = new Vec2d(x, y);
        this.speed = new Vec2d(1, 1);
    }

    /**
     * @param p
     */
    public void isCollidingPerson(Person p)
    {
        if(p != this && p != null && p.state != State.Dead)
        {
            double dx = this.getPosition().x - p.getPosition().x;
            double dy = this.getPosition().y - p.getPosition().y;
            double rSum = this.getRadius() + p.getRadius();

            if( Math.sqrt(dx * dx + dy * dy) < rSum )
            {
                MainController.model.spreadInfection(this, p);
//                this.direction = 90 + this.direction * Math.PI/2;
//                this.direction = this.direction + Math.atan( Math.tan(this.direction) +
//                        2 * Math.sqrt(this.speed.x/p.speed.x ) ) * (Math.sin(p.getDirection()) / Math.cos(this.direction)) ;

//                this.speed.x += Math.sqrt( Math.pow(this.speed.x * Math.sin(this.direction) +
//                        2 * p.speed.x * Math.sin(p.getDirection()) , 2) + Math.pow( this.speed.x + Math.cos(this.direction), 2) );
//                this.speed.y = this.speed.x;
            }
        }
    }

    /**
     *
     * @param width
     * @param heigth
     */
    public void isCollidingBounds(double width, double heigth)
    {
        if( this.getPosition().x + this.getRadius() >= width || this.getPosition().x - this.getRadius() <= 0 )
        {
            this.direction = 180 - this.direction;
        }

        if( this.getPosition().y + this.getRadius() >= heigth || this.getPosition().y - this.getRadius() <= 0 )
        {
            this.direction = 360 - this.direction;
        }
    }

    /**
     *
     */
    public void move()
    {
        if(this.state != State.Dead)
        {
            this.position.x += this.speed.x *  Math.cos(this.direction * Math.PI /180);
            this.position.y += this.speed.y * Math.sin(this.direction * Math.PI /180);
            MainController.model.updatePersonState(this);
        }
    }

    /**
     *
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     *
     * @return
     */
    public Vec2d getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Vec2d position) {
        this.position = position;
    }

    /**
     *
     * @return
     */
    public Vec2d getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(Vec2d speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public double getDirection() {
        return direction;
    }

    /**
     *
     * @param direction
     */
    public void setDirection(double direction) {
        this.direction = direction;
    }

    public boolean isMasked() {
        return masked;
    }

    public void setMasked(boolean masked) {
        this.masked = masked;
    }

    public boolean isVaccined() {
        return vaccined;
    }

    public void setVaccined(boolean vaccined) {
        this.vaccined = vaccined;
    }

    public boolean isConfined() {
        return confined;
    }

    public void setConfined(boolean confined) {
        this.confined = confined;
    }

    public boolean isQuarantained() {
        return quarantained;
    }

    public void setQuarantained(boolean quarantained) {
        this.quarantained = quarantained;
    }
}
