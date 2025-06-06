package models;

import factory.BOTPlayingStrategyFactory;
import models.enums.BOTDifficultyLevel;
import models.enums.PlayerType;
import strategy.BOTStrategy.BOTPlayingStrategy;

public class Bot extends Player {
    private BOTDifficultyLevel difficultyLevel;
    private BOTPlayingStrategy botPlayingStrategy;
    public Bot(String playerName, Symbol playerSymbol, PlayerType playerType,BOTDifficultyLevel difficultyLevel) {

        super(playerName, playerSymbol, playerType.BOT);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy= BOTPlayingStrategyFactory.getBOTPlayingStrategy(this.difficultyLevel);
    }

    public Move makeMove() {
        return null;
    }

}
