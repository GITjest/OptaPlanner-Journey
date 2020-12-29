package journey.guifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.resizableProperty().setValue(Boolean.FALSE);

        Scene scene = new Scene(new Layout(primaryStage).getLayout());
        scene.getStylesheets().add(this.getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
