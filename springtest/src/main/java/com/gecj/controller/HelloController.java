package com.gecj.controller;

import lombok.Data;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }

    @Data
    static class Result {
        private final int left;
        private final int right;
        private final long answer;
    }

    // SQL sample
    @RequestMapping("calc")
    Result calc(@RequestParam int left, @RequestParam int right) {
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("left", left)
                .addValue("right", right);
        return jdbcTemplate.queryForObject("SELECT :left + :right AS answer", source,
                (rs, rowNum) -> new Result(left, right, rs.getLong("answer")));
    }

    @Autowired
    EventBus eventBus;
    @Autowired
    AsyncEventBus asyncEventBus;
    @RequestMapping("/testEventBus")
    public Object testEventBus(String msg) {
        System.err.println("ThreadID:"+Thread.currentThread().getId());
        eventBus.post(msg);
        asyncEventBus.post(msg);
        try {
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        return "success";
    }
}
