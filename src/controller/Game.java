package controller;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Game {
    public GridPane gridPane;
    private Board board;
    private int level;

    public Game() {
        level = 1;
        board = new Board(level);
    }

    public void initialize() {
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setPadding(new Insets(0, 0, 0, 0));

        new Thread(()->{
            update();
            Platform.runLater(() -> gridPane.getScene().setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP, W -> board.move(-1, 0);
                    case DOWN, S -> board.move(1, 0);
                    case LEFT, A -> board.move(0, -1);
                    case RIGHT, D -> board.move(0, 1);
                    default -> {
                    }
                }
                update();
            }));
        }).start();
    }

    private void update() {
        gridPane.getChildren().clear();
        if (board.completed()) {
            level++;
            board = new Board(level);
        }
        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().get(i).size(); j++) {
                gridPane.add(board.getBoard().get(i).get(j), j, i);
            }
        }
    }
}
