package com.lwen.netease.controller;


import com.lwen.netease.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    MusicService musicService;

    public String getMusicDetail() {
        return "";
    }

    @RequestMapping(value = "/search/name/{n}/limit/{l}/type/{t}/offset/{o}",method = RequestMethod.POST)
    public String searchByMusicName(@PathVariable("n") String name,
                                    @PathVariable("l") String limit,
                                    @PathVariable("t") String type,
                                    @PathVariable("o") String offset
                                    ) throws IOException {
        return musicService.searchByMusicName(name,limit,type,offset);
    }
}
