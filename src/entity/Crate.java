package entity;

public class Crate extends Entity {
    private Entity lastTile;

    public Crate() {
        super("textures/crate.gif", true);
        lastTile = new Grass();
    }

    public void setLastTile(Entity lastTile) {
        this.lastTile = lastTile;
    }

    public Entity getLastTile() {
        return lastTile;
    }
}
