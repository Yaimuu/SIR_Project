/**
 *
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package View;

import Controller.BehaviourController;
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
    private int simulationCurrentTime = 0;
    private int simulationDuration = 2000;
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
        if(this.simulationCurrentTime > this.simulationDuration)
            this.reset();
        timer.start();
    }

    public void stop()
    {
        timer.stop();
    }

    public void reset()
    {
        this.simulationCurrentTime = 0;
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
    }

    public void updateCanvas()
    {
        if(this.simulationCurrentTime <= this.simulationDuration)
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
            this.simulationCurrentTime++;
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
