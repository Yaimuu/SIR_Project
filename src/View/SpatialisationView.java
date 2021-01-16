package View;

import Controller.BehaviourController;
import Model.Person;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    }

    public SpatialisationView(Canvas c)
    {
        this.bc = new BehaviourController(c);
    }

    public SpatialisationView(List<PersonView> people, Canvas c)
    {
        this.bc = new BehaviourController(people, c);
    }

    public void start()
    {
        timer.start();
    }

    public void stop()
    {
        timer.stop();
    }

    public void reset()
    {
        this.bc.resetPeople();
        this.draw();
    }

    public void canvasInitialization()
    {
        this.draw();

        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                updateCanvas();
            }
        };

        /*for(int i = 0; i < this.bc.people.stream().count(); i++)
        {
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
        }*/
    }

    public void updateCanvas()
    {
        this.draw();

        PersonView lastPerson = this.bc.getPeople().get(0);
        for (int i = 0; i < this.bc.getPeople().size(); i++)
        {
            PersonView pv = this.bc.getPeople().get(i);

            pv.getPerson().isCollidingBounds(this.bc.getCanvas().getWidth(), this.bc.getCanvas().getHeight());
            for(int j = 0; j < this.bc.getPeople().size(); j++)
            {
                lastPerson = this.bc.getPeople().get(j);

                if(lastPerson != null && lastPerson != pv)
                {
                    pv.getPerson().isCollidingPerson(lastPerson.getPerson());
                }
            }
        }
    }

    @Override
    public void draw()
    {
        GraphicsContext gc = this.bc.getCanvas().getGraphicsContext2D();
        gc.setFill(Color.rgb(47,49,54));
        gc.fillRect(0, 0, this.bc.getCanvas().getWidth(),  this.bc.getCanvas().getHeight());

        for (PersonView pv: this.bc.getPeople())
        {
            pv.draw();
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
