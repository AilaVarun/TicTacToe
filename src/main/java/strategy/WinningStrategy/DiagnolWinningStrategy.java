package strategy.WinningStrategy;

import models.Board;
import models.Move;
import models.Player;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagnolWinningStrategy implements WinningStrategy {


    HashMap<Symbol,Integer> leftDiagnolMap=new HashMap<>();
    HashMap<Symbol,Integer> rightDiagnolMap=new HashMap<>();
    @Override
    public boolean checkWinner(Move move, int N) {

        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getPlayerSymbol();


        if(row==col){
            leftDiagnolMap.put(symbol,leftDiagnolMap.getOrDefault(symbol,0)+1);

        }
        if(row+col==N-1){
            rightDiagnolMap.put(symbol,rightDiagnolMap.getOrDefault(symbol,0)+1);
        }

        return  leftDiagnolMap.getOrDefault(symbol,0)==N || rightDiagnolMap.getOrDefault(symbol,0)==N;
    }

    public void handleUndo(Move move, int N){
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getPlayerSymbol();
        if(row==col){
            leftDiagnolMap.put(symbol,leftDiagnolMap.get(symbol)-1);

        }
        if(row+col== N-1){
            rightDiagnolMap.put(symbol,rightDiagnolMap.get(symbol)-1);
        }

        return;

    };
}
