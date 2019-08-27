package dk.cosby.nettools.findip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("find_ip_gui.fxml"));
        primaryStage.setTitle("Find IP");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);

    }
}
