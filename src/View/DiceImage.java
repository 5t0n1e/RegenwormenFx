package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiceImage extends ImageView {
    private int face;
    public DiceImage() {}
    public void setFace(int face) {
        setImage(new Image(String.format("resources/die%d.png", face)));
        this.face = face;
    }
    public int getFace() {
        return face;
    }
}
