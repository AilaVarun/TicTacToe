package strategy.WinningStrategy;

import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {


    private HashMap<Integer,HashMap<Symbol,Integer>> rowMap=new HashMap<>();
    @Override
    public boolean checkWinner(Move move, int N) {

        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getPlayerSymbol();


        if(!rowMap.containsKey(row)){
            rowMap.put(row,new HashMap<>());
        }

        Map<Symbol,Integer> symbolMap=rowMap.get(row);

        symbolMap.put(symbol,symbolMap.getOrDefault(symbol,0)+1);

        if(symbolMap.get(symbol)==N){
            return true;
        }


        return false;
    }

    public void handleUndo(Move move,int N){
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getPlayerSymbol();
        Map<Symbol,Integer> rowsMap=rowMap.get(row);
        rowsMap.put(symbol,rowsMap.get(symbol)-1);
    }
}
