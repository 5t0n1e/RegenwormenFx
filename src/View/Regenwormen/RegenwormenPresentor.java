package View.Regenwormen;

import Model.RegenwormenModel;
import View.Die;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class RegenwormenPresentor {
    private RegenwormenModel model;
    private RegenwormenView view;
    private boolean thrown;
    public RegenwormenPresentor(RegenwormenModel model, RegenwormenView view) {
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
        for (Die dice : view.getDices()) {
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
    }
    private void checkKapotOrFinished() {
        model.checkKapotOrFinished();
        if (model.getCurrentRoll().isKapot()) {
            model.kapot();
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

