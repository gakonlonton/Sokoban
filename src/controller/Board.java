package controller;

import entity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Entity>> board;
    private int player_x, player_y;
    private int numberOfCrate, target;
    public int height, width;

    public Board(int level) {
        Path dir = Paths.get("").toAbsolutePath();
        File file = new File(dir.normalize().toString() + "/res/levels/level" + (level) + ".txt");

        board = new ArrayList<>();
        numberOfCrate = 0;
        target = 0;

        try (BufferedReader scanner = new BufferedReader(new FileReader(file))) {
            int i = 0;
            String tempLine = scanner.readLine();
            while (tempLine != null && tempLine.length() != 0) {
                List<Entity> entities = new ArrayList<>();
                for (int j = 0; j < tempLine.length(); j++) {
                    Entity e = charToEntity(tempLine.charAt(j));
                    if (e instanceof Player) {
                        player_x = i;
                        player_y = j;
                    } else if (e instanceof Crate) {
                        numberOfCrate++;
                    }
                    entities.add(e);
                }
                board.add(entities);
                tempLine = scanner.readLine();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Entity charToEntity(char c) {
        switch (c) {
            case 'd':
                return new Crate();
            case 'p':
                return new Player();
            case 'x':
                return new Wall();
            case '.':
                return new Target();
            default:
                return new Grass();
        }
    }

    public List<List<Entity>> getBoard() {
        return board;
    }

    public void move(int x, int y) {
        if (!board.get(player_x + x).get(player_y + y).isSolid()) {
            movePlayer(x, y);
        } else if (board.get(player_x + x).get(player_y + y) instanceof Crate
                && !board.get(player_x + 2 * x).get(player_y + 2 * y).isSolid()) {
            if (board.get(player_x + 2 * x).get(player_y + 2 * y) instanceof Target) {
                target++;
            }
            moveCrate(x, y);
            movePlayer(x, y);
        }
    }

    private void movePlayer(int x, int y) {
        Entity nextTile = board.get(player_x + x).get(player_y + y).isSolid()
                ? new Grass() : board.get(player_x + x).get(player_y + y);
        board.get(player_x + x).set(player_y + y, board.get(player_x).get(player_y));
        board.get(player_x).set(player_y, ((Player) board.get(player_x).get(player_y)).getLastTile());

        player_x += x;
        player_y += y;
        ((Player) board.get(player_x).get(player_y)).setLastTile(nextTile);
    }

    private void moveCrate(int x, int y) {
        Entity nextTile = board.get(player_x + 2 * x).get(player_y + 2 * y).isSolid()
                ? new Grass() : board.get(player_x + 2 * x).get(player_y + 2 * y);
        board.get(player_x + 2 * x).set(player_y + 2 * y, board.get(player_x + x).get(player_y + y));
        board.get(player_x + x).set(player_y + y, ((Crate) board.get(player_x + 2 * x).get(player_y + 2 * y)).getLastTile());

        if (((Crate) board.get(player_x + 2 * x).get(player_y + 2 * y)).getLastTile() instanceof Target) {
            target--;
        }
        ((Crate) board.get(player_x + 2 * x).get(player_y + 2 * y)).setLastTile(nextTile);
    }

    public boolean completed() {
        return (target == numberOfCrate);
    }
}
