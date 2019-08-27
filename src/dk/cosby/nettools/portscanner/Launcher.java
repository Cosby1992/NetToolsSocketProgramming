package dk.cosby.nettools.portscanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dk/cosby/nettools/portscanner/port_scanner_gui.fxml"));
        primaryStage.setTitle("Port Scanner");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
