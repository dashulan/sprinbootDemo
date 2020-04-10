package com.dashulan.demo.web;

import com.dashulan.demo.chat.entity.User;
import com.dashulan.demo.chat.service.UserService;
import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.dao.UserNeedActiveDao;
import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.UserNeedActive;
import com.dashulan.demo.entity.vo.ActiveVo;
import com.dashulan.demo.entity.vo.ResponseData;
import com.dashulan.demo.entity.vo.SuggestName;
import com.dashulan.demo.entity.vo.UserVo;
import com.dashulan.demo.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/auth")
public class AuthController {
    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

/*
    @PostMapping("register")
    public ResponseEntity<UserVo> register(@RequestBody User user) {
        UserVo userVo =new UserVo();
        userVo.setStatus(UserVo.RegisterStatus.FAIL);
        Long id=0l;
        try {
            id = userService.userRegister(user);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(userVo, HttpStatus.OK);
        }

        Optional<User> successUser = userDao.findById(id);
        if (successUser.isPresent()) {
            User succUser = successUser.get();

            succUser.setCreated_at(LocalDateTime.now());
            succUser.setUpdated_at(LocalDateTime.now());
            userDao.save(succUser);

            userVo = userVo.fillINfo(succUser);
            userVo.setStatus(UserVo.RegisterStatus.SUCCESS);
        }
        return new ResponseEntity<>(userVo, HttpStatus.OK);
    }
*/

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody User user) {
       User u =  userService.findUser(user.getName());
        if (u == null) {
            return new ResponseEntity<>(ResponseData.error(null,"用户不存在"),HttpStatus.OK);
        }
        if (!u.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(ResponseData.error(null,"密码错误"), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseData.ok(u.getId()), HttpStatus.OK);
    }

    @GetMapping(path = "/suggest",params = "name")
    public ResponseEntity<String> suggestName(String name) {
        User user = userService.findUser(name);
        if (user == null) {
            return new ResponseEntity<>("用户名重复", HttpStatus.OK);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);

    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> active(@RequestBody User user, @PathParam("code")String code){
        boolean isActive = userService.activeUser(user.getPhone(),code);
        if (!isActive) {
            return  new ResponseEntity<>("验证码错误",HttpStatus.OK);
        }
        userService.addUser(user);
        return  new ResponseEntity<>(("注册成功"), HttpStatus.OK);

    }

    @GetMapping(path = "/code")
    public ResponseEntity<String> generateCode(String phone){
        boolean isSuccess =  userService.addUserWaitActive(phone);
        if (!isSuccess) {
            return new ResponseEntity<>("手机号已被使用",HttpStatus.OK);
        }
        return new ResponseEntity<>("请确认", HttpStatus.OK);
    }
}
