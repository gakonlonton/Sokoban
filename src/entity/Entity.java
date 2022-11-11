package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView {
    private final boolean solid;

    public Entity(String path, boolean solid) {
        setImage(new Image(path));
        this.solid = solid;
    }

    public boolean isSolid() {
        return solid;
    }
}
