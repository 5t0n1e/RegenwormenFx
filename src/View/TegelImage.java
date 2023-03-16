package View;

import javafx.scene.image.ImageView;

public class TegelImage extends ImageView {
    private int number, wurms;
    public TegelImage(String path, int number, int wurms) {
        super(path);
        this.number = number;
        this.wurms = wurms;
    }

    public TegelImage(String path) {
        super(path);
    }

    public int getNumber() {
        return number;
    }
    public int getWurms() {
        return wurms;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setWurms(int wurms) {
        this.wurms = wurms;
    }
}
