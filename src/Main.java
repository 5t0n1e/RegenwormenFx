import Model.RegenwormenModel;
import View.Home.HomePresenter;
import View.Home.HomeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HomeView view = new HomeView();
        RegenwormenModel model = new RegenwormenModel();
        HomePresenter presentor = new HomePresenter(model, view);
        stage.setScene(new Scene(view));
        stage.setTitle("Regenwormen");
        stage.setWidth(1600);
        stage.setHeight(1200);
        stage.show();
    }
}