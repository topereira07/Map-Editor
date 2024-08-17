package io.codeforall.forsome;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {

    private static final int PADDING = 10;
    private static final int CELL_SIZE = 20;
    private int cols;
    private int rows;
    private Rectangle rectangle;
    private Cell[][] cells;

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.rectangle = new Rectangle(PADDING, PADDING, cols * CELL_SIZE, rows * CELL_SIZE);
        this.cells = new Cell[cols][rows];
    }

    public void createGrid() {
        this.rectangle.draw();

        int transY = 0;

        for (int i = 0; i < cols; i++) {
            int transX = 0;
            for (int j = 0; j < rows; j++) {
                cells[i][j] = new Cell(transX, transY, j, i);
                transX += CELL_SIZE;
            }
            transY += CELL_SIZE;
        }
    }

    public int getCols() {
        return this.cols;
    }

    public int getRows() {
        return this.rows;
    }

    public int getWidth() {
        return this.rectangle.getWidth();
    }

    public int getHeight() {
        return this.rectangle.getHeight();
    }

    public int getCellSize() {
        return CELL_SIZE;
    }

    public int getPadding() {
        return PADDING;
    }

    public int rowToY(int row) {
        return (row * CELL_SIZE) + PADDING;
    }

    public int columnToX(int col) {
        return (col * CELL_SIZE) + PADDING;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public class Cell {
        private int col;
        private int row;
        private int width;
        private int height;
        private Rectangle cell;
        private boolean isPainted;

        public Cell(int transX, int transY, int col, int row) {
            this.col = col;
            this.row = row;
            this.height = CELL_SIZE;
            this.width = CELL_SIZE;
            this.cell = new Rectangle(PADDING, PADDING, width, height);
            cell.translate(transX, transY);
            this.isPainted = false;
            cellDraw();
        }

        public void cellDraw() {
            this.cell.draw();
        }

        public boolean isPainted() {
            return isPainted;
        }

        public void setPainted(boolean painted) {
            isPainted = painted;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        public void paint() {

            this.cell.setColor(Color.BLACK);
            this.cell.fill();
            this.setPainted(true);
        }

        public void clear() {

            this.cell.draw();
            this.setPainted(false);
        }

        @Override
        public String toString() {
            if (isPainted) {
                return getCol() + " " +
                        getRow() + " " +
                        "1";
            }
            return getCol() + " " +
                    getRow() + " " +
                    "0";
        }
    }

}
