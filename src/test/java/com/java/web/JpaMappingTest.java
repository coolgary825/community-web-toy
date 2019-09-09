package com.java.web;

import com.java.web.domain.Board;
import com.java.web.domain.User;
import com.java.web.domain.enums.BoardType;
import com.java.web.repository.BoardRepository;
import com.java.web.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static  org.hamcrest.MatcherAssert.assertThat;
import static  org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {

    private static final String  boradTestTitle = "테스트";
    private static final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init(){
        User user = userRepository.save(new User("havi","test",email, LocalDateTime.now()));
        boardRepository.save(new Board(boradTestTitle,"서브타이틀","컨텐츠", BoardType.free,LocalDateTime.now(),LocalDateTime.now(),user));

    }

    @Test
    public void 제대로_생성됐는지_테스트(){
        User user = userRepository.findByEmail(email);
        assertThat(user.getName(),is("havi"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(),is(email));

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle() , is(boradTestTitle));
        assertThat(board.getSubTitle() , is("서브타이틀"));
        assertThat(board.getContent() , is("컨텐츠"));
        assertThat(board.getBoardType() , is(BoardType.free));
    }
}
