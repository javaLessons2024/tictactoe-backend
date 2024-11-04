package com.ticTacToe.ticTacToe;

import com.ticTacToe.ticTacToe.Models.GameSession;
import com.ticTacToe.ticTacToe.Services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RedisService redisService;


    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate, RedisService redisService) {
        this.messagingTemplate = messagingTemplate;
        this.redisService = redisService;
    }

    @MessageMapping("/game/{sessionId}")
    @SendTo("/topic/game/{sessionId}")
    public GameSession chatMessage(GameSession gameSession) throws Exception {

        if(!startGame(gameSession)){
            return createEmptyBoard(gameSession);
        }

        if(isMoveLegal(gameSession)){
            redisService.put(gameSession.getSessionId(), gameSession);
            setTurn(gameSession);
            return gameSession;
        }

        return redisService.getGameSession(gameSession.getSessionId());

    }

    public boolean isMoveLegal(GameSession gameSession){
        int mismatchTimes = 0;
        GameSession gameSessionRedis = redisService.getGameSession(gameSession.getSessionId());
        for(int i = 0; i < 9; i++){
            if(gameSession.getBoardStatus()[i] != gameSessionRedis.getBoardStatus()[i]){
                mismatchTimes++;
            }
        }
        if(mismatchTimes > 1){
            return false;
        }
        return true;
    }
    public boolean startGame(GameSession gameSession){
        if(gameSession.getPlayer1() == null || gameSession.getPlayer2() == null){
            return false;
        }
        return true;

    }
    public void setTurn(GameSession gameSession){
        if(gameSession.isPlayerOneMove()){
            gameSession.setPlayerOneMove(false);
        }else{
            gameSession.setPlayerOneMove(true);
        }
    }
    public GameSession createEmptyBoard(GameSession gameSession){
        GameSession newSession = new GameSession(
                gameSession.getSessionId(),
                gameSession.getPlayer1(),
                gameSession.getPlayer2()
        );
        return newSession;
    }


}
