package View.Home;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class HomeView extends BorderPane {
    private Button start, options;

    public HomeView() {
        initialiseNodes();
        layoutNodes();
    }

    private void layoutNodes() {
        VBox menu = new VBox(start, options);
        menu.setSpacing(20);
        menu.setAlignment(Pos.CENTER);
        setCenter(menu);
        setBackground(new Background(new BackgroundImage(new Image("resources/home.png"), null, null, null, null)));
    }

    private void initialiseNodes() {
        start = new Button("START");
        start.setMinSize(200, 100);
        start.setFont(new Font("Nimbus Mono PS Bold Italic", 40));
        start.setBackground(new Background(new BackgroundFill(Paint.valueOf("ff946845"), null, null)));

        options = new Button("OPTIONS");
        options.setMinSize(200, 100);
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
