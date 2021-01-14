package View;

import Controller.BehaviourController;
import Model.Person;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;

public class SpatialisationView implements View
{
    private BehaviourController bc;
    private AnimationTimer timer;
    private Timeline timeline;

    private boolean start = false;
    private boolean reset = false;

    public SpatialisationView()
    {
        this.bc = new BehaviourController();
        //this.people = new LinkedList<PersonView>();
    }

    public SpatialisationView(Canvas c)
    {
        this.bc = new BehaviourController(c);
        //this.people = new LinkedList<PersonView>();
    }

    public SpatialisationView(List<PersonView> people, Canvas c)
    {
        this.bc = new BehaviourController(people, c);
    }

    public void Start()
    {
        timer.start();
        timeline.play();
    }

    public void Stop()
    {
        timer.stop();
        timeline.stop();
    }

    public void canvasInitialization()
    {
        this.Draw();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //Canvas canvas;
                GraphicsContext gc = bc.canvas.getGraphicsContext2D();
                gc.setFill(Color.CORNSILK);
                gc.fillRect(0, 0, bc.canvas.getWidth(),  bc.canvas.getHeight());
                gc.setFill(Color.FORESTGREEN);
                Draw();
            }
        };

        DoubleProperty x  = new SimpleDoubleProperty();
        DoubleProperty y  = new SimpleDoubleProperty();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(x, 0),
                        new KeyValue(y, 0)
                ),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(x, bc.canvas.getWidth() - 20),
                        new KeyValue(y, bc.canvas.getHeight() - 20)
                )
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void Draw()
    {
        for (PersonView pv: this.bc.people)
        {
            pv.Draw();
        }
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }
}
