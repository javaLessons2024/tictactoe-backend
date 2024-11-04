package com.ticTacToe.ticTacToe.Models;

public class GameSession {
    private String sessionId;
    private String player1;
    private String player2;
    private Character[] boardStatus;
    private boolean playerOneMove; // True = player 1 move, False = player 2 move.

    public GameSession(String sessionId, String player1, String player2) {
        this.sessionId = sessionId;
        this.player1 = player1;
        this.player2 = player2;
        this.boardStatus = new Character[]{' ',' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        this.playerOneMove = false;
    }


    public GameSession() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public Character[] getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(Character[] boardStatus) {
        this.boardStatus = boardStatus;
    }

    public boolean isPlayerOneMove() {
        return playerOneMove;
    }

    public void setPlayerOneMove(boolean playerOneMove) {
        this.playerOneMove = playerOneMove;
    }
}

