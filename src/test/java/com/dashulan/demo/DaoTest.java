package com.dashulan.demo;

import com.dashulan.demo.dao.ConversationDao;
import com.dashulan.demo.dao.MessageDao;
import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.MakeFriends;
import com.dashulan.demo.entity.dao.Message;
import com.dashulan.demo.entity.dao.User;
import com.dashulan.demo.service.ConversationService;
import com.dashulan.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class DaoTest {



}
