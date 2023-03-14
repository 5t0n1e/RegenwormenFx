package View.Home;

import Model.RegenwormenModel;
import View.Player.Amount.AmountPresentor;
import View.Player.Amount.AmountView;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePresentor {
    private RegenwormenModel model;
    private HomeView view;

    public HomePresentor(RegenwormenModel model, HomeView view) {
        this.model = model;
        this.view = view;
        handleEvents();
    }
    private void handleEvents() {
        view.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AmountView amountView = new AmountView();
                AmountPresentor presentor = new AmountPresentor(model, amountView);
                Scene scene = view.getScene();
                scene.setRoot(amountView);
                //Om te kunnen switchen van mainView naar Applicatie
                //mainView.getScene().setRoot(tweedePaginaView);
                //tweedePaginaView.getScene().getWindow().sizeToScene();
            }
        });
    }

}
