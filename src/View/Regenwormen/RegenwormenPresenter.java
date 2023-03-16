package View.Regenwormen;

import Model.RegenwormenModel;
import View.DiceImage;
import View.RegenwormenEnd.RegenwormenEndPresenter;
import View.RegenwormenEnd.RegenwormenEndView;
import View.TegelImage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RegenwormenPresenter {
    private RegenwormenModel model;
    private RegenwormenView view;
    private boolean thrown;
    public RegenwormenPresenter(RegenwormenModel model, RegenwormenView view) {
        this.model = model;
        this.view = view;
        thrown = false;
        view.initialiseNodes(model.getPlayers(), model.getTegels());
        handleEvents();
    }
    private void handleEvents() {
        view.getThrowDice().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!thrown) {
                    model.rollDice();
                    thrown = true;
                    checkKapotOrFinished();
                    updateView();
                }
            }
        });
        for (DiceImage dice : view.getDices()) {
            dice.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (thrown && view.getThrownDices().getChildren().contains(dice) && model.checkChoice(dice.getFace())) {
                        //model.checkChoice(dice.getFace())
                        model.updateRoll(dice.getFace());
                        updateView();
                        thrown = false;
                    }
                }
            });
        }
        view.getStopRoll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!thrown) {
                    model.finishRoll();
                    checkKapotOrFinished();
                    updateView();
                }
            }
        });
        for (TegelImage tegel : view.getPlayers()) {
            tegel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (model.checkSteal(tegel.getNumber()) && !thrown) {
                        model.steal();
                        model.createNextRoll();
                        updateView();
                    }
                }
            });
        }
    }
    private void checkKapotOrFinished() {
        model.checkKapotOrFinished();
        if (model.checkEnd()) {
            RegenwormenEndView endView = new RegenwormenEndView();
            RegenwormenEndPresenter presentor = new RegenwormenEndPresenter(model, endView);
            Scene scene = view.getScene();
            scene.setRoot(endView);
        }
        if (model.getCurrentRoll().isKapot()) {
            model.kapot();
            updateView();
            view.kapot();
            thrown = false;
            model.createNextRoll();
        } else if (model.getCurrentRoll().isFinished()) {
            model.createNextRoll();
        }
    }
    private void updateView() {
        view.updateTegels(model.getTegels());
        view.updatePlayers(model.getPlayers(), model.getCurrentRoll());
        view.updateDices(model.getCurrentRoll());
    }
}

