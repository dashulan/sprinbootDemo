package com.dashulan.demo.chat.web;

import com.dashulan.demo.chat.entity.User;
import com.dashulan.demo.chat.service.UserService;
import com.dashulan.demo.chat.entity.vo.ResponseData;
import com.dashulan.demo.chat.entity.vo.UserVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/find/{information}")
    public ResponseEntity<ResponseData> find(@PathVariable  String information) {
        User user = userService.findUser(information);
        if (user == null) {
            return new ResponseEntity<>(ResponseData.error(null,"用户不存在"), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseData.ok(user.getId()), HttpStatus.OK);

    }

    @RequestMapping("/add/{from}/{to}")
    public ResponseEntity<ResponseData> add(@PathVariable String from, @PathVariable String to) {
        if (userService.askFriend(from, to)) {
            return new ResponseEntity<>(ResponseData.ok("请求成功"), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(ResponseData.error(null, "请求失败"), HttpStatus.OK);
        }
    }

    @GetMapping("/friends/{name}")
    public ResponseEntity<ResponseData> getAllFriends(@PathVariable String name) {
        List<User> users =  userService.findAllFriends(name);
        List<UserVo> userVoList = new ArrayList<>();
        users.forEach(user -> {
            userVoList.add(new UserVo(user));
        });
        return new ResponseEntity<>(ResponseData.ok(userVoList), HttpStatus.OK);
    }


}
