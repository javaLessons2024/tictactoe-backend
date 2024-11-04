package com.ticTacToe.ticTacToe.Controllers;

import com.ticTacToe.ticTacToe.Models.GameSession;
import com.ticTacToe.ticTacToe.Services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class SessionController {
    @Autowired
    SessionService sessionService;


    @GetMapping("/getGameSession/{name}")
    public ResponseEntity<GameSession> getGameSession(@PathVariable String name) {

        GameSession gameSession = sessionService.getGameSession(name);
        if(gameSession == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(gameSession, HttpStatus.OK);
    }

}
