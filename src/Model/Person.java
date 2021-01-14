package Model;

import com.sun.javafx.geom.Vec2d;

public class Person implements Model
{
    private float radius;
    private Vec2d position;
    private Vec2d speed;

    public Person()
    {
        double ranPosX = 0 + Math.random() * (100 - 0);
        double ranPosY = 0 + Math.random() * (100 - 0);
        /*this.setPosition(new Vec2d(10, 10));
        this.setRadius(5);
        this.setSpeed(new Vec2d(1, 1));*/
        this.radius = 5;
        this.position = new Vec2d(ranPosX, ranPosY);
        this.speed = new Vec2d(1, 1);
    }

    public Person(float x, float y)
    {
        this.radius = 5;
        this.position = new Vec2d(x, y);
        this.speed = new Vec2d(1, 1);
    }

    public void OnCollisionEnter()
    {
        this.speed.x *= -1;
        this.speed.y *= -1;
    }

    public void Move()
    {
        this.position.x += this.speed.x;
        this.position.y += this.speed.y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
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

    /*public void ChangeState(State newState)
    {

    }*/

}
