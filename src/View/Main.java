package View;

import Model.SIR;
import Model.SimulationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException
    {
        try
        {
//            SimulationModel test = new SIR();
//            test.calculateModel();
//            test.displayResult();
//            test.exportCsv();

            // Read file fxml and draw interface.  <Spinner fx:id="spinnerAlpha" layoutX="17.0" layoutY="49.0" prefHeight="25.0" prefWidth="110.0" />
            Parent root = FXMLLoader.load(getClass()
                    .getResource("Main.fxml"));

            stage.setTitle("Epidemic simulation project");
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch();
    }

}
