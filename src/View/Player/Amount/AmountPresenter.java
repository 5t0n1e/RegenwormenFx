package View.Player.Amount;

import Model.FileHandeling;
import Model.RegenwormenException;
import Model.RegenwormenModel;
import View.Player.Names.NamesPresenter;
import View.Player.Names.NamesView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class AmountPresenter {
    private RegenwormenModel model;
    private AmountView view;
    private FileHandeling fileHandeling;

    public AmountPresenter(RegenwormenModel model, AmountView view, FileHandeling fileHandeling) {
        this.model = model;
        this.view = view;
        this.fileHandeling = fileHandeling;
        handleEvents();
    }

    private void handleEvents() {
        view.getConfirm().setOnAction(actionEvent -> {
            try {
                String input = view.getPlayerAmountInput().getText();
                int inputInt = Integer.parseInt(input);
                if (!(inputInt > 1 && inputInt <= 4)) {
                    throw new RegenwormenException("Geef een nummer tussen 2 en 4!");
                }
                NamesView namesView = new NamesView(inputInt);
                NamesPresenter presenter = new NamesPresenter(model, namesView, fileHandeling);
                Scene scene = view.getScene();
                scene.setRoot(namesView);
            } catch (NumberFormatException ex) {
                alertBox("Geef een nummer!!");
            } catch (RegenwormenException ex) {
                alertBox(ex.getMessage());
            }
        });
    }

    private void alertBox(String message) {
        Alert ex = new Alert(Alert.AlertType.ERROR, message);
        ex.showAndWait();
    }

}
