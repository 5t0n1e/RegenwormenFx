package View.Player.Amount;

import Model.RegenwormenError;
import Model.RegenwormenModel;
import View.Player.Names.NamesPresentor;
import View.Player.Names.NamesView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class AmountPresentor {
    private RegenwormenModel model;
    private AmountView view;

    public AmountPresentor(RegenwormenModel model, AmountView view) {
        this.model = model;
        this.view = view;
        handleEvents();
    }
    private void handleEvents(){
        view.getConfirm().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String input = view.getPlayerAmountInput().getText();
                if (!input.contains("123456789")) {
                    //throw new RegenwormenError("Please pass a numeric symbol!!");
                }
                int inputInt = Integer.parseInt(input);
                if (inputInt > 1 && inputInt <= 8) {
                    //throw new RegenwormenError("Please pass a number between 1 and 9!!");
                }
                NamesView namesView = new NamesView(inputInt);
                NamesPresentor presentor = new NamesPresentor(model, namesView);
                Scene scene = view.getScene();
                scene.setRoot(namesView);
            }
        });
    }

}
