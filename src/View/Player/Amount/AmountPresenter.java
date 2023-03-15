package View.Player.Amount;

import Model.RegenwormenException;
import Model.RegenwormenModel;
import View.Player.Names.NamesPresenter;
import View.Player.Names.NamesView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class AmountPresenter {
    private RegenwormenModel model;
    private AmountView view;

    public AmountPresenter(RegenwormenModel model, AmountView view) {
        this.model = model;
        this.view = view;
        handleEvents();
    }
    private void handleEvents(){
        view.getConfirm().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String input = view.getPlayerAmountInput().getText();
                    int inputInt = Integer.parseInt(input);
                    if (!(inputInt > 1 && inputInt <= 8)) {
                        throw new RegenwormenException("Geef een nummer tussen 1 en 9!");
                    }
                    NamesView namesView = new NamesView(inputInt);
                    NamesPresenter presentor = new NamesPresenter(model, namesView);
                    Scene scene = view.getScene();
                    scene.setRoot(namesView);
                } catch(NumberFormatException ex) {
                    alert("Geef een nummer!!");
                } catch(RegenwormenException ex) {
                    alert(ex.getMessage());
                }
            }
        });
    }
    private void alert(String message) {
        Alert ex = new Alert(Alert.AlertType.ERROR, message);
        ex.showAndWait();
    }

}
