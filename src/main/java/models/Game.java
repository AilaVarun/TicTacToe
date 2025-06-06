package models;

import exceptions.BOTCountInvalidException;
import exceptions.PlayerCountNotValidException;
import models.enums.GameState;
import models.enums.PlayerType;
import strategy.WinningStrategy.WinningStrategy;

import javax.swing.*;
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
                symbols.add(p.getPlayerSymbol().getPlayerSymbol());
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

    public Player checkWinner() {
        for(WinningStrategy winningStrategy:winningStrategies){
            winningStrategy.checkWinner();
        }
        return Winner;
    }

    public void printBoard() {
        board.printBoard();
    }

    private Game(int dimension,List<Player> players,List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.Winner = null;
        this.nextPlayerTurnIndex = 0;
        this.gameState=GameState.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
    }

}
