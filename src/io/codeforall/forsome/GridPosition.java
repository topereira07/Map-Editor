package io.codeforall.forsome;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GridPosition {

    private int col;
    private int row;
    private Rectangle rectangle;
    private Grid grid;

    public GridPosition(Grid grid) {
        this.grid = grid;
        this.col = this.grid.columnToX(this.grid.getCols());
        this.row = this.grid.rowToY(this.grid.getRows());
        this.rectangle = new Rectangle(col, row, this.grid.getCellSize(), this.grid.getCellSize());
        drawGrid();
    }

    public void drawGrid() {
        this.rectangle.draw();
    }

}