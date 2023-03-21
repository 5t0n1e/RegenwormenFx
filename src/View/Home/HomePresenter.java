package View.Home;

import Model.RegenwormenModel;
import View.Player.Amount.AmountPresenter;
import View.Player.Amount.AmountView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class HomePresenter {
    private RegenwormenModel model;
    private HomeView view;

    public HomePresenter(RegenwormenModel model, HomeView view) {
        this.model = model;
        this.view = view;
        handleEvents();
    }

    private void handleEvents() {
        view.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AmountView amountView = new AmountView();
                AmountPresenter presentor = new AmountPresenter(model, amountView);
                Scene scene = view.getScene();
                scene.setRoot(amountView);
                //Om te kunnen switchen van mainView naar Applicatie
                //mainView.getScene().setRoot(tweedePaginaView);
                //tweedePaginaView.getScene().getWindow().sizeToScene();
            }
        });
    }

}
