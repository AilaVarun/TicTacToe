package models;

import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import factory.BOTPlayingStrategyFactory;
import models.enums.BOTDifficultyLevel;
import models.enums.CellState;
import models.enums.PlayerType;
import strategy.BOTStrategy.BOTPlayingStrategy;

import java.util.Random;

public class Bot extends Player {
    private BOTDifficultyLevel difficultyLevel;
    private BOTPlayingStrategy botPlayingStrategy;
    public Bot(String playerName, Symbol playerSymbol,BOTDifficultyLevel difficultyLevel) {

        super(playerName, playerSymbol, PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy= BOTPlayingStrategyFactory.getBOTPlayingStrategy(this.difficultyLevel);
    }

    public Cell chooseCellToPlay(Board board) {
        return botPlayingStrategy.chooseCellToPlay(board);
    }

}
