package View;

import javafx.scene.image.ImageView;

public class TegelImage extends ImageView {
    private int number;
    String initPath;

    public TegelImage(String path, int number) {
        super(path);
        initPath = path;
        this.number = number;
    }

    public TegelImage(String path) {
        super(path);
        initPath = path;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getInitPath() {
        return initPath;
    }
}
