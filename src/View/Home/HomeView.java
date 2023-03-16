package View.Home;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HomeView extends BorderPane {
    private Button start, options;
    public HomeView() {
        initialiseNodes();
        layoutNodes();
    }
    private void layoutNodes() {
        VBox menu = new VBox(start, options);
        menu.setAlignment(Pos.CENTER);
        setCenter(menu);
        setBackground(new Background(new BackgroundImage(new Image("resources/Home_Achtergrond.jpg"), null, null, null, null)));
    }

    private void initialiseNodes() {
        start = new Button("START");
        options = new Button("OPTIONS");
    }
    public void start() {
        
    }

    public Button getStart() {
        return start;
    }

    public Button getOptions() {
        return options;
    }
}
