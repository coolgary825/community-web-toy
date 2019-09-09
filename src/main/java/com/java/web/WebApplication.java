package com.java.web;

import com.java.web.domain.Board;
import com.java.web.domain.User;
import com.java.web.domain.enums.BoardType;
import com.java.web.repository.BoardRepository;
import com.java.web.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception{
        return (args) -> {
            User user = userRepository.save(new User("havi","test","havi@gamail.com", LocalDateTime.now()));

            IntStream.range(1, 200).forEach(index ->
                    boardRepository.save(new Board("게시글"+index,"순서"+index,"컨텐츠", BoardType.free,LocalDateTime.now(),LocalDateTime.now(),user))
                    );
        };
    }
}
