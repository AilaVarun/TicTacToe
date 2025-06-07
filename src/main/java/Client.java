import controllers.GameController;
import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.Bot;
import models.Game;
import models.Player;
import models.Symbol;
import models.enums.BOTDifficultyLevel;
import models.enums.GameState;
import models.enums.PlayerType;
import strategy.WinningStrategy.ColumnWinningStrategy;
import strategy.WinningStrategy.DiagnolWinningStrategy;
import strategy.WinningStrategy.RowWinningStrategy;
import strategy.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static models.enums.BOTDifficultyLevel.EASY;

public class Client {
    public static void main(String[] args) throws PlayerCountNotValidException, BOTCountInvalidException {
        List<Player> players = new ArrayList<Player>();
        Player p1=new Player("Varun", new Symbol('U', "#####"), PlayerType.HUMAN);
        Player p2=new Bot("Bot", new Symbol('V', "#####"), BOTDifficultyLevel.EASY);
        //Player p3=new Player("Monesh", new Symbol('W', "#####"), PlayerType.HUMAN);


        players.add(p1);
        players.add(p2);


        List<WinningStrategy> winningStrategies=new  ArrayList<>();

        ColumnWinningStrategy columnWinningStrategy=new ColumnWinningStrategy();
        RowWinningStrategy rowWinningStrategy=new RowWinningStrategy();
        DiagnolWinningStrategy DiagnolWinningStrategy=new DiagnolWinningStrategy();


        winningStrategies.add(DiagnolWinningStrategy);
        winningStrategies.add(columnWinningStrategy);
        winningStrategies.add(rowWinningStrategy);

        //players.add(p3);



        GameController gameController=new GameController();
        gameController.startGame(3,players,winningStrategies);
        gameController.Gameloop();
    }
}
