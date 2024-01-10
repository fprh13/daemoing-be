package com.daemoing.daemo.repository;

import com.daemoing.daemo.domain.Board;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {


    @Query("select b from Board b left join fetch b.user where b.id = :id")
    Optional<Board> findBoardAndUserById(@Param("id") Long id);

    Page<Board> findPageBy(Pageable pageAble);

}
