package entity;

public class Player extends Entity {
    private Entity lastTile;

    public Player() {
        super("textures/player.gif", true);
        lastTile = new Grass();
    }

    public void setLastTile(Entity lastTile) {
        this.lastTile = lastTile;
    }

    public Entity getLastTile() {
        return lastTile;
    }
}
