package strategy.WinningStrategy;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {

    private HashMap<Integer, HashMap<Symbol,Integer>> colMap=new HashMap<>();
    @Override
    public boolean checkWinner(Move move, int N) {

        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getPlayerSymbol();


        if(!colMap.containsKey(col)){
            colMap.put(col,new HashMap<>());
        }

        Map<Symbol,Integer> symbolMap=colMap.get(col);

        symbolMap.put(symbol,symbolMap.getOrDefault(symbol,0)+1);

        if(symbolMap.get(symbol)==N){
            return true;
        }

        return false;
    }

    public void handleUndo(Move move, int N){
        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getPlayerSymbol();
        Map<Symbol,Integer> colsMap=colMap.get(col);
        colsMap.put(symbol,colsMap.get(symbol)-1);
    };
}
