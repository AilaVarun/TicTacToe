package models;

import models.enums.CellState;

public class Cell {

    private int row;
    private int col;
    private Player player;
    private CellState CellState;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.CellState = CellState.EMPTY;
        this.player = null;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CellState getCellState() {
        return CellState;
    }

    public void setCellState(CellState cellState) {
        CellState = cellState;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
