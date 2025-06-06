package factory;

import models.enums.BOTDifficultyLevel;
import strategy.BOTStrategy.BOTPlayingStrategy;
import strategy.BOTStrategy.EasyBOTPlayingStrategy;
import strategy.BOTStrategy.HardBOTPlayingStrategy;
import strategy.BOTStrategy.MediumBOTPlayingStrategy;

public class BOTPlayingStrategyFactory {
    public static BOTPlayingStrategy getBOTPlayingStrategy(BOTDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BOTDifficultyLevel.EASY)){
            return new EasyBOTPlayingStrategy();
        }
        else if(botDifficultyLevel.equals(BOTDifficultyLevel.MEDIUM)){
            return new MediumBOTPlayingStrategy();
        }
        else {
            return new HardBOTPlayingStrategy();
        }
    }
}
