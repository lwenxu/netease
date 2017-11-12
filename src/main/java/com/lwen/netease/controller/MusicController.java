package com.lwen.netease.controller;


import com.lwen.netease.Enmu.ResultEnmu;
import com.lwen.netease.entity.Result;
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
    public Result searchByName(@PathVariable(value = "n",required = true) String name,
                                    @PathVariable("l") String limit,
                                    @PathVariable("t") String type,
                                    @PathVariable("o") String offset
                                    ) throws IOException {
        return new Result(ResultEnmu.SUCCESS.getCode(),ResultEnmu.SUCCESS.getMsg(),musicService.searchByMusicName(name,limit,type,offset));
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
     * 根据专辑的 id 获取专辑内的歌曲
     * @param id  专辑 id
     * @return
     */
    @RequestMapping(value = "/album/{id}",method = RequestMethod.GET)
    public String getAlbumById(@PathVariable("id") String id) {
        return musicService.getAlbumById(id);
    }


    private long getSingerIdByName(String name) {
        return 10557;
    }

    @RequestMapping(value = "/artist",method = RequestMethod.GET)
    public String getDfs() throws IOException {
        return musicService.parseJson();
    }
}