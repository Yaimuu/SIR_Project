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
        this.updateCanvas();
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
        GraphicsContext gc = this.bc.getCanvas().getGraphicsContext2D();
        gc.setFill(Color.CORNSILK);
        gc.fillRect(0, 0, this.bc.getCanvas().getWidth(),  this.bc.getCanvas().getHeight());
        gc.setFill(Color.FORESTGREEN);

        Person lastPerson = null;
        for (PersonView pv : this.bc.getPeople())
        {
            pv.getPerson().isCollidingBounds(this.bc.getCanvas().getWidth(), this.bc.getCanvas().getHeight());
            for(int i = 0; i < this.bc.getPeople().stream().count(); i++)
            {
                if(lastPerson != null)
                {
                    //System.out.println(lastPerson);
                    pv.getPerson().isCollidingPerson(lastPerson);
                }
                lastPerson = this.bc.getPeople().get(i).getPerson();
            }
            lastPerson = pv.getPerson();
        }

        for (PersonView pv: this.bc.getPeople())
        {
            pv.getPerson().move();
        }

        this.draw();
    }

    @Override
    public void draw()
    {
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
