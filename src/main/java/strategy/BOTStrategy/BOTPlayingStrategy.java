package strategy.BOTStrategy;

import models.Board;
import models.Cell;
import models.Move;

public interface BOTPlayingStrategy {

    public Cell chooseCellToPlay(Board board);
}
