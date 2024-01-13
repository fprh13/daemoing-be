package com.daemoing.daemo.service;

import com.daemoing.daemo.domain.Board;
import com.daemoing.daemo.domain.User;
import com.daemoing.daemo.global.common.exception.CustomException;
import com.daemoing.daemo.repository.BoardRepository;
import com.daemoing.daemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.daemoing.daemo.service.dto.BoardDto.*;
import static com.daemoing.daemo.global.common.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * create
     */
    @Transactional
    public Long write(WriteDto writeDto) {
        User user = userRepository.findByLoginId(
                        SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return boardRepository.save(new Board(writeDto.getTitle(),writeDto.getContent(),user)).getId();
    }

    /**
     * update
     */
    @Transactional
    public Long update(Long id, UpdateDto updateDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));
        board.update(updateDto.getTitle(), updateDto.getContent());
        return board.getId();
    }

    /**
     * delete
     */
    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findBoardAndUserById(id)
                .orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));
        if (board.getUser().getLoginId()
                .equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            boardRepository.deleteById(id);
        } else {
            throw new CustomException(ACCESS_DENIED);
        }
    }

    /**
     * read one
     * 조회수 + 1
     */
    @Transactional
    public DetailDto detail(Long id) {
        Board board = boardRepository
                .findById(id).orElseThrow(() -> new CustomException(RESOURCE_NOT_FOUND));

        board.increaseView(board.getViewCount());

        return new DetailDto(board.getCreateBy(),
                board.getModifyBy(),
                board.getTitle(),
                board.getContent(),
                board.getViewCount(),
                board.getCreateDate(),
                board.getLastModifiedDate());
    }

    /**
     * read paging
     */
    public Page<PageDto> page(int id) {
        Page<Board> page = boardRepository
                .findPageBy(PageRequest.of(id, 10, Sort.by(Sort.Direction.DESC,"createDate")));
        return page.map(board -> new PageDto(
                board.getCreateBy(),
                board.getModifyBy(),
                board.getTitle(),
                board.getViewCount(),
                board.getCreateDate(),
                board.getLastModifiedDate()));
    }
}
