package View.RegenwormenEnd;

import Model.RegenwormenModel;
import View.Regenwormen.RegenwormenView;

public class RegenwormenEndPresenter {
    RegenwormenEndView view;
    RegenwormenModel model;
    public RegenwormenEndPresenter(RegenwormenModel model, RegenwormenEndView view) {
        this.view = view;
        this.model = model;
        handleEvents();
    }

    public void handleEvents() {
        view.initPlayers(model.getPlayers());
    }
}
