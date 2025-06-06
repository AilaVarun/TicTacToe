package controllers;

import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.Game;
import models.Player;
import strategy.WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws PlayerCountNotValidException, BOTCountInvalidException {
        return Game.getBuilder().
                setDimension(dimension).
                setPlayers(players).
                setWinningStrategies(winningStrategies).
                build();
    }
}
