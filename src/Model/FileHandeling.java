package Model;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

public class FileHandeling {
    List<String> paths;

    /**
     * Initialiseert de lijst van paden en leest de paden in uit het bestand "src/paths.txt".
     * Het slaat de paden op in de lijst van paden. Als het bestand niet kan worden gelezen, wordt er een foutmelding afgedrukt.
     */
    public FileHandeling() {
        paths = new ArrayList<>();
        paths.add(null);
        paths.add(null);
        File path = new File("src/paths.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] split = line.split(":");
                if (line.contains("config")) {
                    paths.set(0, split[1]);
                } else if (line.contains("highScore")) {
                    paths.set(1, split[1]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Werkt he configuratiebestand bij. Het schrijft een string naar het bestand op het opgegeven pad.
     * @param out De string die moet worden geschreven.
     */
    public void updateConfig(String out) {
        try (Formatter output = new Formatter(paths.get(0))) {
            output.format(out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update de highscore aan de hand van de oude highscore te vergelijken met de winner die binnen komt.
     * De winner met de meeste wormen wordt vervolgens weg geschreven.
     * @param winner De speler die het spel heeft gewonnen, "Potentiële highscore houder".
     */
    public void updateHighScore(Player winner) {
        TreeMap<Integer, String> highScore = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paths.get(1)));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] split = line.split("\\s");
                highScore.put(Integer.parseInt(split[6]), split[0]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        highScore.put(winner.getTotalWurms(), winner.getName());
        try (Formatter output = new Formatter(paths.get(1))) {
            output.format(String.format("%s behoudt momenteel de highscore met %d wormen!!%n", highScore.lastEntry().getValue(), highScore.lastKey()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update de twee bestandspaden in het geheugen en slaat deze op in een tekstbestand.
     * @param config Het bestandspad dat de configuratie bevat.
     * @param highScore Het bestandspad dat de high scores bevat.
     */
    private void updatePaths(String config, String highScore) {
        paths.set(0, config);
        paths.set(1, highScore);
        String out = String.format("config:%s%nhighScore:%s", config, highScore);
        File path = new File("src/paths.txt");
        try (Formatter output = new Formatter(path)) {
            output.format(out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update het configuratie bestandspad in het geheugen en slaat deze op in een tekstbestand.
     * @param configFile Het bestand dat de configuratie bevat.
     */
    public void setConfig(File configFile) {
        if (configFile != null) {
            updatePaths(configFile.getPath(), paths.get(1));
        } else {
            Alert ex = new Alert(Alert.AlertType.ERROR, "Kies een bestand!!");
            ex.showAndWait();
        }
    }

    /**
     * Update het high scores bestandspad in het geheugen en slaat deze op in een tekstbestand.
     * @param highScoreFile Het bestand dat de high scores bevat.
     */
    public void setHighScoreFile(File highScoreFile) {
        if (highScoreFile != null) {
            updatePaths(paths.get(0), highScoreFile.getPath());
        } else {
            Alert ex = new Alert(Alert.AlertType.ERROR, "Kies een bestand!!");
            ex.showAndWait();
        }
    }

    /**
     * Deze methode leest de opties uit het configuratiebestand en retourneert ze als een lijst van integers.
     * @return de lijst van opties uit het configuratiebestand
     */
    public List<Integer> getOptions() {
        List<Integer> options = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paths.get(0)));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] split = line.split(":");
                options.add(Integer.parseInt(split[1]));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return options;
    }
}
