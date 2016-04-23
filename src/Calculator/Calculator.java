package Calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Calculator extends Application {

    @Override
    public void start(Stage mainWindow) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("userinterface.fxml"));
        mainWindow.setTitle("Calculator");
        mainWindow.setScene(new Scene(root, 300, 400));
        mainWindow.setResizable(false);
        mainWindow.getIcons().add(new Image("file:src/Calculator/icon.PNG"));
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
