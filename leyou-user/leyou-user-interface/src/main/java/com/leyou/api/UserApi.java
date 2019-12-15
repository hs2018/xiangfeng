package com.leyou.api;

import com.leyou.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


public interface UserApi {

    @GetMapping("query")
     User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );
}
