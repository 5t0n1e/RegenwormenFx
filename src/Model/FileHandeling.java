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
                    System.out.println("Config found");
                } else if (line.contains("highScores")) {
                    paths.set(1, split[1]);
                    System.out.println("HighScores found");
                } else {
                    System.out.println("niks gevonne kut");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Methode om het configuratiebestand bij te werken. Het schrijft een string naar het bestand op het opgegeven pad.
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
     * Methode om de scores in het highScores-bestand bij te werken. Het sorteert de scores van hoog naar laag en schrijft
     * ze naar het bestand op het opgegeven pad.
     * @param winner De speler die het spel heeft gewonnen en wiens score aan het bestand moet worden toegevoegd.
     */
    public void updateHighScores(Player winner) {
        Map<Integer, String> highScores = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paths.get(1)));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] split = line.split("\\s");
                highScores.put(Integer.parseInt(split[4]), split[0]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        highScores.put(winner.getTotalWurms(), winner.getName());
        StringBuilder out = new StringBuilder();
        for (Map.Entry<Integer, String> winnerEntry : highScores.entrySet()) {
            out.append(String.format("%s had %d wormen!!", winnerEntry.getValue(), winnerEntry.getKey()));
        }
        try (Formatter output = new Formatter(paths.get(1))) {
            output.format(out.toString());
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
     * @param highScoresFile Het bestand dat de high scores bevat.
     */
    public void setHighScoresFile(File highScoresFile) {
        if (highScoresFile != null) {
            updatePaths(paths.get(0), highScoresFile.getPath());
        } else {
            Alert ex = new Alert(Alert.AlertType.ERROR, "Kies een bestand!!");
            ex.showAndWait();
        }
    }

    /**
     * Update de twee bestandspaden in het geheugen en slaat deze op in een tekstbestand.
     * @param config Het bestand dat de configuratie bevat.
     * @param highScores Het bestand dat de high scores bevat.
     */
    private void updatePaths(String config, String highScores) {
        paths.set(0, config);
        paths.set(1, highScores);
        String out = String.format("config:%s%nhighScores:%s", config, highScores);
        File path = new File("src/paths.txt");
        try (Formatter output = new Formatter(path)) {
            output.format(out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
