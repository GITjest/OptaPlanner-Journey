package journey.guifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private final int WIDTH = 1440;
    private final int HEIGHT = WIDTH / 16 * 9;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("");
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        Scene scene = new Scene(new Layout(primaryStage).getLayout(), WIDTH, HEIGHT);
        scene.getStylesheets().add(this.getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
