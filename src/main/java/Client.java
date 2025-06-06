import controllers.GameController;
import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.Game;
import models.Player;
import models.Symbol;
import models.enums.GameState;
import models.enums.PlayerType;
import strategy.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws PlayerCountNotValidException, BOTCountInvalidException {
        Player p1=new Player("Varun", new Symbol('U', "#####"), PlayerType.HUMAN);
        Player p2=new Player("Tharun", new Symbol('V', "#####"), PlayerType.HUMAN);
        Player p3=new Player("Monesh", new Symbol('W', "#####"), PlayerType.HUMAN);

        List<Player> players = new ArrayList<Player>();
        List<WinningStrategy> winningStrategies=new  ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        GameController gameController=new GameController();

        gameController.startGame(4,players,winningStrategies);

        Game game= Game.getBuilder().setDimension(4).setPlayers(players).setWinningStrategies(winningStrategies).build();


        while(game.getGameState().equals(GameState.IN_PROGRESS)){

            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        System.out.println("Enjoy");
    }
}
