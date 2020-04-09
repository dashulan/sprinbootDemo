package com.dashulan.demo.web;

import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.dao.UserNeedActiveDao;
import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.User;
import com.dashulan.demo.entity.dao.UserNeedActive;
import com.dashulan.demo.entity.vo.ActiveVo;
import com.dashulan.demo.entity.vo.ResponseData;
import com.dashulan.demo.entity.vo.SuggestName;
import com.dashulan.demo.entity.vo.UserVo;
import com.dashulan.demo.service.ConversationService;
import com.dashulan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/auth")
public class AuthController {
    UserService userService;
    UserDao userDao;
    ConversationService conversationService;
    UserNeedActiveDao userNeedActiveDao;

    @Autowired
    public AuthController(UserService userService,UserDao userDao,ConversationService conversationService,UserNeedActiveDao dao) {
        this.userService = userService;
        this.userDao = userDao;
        this.conversationService = conversationService;
        this.userNeedActiveDao =dao;
    }

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

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody User user) {
        Optional<User> toLoginUser = userDao.findByName(user.getName());
        if (toLoginUser.isPresent()) {
            User exitsUsr = toLoginUser.get();
            if(user.getPassword().equals(exitsUsr.getPassword())){
//                List<Conversation> conversations = conversationService.getUserAllConversations(user.getId());
//                exitsUsr.getConversations().addAll(conversations);
                return new ResponseEntity<>(ResponseData.ok(exitsUsr.getId()), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(ResponseData.error(null,"密码错误"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ResponseData.error(null,"用户不存在"),HttpStatus.OK);
    }

    @GetMapping(path = "/suggest",params = "name")
    public ResponseEntity<SuggestName> suggestName(String name) {
        SuggestName suggestName = new SuggestName();
        suggestName.setStatus(SuggestName.NameState.OK);
        Optional<User> user = userDao.findByName(name);
        user.ifPresent(u->{
            suggestName.setStatus(SuggestName.NameState.REPEAT);
        });
        return new ResponseEntity<>(suggestName, HttpStatus.OK);
    }

    @PostMapping(path = "/active")
    public ResponseEntity<String> active(@RequestBody ActiveVo userActive){
        UserNeedActive userNeedActive =  userNeedActiveDao.findByPhone(userActive.getPhone());
        if (userNeedActive == null) {
            return new ResponseEntity<>("手机号不存在",HttpStatus.OK);
        }else {
            if (!userNeedActive.getCode().equals(userActive.getCode())) {
                return  new ResponseEntity<>("验证码错误",HttpStatus.OK);
            }else {
                userNeedActive.setActive(true);
                userNeedActiveDao.save(userNeedActive);
                User user = new User();
                user.setName(userActive.getName());
                user.setPassword(userActive.getPassword());
                user.setPhone(userActive.getPhone());
                user.setCreated_at(LocalDateTime.now());
                user.setUpdated_at(LocalDateTime.now());
                userDao.save(user);
                return new ResponseEntity<>(("注册成功"), HttpStatus.OK);
            }
        }
    }

    @GetMapping(path = "/code",params = "phone")
    public ResponseEntity<String> generateCode(String phone){
        UserNeedActive user = userNeedActiveDao.findByPhone(phone);
        UserNeedActive userNeedActive = Optional.ofNullable(user).orElseGet(()->new UserNeedActive());
        if(userNeedActive.isActive()){
            return new ResponseEntity<>("手机号已被使用",HttpStatus.OK);
        }else {
            userNeedActive.setPhone(phone);
            userNeedActive.setCreate_at(LocalDateTime.now());
            String code = UUID.randomUUID().toString().substring(0,6);
            System.out.println(code);
            userNeedActive.setCode(code);
            userNeedActiveDao.save(userNeedActive);
            return new ResponseEntity<>("请确认", HttpStatus.OK);
        }
    }


}
