package com.dashulan.demo.web;

import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.dao.User;
import com.dashulan.demo.entity.dao.UserAndUer;
import com.dashulan.demo.entity.dao.UserAndUserKey;
import com.dashulan.demo.entity.vo.ResponseData;
import com.dashulan.demo.entity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.*;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Controller
@RequestMapping("/user")
public class UserController {
    UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping(value = "/find/{name}")
    public ResponseEntity<String> find(@PathVariable  String name) {
        Optional<User> user = userDao.findByName(name);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get().getId().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("用户不存在", HttpStatus.OK);
    }

    @RequestMapping("/add/{me}/{friend}")
    public ResponseEntity<String> add(@PathVariable String me, @PathVariable String friend) {
        Optional<User> userA= userDao.findById(Long.decode(me));
        Optional<User> userB = userDao.findById(Long.decode(friend));
        UserAndUer userAndUer = new UserAndUer();
        if (userA.isPresent() && userB.isPresent()) {
            User meUser = userA.get();
            User friUser = userB.get();
            UserAndUserKey key =  new UserAndUserKey();
            key.setUserAId(meUser.getId());
            key.setUserBId(friUser.getId());
            userAndUer.setId(key);
            userAndUer.setUserA(meUser);
            userAndUer.setUserB(friUser);
            meUser.getFriendsFromMe().add(userAndUer);
            userDao.save(meUser);
            return new ResponseEntity<>("添加成功", HttpStatus.OK);
        }
        return new ResponseEntity<>("添加失败", HttpStatus.OK);
    }

    @GetMapping("/friends/{name}")
    public ResponseEntity<ResponseData> getAllFriends(@PathVariable String name) {
        Optional<User> user = userDao.findByName(name);
        if (user.isPresent()) {
            List<UserVo> friends = userNameId(user.get().getFriendsFromMe());
            return new ResponseEntity<>(ResponseData.ok(friends), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseData.error(null, "错误"), HttpStatus.OK);
    }

    private List<UserVo> userNameId(Set<UserAndUer> uerSet){
        List<UserVo> list = new ArrayList<>();
        Iterator<UserAndUer> iterator =  uerSet.iterator();
        while (iterator.hasNext()) {
            UserAndUer userAndUer = iterator.next();
            UserVo userVo = new UserVo();
            userVo.setId(userAndUer.getUserB().getId());
            userVo.setName(userAndUer.getUserB().getName());
            list.add(userVo);
        }
        return list;
    }
}
