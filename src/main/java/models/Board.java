package models;

import models.enums.CellState;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int dimension;

    private List<List<Cell>> board;

    Board(int dimension) {
        this.dimension = dimension;
        this.board = new ArrayList<>(dimension);
        for (int i = 0; i < dimension; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }


     void printBoard() {
        for (List<Cell> cells : board) {
            for (Cell cell : cells) {
                if(cell.getCellState().equals(CellState.EMPTY)){
                    System.out.print("|   |");
                }
                else{
                    System.out.print("| "+cell.getPlayer().getPlayerSymbol().getSymbolChar() + " |");
                }
            }
            System.out.println();
        }
    }
}
