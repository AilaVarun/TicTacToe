package models;

import models.enums.PlayerType;

import java.util.Scanner;

public class Player {

    private String playerName;
    private Symbol playerSymbol;
    private PlayerType playerType;
    private Scanner scanner= new Scanner(System.in);

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


    public Cell chooseCellToPlay(Board board) {

        System.out.println("Enter the row number where you want to make");
        int row = scanner.nextInt();
        System.out.println("Enter the column number where you want to make");
        int column = scanner.nextInt();

        return new Cell(row,column);
    }
}
