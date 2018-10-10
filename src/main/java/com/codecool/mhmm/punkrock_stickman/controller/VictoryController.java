package com.codecool.mhmm.punkrock_stickman.controller;

import com.codecool.mhmm.punkrock_stickman.service.Sound;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VictoryController {

    @GetMapping("/won")
    public String WinController(){
        Sound.playWon();
        return null;
    }
}
