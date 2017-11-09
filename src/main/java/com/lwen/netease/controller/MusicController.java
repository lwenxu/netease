package com.lwen.netease.controller;


import com.lwen.netease.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    MusicService musicService;

    public String getMusicDetail() {
        return "";
    }

    /**
     *
     * 通过名称获取歌曲  专辑   歌手  歌单  用户
     *
     * @param name   搜索词
     * @param limit  limit: 返回数量
     * @param type   1 单曲  10 专辑   100 歌手   1000 歌单   1002 用户
     * @param offset   offset: 偏移数量，用于分页
     * @return  json字符串
     * @throws IOException
     */
    @RequestMapping(value = "/search/name/{n}/limit/{l}/type/{t}/offset/{o}",method = RequestMethod.GET)
    public String searchByName(@PathVariable(value = "n",required = true) String name,
                                    @PathVariable("l") String limit,
                                    @PathVariable("t") String type,
                                    @PathVariable("o") String offset
                                    ) throws IOException {
        return musicService.searchByMusicName(name,limit,type,offset);
    }


    /**
     * 获取歌手的专辑列表
     * @param name  歌手名字
     * @param offset  分页
     * @param limit  数量
     * @return  json
     */
    @RequestMapping(value = "/singerList/name/{name}/offset/{offset}/limit/{limit}", method = RequestMethod.GET)
    public String listBySinger(@PathVariable("name") String name,
                               @PathVariable("offset") Integer offset,
                               @PathVariable("limit") Integer limit) {
        long artist_id = getSingerIdByName(name);
        return musicService.listBySinger(artist_id, offset, limit);
    }


    /**
     * 根据歌曲 id 获取歌曲详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/songDetail/id/{id}", method = RequestMethod.GET)
    public String getSongDetailById(@PathVariable("id") String id) {
        return musicService.getSongDetailById(id);
    }

    /**
     * 根据专辑的 id 获取专辑内的歌曲
     * @param id  专辑 id
     * @return
     */
    public String getAlbumById(String id) {
        return musicService.getAlbumById(id);
    }


    private long getSingerIdByName(String name) {
        return 10557;
    }
}