package strategy.BOTStrategy;

import factory.BOTPlayingStrategyFactory;
import models.Board;
import models.Cell;
import models.Move;
import models.enums.CellState;

import java.util.Random;

public class EasyBOTPlayingStrategy implements BOTPlayingStrategy{

    @Override
    public Cell chooseCellToPlay(Board board) {

        int row;
        int col;
        int boardDimension = board.getDimension();
        Random random = new Random();

        do{
            row = random.nextInt(boardDimension); // Generates a number from 0 (inclusive) to boardDimension (exclusive)
            col = random.nextInt(boardDimension);
        } while(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED));

        return new Cell(row,col);
    }
}
