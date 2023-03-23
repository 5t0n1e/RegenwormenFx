package View.Home;

import Model.FileHandeling;
import Model.RegenwormenModel;
import View.Player.Amount.AmountPresenter;
import View.Player.Amount.AmountView;
import View.options.OptionsPresenter;
import View.options.OptionsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.io.File;

public class HomePresenter {
    private RegenwormenModel model;
    private HomeView view;

    private FileHandeling fileHandeling;

    public HomePresenter(RegenwormenModel model, HomeView view, FileHandeling fileHandeling) {
        this.model = model;
        this.view = view;
        this.fileHandeling = fileHandeling;
        handleEvents();
    }

    private void handleEvents() {
        view.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AmountView amountView = new AmountView();
                AmountPresenter presentor = new AmountPresenter(model, amountView, fileHandeling);
                Scene scene = view.getScene();
                scene.setRoot(amountView);
            }
        });
        view.getOptions().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OptionsView optionsView = new OptionsView();
                OptionsPresenter presentor = new OptionsPresenter(model, optionsView, fileHandeling);
                Scene scene = view.getScene();
                scene.setRoot(optionsView);
            }
        });
    }

}
