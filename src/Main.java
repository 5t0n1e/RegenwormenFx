import Model.FileHandeling;
import Model.RegenwormenModel;
import View.Home.HomePresenter;
import View.Home.HomeView;
import View.TegelImage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TegelImage tegel = new TegelImage("resources/home.png");
        Label label = new Label();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(label, tegel);
        VBox vbox = new VBox();
        vbox.getChildren().add(hbox);

        HomeView view = new HomeView();
        FileHandeling fileHandeling = new FileHandeling();
        List<Integer> options = fileHandeling.getOptions();
        RegenwormenModel model = new RegenwormenModel(options.get(0), options.get(1));
        HomePresenter presentor = new HomePresenter(model, view, fileHandeling);
        Scene scene = new Scene(view);
        scene.getStylesheets().add("css/Regenwormen.css");
        stage.setScene(scene);
        stage.setTitle("Regenwormen");
        stage.setWidth(1920);//1280
        stage.setHeight(1080);//800
        stage.show();
    }
}