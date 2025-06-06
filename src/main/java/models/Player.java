package models;

import models.enums.PlayerType;

public class Player {

    private String playerName;
    private Symbol playerSymbol;
    private PlayerType playerType;

    public Player(String playerName, Symbol playerSymbol, PlayerType playerType) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
        this.playerType = playerType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }


    public Move makeMove() {
        return null;
    }
}
