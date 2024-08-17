package io.codeforall.forsome;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;

public class GridPainter {

    private Grid grid;
    private Painter painter;

    private String savePosition;

    public GridPainter(int cols, int rows) {
        this.grid = new Grid(cols, rows);
    }

    public void init() {
        this.grid.createGrid();
        this.painter = new Painter();
    }

    private class Painter implements KeyboardHandler {
        private Rectangle painter;
        private Keyboard keyboard;
        private Grid.Cell[][] cells;
        private Grid.Cell currentCell;

        public Painter() {
            this.painter = new Rectangle(grid.getPadding(), grid.getPadding(), grid.getCellSize(), grid.getCellSize());
            this.painter.setColor(Color.BLUE);
            this.painter.fill();
            this.keyboard = new Keyboard(this);
            addKeyboard();
            this.cells = grid.getCells();
            this.currentCell = this.cells[0][0];
        }

        public void addKeyboard() {
            KeyboardEvent moveRight = new KeyboardEvent();
            moveRight.setKey(KeyboardEvent.KEY_RIGHT);
            moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(moveRight);

            KeyboardEvent moveLeft = new KeyboardEvent();
            moveLeft.setKey(KeyboardEvent.KEY_LEFT);
            moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(moveLeft);

            KeyboardEvent moveUp = new KeyboardEvent();
            moveUp.setKey(KeyboardEvent.KEY_UP);
            moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(moveUp);

            KeyboardEvent moveDown = new KeyboardEvent();
            moveDown.setKey(KeyboardEvent.KEY_DOWN);
            moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(moveDown);

            KeyboardEvent changeColor = new KeyboardEvent();
            changeColor.setKey(KeyboardEvent.KEY_SPACE);
            changeColor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(changeColor);

            KeyboardEvent save = new KeyboardEvent();
            save.setKey(KeyboardEvent.KEY_S);
            save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(save);

            KeyboardEvent load = new KeyboardEvent();
            load.setKey(KeyboardEvent.KEY_L);
            load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(load);

            KeyboardEvent clear = new KeyboardEvent();
            clear.setKey(KeyboardEvent.KEY_C);
            clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(clear);
        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {
            int keyPressed = keyboardEvent.getKey();
            if (keyPressed == keyboardEvent.KEY_RIGHT) {
                move(GridDirection.RIGHT);
            }
            if (keyPressed == keyboardEvent.KEY_LEFT) {
                move(GridDirection.LEFT);
            }
            if (keyPressed == keyboardEvent.KEY_UP) {
                move(GridDirection.UP);
            }
            if (keyPressed == keyboardEvent.KEY_DOWN) {
                move(GridDirection.DOWN);
            }
            if (keyPressed == keyboardEvent.KEY_SPACE) {
                changeColor();
            }
            if (keyPressed == keyboardEvent.KEY_S) {
                saveState();
            }
            if (keyPressed == KeyboardEvent.KEY_L) {
                loadState();
            }
            if (keyPressed == KeyboardEvent.KEY_C) {
                clear();
            }
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }

        public void move(GridDirection gridDirection) {
            Grid.Cell beforeMovement = this.currentCell;

            switch (gridDirection) {
                case RIGHT:
                    if (painter.getX() == (grid.getWidth() - grid.getPadding())) {
                        return;
                    }
                    this.currentCell = this.cells[beforeMovement.getRow()][beforeMovement.getCol() + 1];
                    this.painter.translate(grid.getCellSize(), 0);
                    break;
                case LEFT:
                    if (painter.getX() == grid.getPadding()) {
                        return;
                    }
                    this.currentCell = this.cells[beforeMovement.getRow()][beforeMovement.getCol() - 1];
                    this.painter.translate(-grid.getCellSize(), 0);
                    break;
                case UP:
                    if (painter.getY() == grid.getPadding()) {
                        return;
                    }
                    this.currentCell = this.cells[beforeMovement.getRow() - 1][beforeMovement.getCol()];
                    this.painter.translate(0, -grid.getCellSize());
                    break;
                case DOWN:
                    if (painter.getY() == (grid.getHeight() - grid.getPadding())) {
                        return;
                    }
                    this.currentCell = this.cells[beforeMovement.getRow() + 1][beforeMovement.getCol()];

                    this.painter.translate(0, grid.getCellSize());
                    break;
            }
        }

        public void changeColor() {
            if (!this.currentCell.isPainted()) {
                this.currentCell.paint();
                return;
            }
            this.currentCell.clear();
        }

        public void saveState() {
            try {
                FileWriter writer = new FileWriter("src/resources/savefile.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                savePosition = "" + grid.getCols() + "\n" + grid.getRows() + "\n";

                for (int i = 0; i < grid.getCols(); i++) {
                    for (int j = 0; j < grid.getRows(); j++) {
                        savePosition += cells[i][j] + "\n";
                    }
                }
                bufferedWriter.write(savePosition);
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void loadState() {
            try {
                FileReader reader = new FileReader("src/resources/savefile.txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = "";

                int lineCounter = 0;
                int cols = 0;
                int rows = 0;

                while ((line = bufferedReader.readLine()) != null) {
                    lineCounter++;

                    if (lineCounter == 1) {
                        cols = Integer.parseInt(line);
                    }
                    if (lineCounter == 2) {
                        rows = Integer.parseInt(line);
                    }
                    if (lineCounter > 2) {
                        String[] splitedLine = line.split(" ");

                        int posY = Integer.parseInt(splitedLine[0]);
                        int posX = Integer.parseInt(splitedLine[1]);
                        int state = Integer.parseInt(splitedLine[2]);

                        if (state == 0) {
                            cells[posX][posY].clear();
                            continue;
                        }
                        cells[posX][posY].paint();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void clear() {
            for (int i = 0; i < grid.getCols(); i++) {
                for (int j = 0; j < grid.getRows(); j++) {
                    this.cells[i][j].clear();
                }
            }
        }

    }
}