package View.Regenwormen;

import Model.FileHandeling;
import Model.RegenwormenModel;
import View.DiceImage;
import View.RegenwormenEnd.RegenwormenEndPresenter;
import View.RegenwormenEnd.RegenwormenEndView;
import View.TegelImage;
import javafx.scene.Scene;

public class RegenwormenPresenter {
    private RegenwormenModel model;
    private RegenwormenView view;
    private FileHandeling fileHandeling;
    private boolean thrown;

    public RegenwormenPresenter(RegenwormenModel model, RegenwormenView view, FileHandeling fileHandeling) {
        this.model = model;
        this.view = view;
        this.fileHandeling = fileHandeling;
        thrown = false;
        view.initialiseNodes(model.getPlayers(), model.getTegels());
        handleEvents();
    }

    private void handleEvents() {
        view.getThrowDice().setOnAction(actionEvent -> {
            if (!thrown) {
                model.rollDice();
                thrown = true;
                checkKapotOrFinished();
                updateView();
            }
        });
        for (DiceImage dice : view.getDices()) {
            dice.setOnMouseClicked(mouseEvent -> {
                if (thrown && view.getThrownDices().getChildren().contains(dice) && model.checkChoice(dice.getFace())) {
                    //model.checkChoice(dice.getFace())
                    model.updateRoll(dice.getFace());
                    updateView();
                    thrown = false;
                }
            });
        }
        view.getStopRoll().setOnAction(actionEvent -> {
            if (!thrown) {
                model.finishRoll();
                checkKapotOrFinished();
                updateView();
            }
        });
        for (TegelImage tegel : view.getPlayers()) {
            tegel.setOnMouseClicked(mouseEvent -> {
                if (model.checkSteal(tegel.getNumber()) && !thrown) {
                    model.steal();
                    model.createNextRoll();
                    updateView();
                }
            });
        }
    }

    private void checkKapotOrFinished() {
        model.checkKapotOrFinished();
        if (model.checkEnd()) {
            RegenwormenEndView endView = new RegenwormenEndView();
            RegenwormenEndPresenter presentor = new RegenwormenEndPresenter(model, endView, fileHandeling);
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

