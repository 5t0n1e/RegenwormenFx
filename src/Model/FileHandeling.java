package Model;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

public class FileHandeling {
    List<String> paths;

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

    public void updateConfig(String out) {
        try (Formatter output = new Formatter(paths.get(0))) {
            output.format(out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateHighScores(Player winner) {
        TreeMap<Integer, String> highScores = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(paths.get(1)));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] split = line.split("\\s");
                highScores.put(Integer.parseInt(split[6]), split[0]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        highScores.put(winner.getTotalWurms(), winner.getName());
        try (Formatter output = new Formatter(paths.get(1))) {
            output.format(String.format("%s behoudt momenteel de highscore met %d wormen!!%n", highScores.lastEntry().getValue(), highScores.lastKey()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setConfig(File configFile) {
        if (configFile != null) {
            updatePaths(configFile.getPath(), paths.get(1));
        } else {
            Alert ex = new Alert(Alert.AlertType.ERROR, "Kies een bestand!!");
            ex.showAndWait();
        }
    }

    public void setHighScoresFile(File highScoresFile) {
        if (highScoresFile != null) {
            updatePaths(paths.get(0), highScoresFile.getPath());
        } else {
            Alert ex = new Alert(Alert.AlertType.ERROR, "Kies een bestand!!");
            ex.showAndWait();
        }
    }

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
