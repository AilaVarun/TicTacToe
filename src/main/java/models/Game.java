package models;

import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.enums.CellState;
import models.enums.GameState;
import models.enums.PlayerType;
import strategy.WinningStrategy.WinningStrategy;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player Winner;
    private int nextPlayerTurnIndex;
    private int CurrentPlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public int getNextPlayerTurnIndex() {
        return nextPlayerTurnIndex;
    }

    public void setNextPlayerTurnIndex(int nextPlayerTurnIndex) {
        this.nextPlayerTurnIndex = nextPlayerTurnIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public int getCurrentPlayerIndex() {
        return CurrentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        CurrentPlayerIndex = currentPlayerIndex;
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayerTurnIndex);
        System.out.println("It's "+currentPlayer.getPlayerName()+"'s turn");

        Cell dummycell=currentPlayer.chooseCellToPlay(board);

        int row=dummycell.getRow();
        int col=dummycell.getCol();

        if(!validateMove(dummycell.getRow(),dummycell.getCol())){
            System.out.println("Invalid move, can you choose again");
            return;
        }

        Cell cell=board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move move=new Move(currentPlayer,cell);

        moves.add(move);

        if(checkWinner(move)){
            gameState =gameState.ENDED;
            Winner=currentPlayer;
        }
        else if(moves.size()== board.getDimension()*board.getDimension()){
            gameState =gameState.DRAW;
        }
        CurrentPlayerIndex=nextPlayerTurnIndex;
        nextPlayerTurnIndex=(nextPlayerTurnIndex+1)%players.size();
//        if(nextPlayerTurnIndex==0){
//            CurrentPlayerIndex=0;
//        }
    }

    private boolean validateMove(int row, int column) {
        if(row<0 || row>= board.getDimension() || column<0 || column> board.getDimension()){
            return false;
        }

        if(board.getBoard().get(row).get(column).getCellState().equals(CellState.FILLED)){
            return false;
        }

        return true;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder {

        private int dimension;

        private List<Player> players;

        private List<WinningStrategy> winningStrategies;

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validatePlayerCount() throws PlayerCountNotValidException {
            if(players.size()!=dimension-1){
                throw new PlayerCountNotValidException("Players count is not equivalent to dimension -1");
            }
        }

        private void validateUnqiueSymbols() {
            HashSet<Character> symbols=new HashSet<Character>();
            for(Player p:players){
                symbols.add(p.getPlayerSymbol().getSymbolChar());
            }
            if(symbols.size()!=players.size()){
                throw new RuntimeException("Symbols for all players are not unique");
            }
        }

        private void validateBOTCount() throws BOTCountInvalidException {
            int botcount=0;
            for(Player p:players){
                if(p.getPlayerType()== PlayerType.BOT){
                    botcount++;
                }
            }

            if(botcount>dimension-2){
                throw new BOTCountInvalidException("Bots count is not equivalent to dimension -2");
            }
        }

        private void validate() throws PlayerCountNotValidException, BOTCountInvalidException {
            validateBOTCount();
            validatePlayerCount();
            validateUnqiueSymbols();
        }

        public Game build() throws PlayerCountNotValidException, BOTCountInvalidException {
            validate();
            return new  Game(dimension,players,winningStrategies);
        }
    }

    public boolean checkWinner(Move move) {
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(move, board.getDimension())){
                return true;
            }
        }
        return false;
    }

    public void printBoard() {
        board.printBoard();
    }


    public void UndoMove(){


        if(moves.size()==0){
            System.out.println("No Moves to Undo");
            return;
        }

        Move lastMove=moves.getLast();
        moves.remove(lastMove);


        Cell cell=lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);


        nextPlayerTurnIndex=(nextPlayerTurnIndex-1+players.size())%players.size();


        for(WinningStrategy winningStrategy:winningStrategies){
            winningStrategy.handleUndo(lastMove, board.getDimension());
        }

    }

    private Game(int dimension,List<Player> players,List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.Winner = null;
        this.nextPlayerTurnIndex = 0;
        this.gameState=GameState.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
        this.CurrentPlayerIndex=0;
    }

}
