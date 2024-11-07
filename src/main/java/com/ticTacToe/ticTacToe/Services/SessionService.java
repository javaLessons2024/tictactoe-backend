package com.ticTacToe.ticTacToe.Services;

import com.ticTacToe.ticTacToe.Models.GameSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class SessionService {
    @Autowired
    private RedisService redisService;

    public synchronized GameSession getGameSession(String name){

        Set<GameSession> gameSessionSet = redisService.getAllGameSessions();

        for (GameSession gameSession : gameSessionSet){
            if(gameSession.getPlayer2() == null && !gameSession.getPlayer1().equals(name)){
                gameSession.setPlayer2(name);
                redisService.put(gameSession.getSessionId(),gameSession);
                return gameSession;
            }
        }

        GameSession gameSession = new GameSession(getKey(), name,null);
        redisService.put(gameSession.getSessionId(),gameSession);
        return gameSession;
    }


    private String getKey(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }





}
