package strategy.WinningStrategy;

import models.Move;
import models.Player;

public interface WinningStrategy {
    boolean checkWinner(Move move, int N);

    void handleUndo(Move move, int N);
}
