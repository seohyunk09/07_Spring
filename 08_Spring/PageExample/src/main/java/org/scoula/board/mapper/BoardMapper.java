package org.scoula.mapper;

import org.scoula.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

    public List<BoardVO> getList();
    public BoardVO get(Long no);
    public void create(BoardVO board);
    // 게시글 수정
    public int update(BoardVO board);

    // 게시글 삭제
    public int delete(Long no);
}
