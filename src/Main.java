import Model.FileHandeling;
import Model.RegenwormenModel;
import View.Home.HomePresenter;
import View.Home.HomeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HomeView view = new HomeView();
        FileHandeling fileHandeling = new FileHandeling();
        List<Integer> options = fileHandeling.getOptions();
        RegenwormenModel model = new RegenwormenModel(options.get(0), options.get(1));
        HomePresenter presentor = new HomePresenter(model, view, fileHandeling);
        stage.setScene(new Scene(view));
        stage.setTitle("Regenwormen");
        stage.setWidth(1920);//1280
        stage.setHeight(1080);//800
        stage.show();
    }
}