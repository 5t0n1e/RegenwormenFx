package View.options;

import Model.FileHandeling;
import Model.RegenwormenModel;
import View.Home.HomePresenter;
import View.Home.HomeView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class OptionsPresenter {
    RegenwormenModel model;
    OptionsView view;
    FileHandeling fileHandeling;

    public OptionsPresenter(RegenwormenModel model, OptionsView view, FileHandeling fileHandeling) {
        this.model = model;
        this.view = view;
        this.fileHandeling = fileHandeling;
        view.updateTextFields(fileHandeling.getOptions());
        handleEvents();
    }

    private void handleEvents() {
        view.getOpenConfig().setOnAction(actionEvent -> fileHandeling.setConfig(getFile()));
        view.openHighScores.setOnAction(actionEvent -> fileHandeling.setHighScoresFile(getFile()));
        view.getToepassen().setOnAction(actionEvent -> {
            int tegels = 0;
            int dices = 0;
            try {
                tegels = Integer.parseInt(view.getTegelAmount().getText());
            } catch (Exception e) {
                Alert ex = new Alert(Alert.AlertType.ERROR, "Geef een getal voor tegel hoeveelheid!!");
                ex.showAndWait();
            }
            try {
                dices = Integer.parseInt(view.getDiceAmount().getText());
            } catch (Exception e) {
                Alert ex = new Alert(Alert.AlertType.ERROR, "Geef een getal voor dobbelsteen hoeveelheid!!");
                ex.showAndWait();
            }
            fileHandeling.updateConfig(String.format("tegels:%d%ndices:%d", tegels, dices));
        });
        view.getBack().setOnAction(actionEvent -> {
            HomeView homeView = new HomeView();
            List<Integer>options = fileHandeling.getOptions();
            model = new RegenwormenModel(options.get(0), options.get(1));
            HomePresenter presenter = new HomePresenter(model, homeView, fileHandeling);
            Scene scene = view.getScene();
            scene.setRoot(homeView);
        });
    }

    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/home/st0n1e"));
        fileChooser.setTitle("Open config File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Textfiles", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        return fileChooser.showOpenDialog(view.getScene().getWindow());
    }

}
