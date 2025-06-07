package controllers;

import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.Board;
import models.Game;
import models.Player;
import models.enums.GameState;
import models.enums.PlayerType;
import strategy.WinningStrategy.WinningStrategy;

import java.util.List;
import java.util.Scanner;

public class GameController {

    private Game game;
    public void startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws PlayerCountNotValidException, BOTCountInvalidException {
        this.game= Game.getBuilder().
                setDimension(dimension).
                setPlayers(players).
                setWinningStrategies(winningStrategies).
                build();
    }

    Scanner scanner = new Scanner(System.in);
    public void Gameloop(){
        while(game.getGameState().equals(GameState.IN_PROGRESS)) {

            game.printBoard();
            game.makeMove();

            if(game.getPlayers().get(game.getCurrentPlayerIndex()).getPlayerType().equals(PlayerType.HUMAN)){
                System.out.println("Do you want to Undo y/n");
                String UndoAnswer = scanner.next();
                if (UndoAnswer.equalsIgnoreCase("y")) {
                    game.UndoMove();
                }
            }

        }

        game.printBoard();
        if(game.getGameState().equals(GameState.ENDED)){
            System.out.println("GAME ENDED AND THE WINNER IS: "+game.getWinner().getPlayerName());
        }
        else{
            System.out.println("GAME IS DRAW");
        }
    }
}
