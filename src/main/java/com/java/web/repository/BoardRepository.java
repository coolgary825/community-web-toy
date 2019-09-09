package com.java.web.repository;

import com.java.web.domain.Board;
import com.java.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
