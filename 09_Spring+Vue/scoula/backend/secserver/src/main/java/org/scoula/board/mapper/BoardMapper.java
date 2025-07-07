package org.scoula.board.mapper;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.common.pagination.PageRequest;

import java.util.List;

public interface BoardMapper {
    // 게시글 목록 조회 (어노테이션 방식)
    //@Select("select * from tbl_board order by no desc")

    int getTotalCount();
    List<BoardVO> getPage(PageRequest pageRequest);
    public List<BoardVO> getList();

    // 단일 게시글 조회
    public BoardVO get(Long no);

    // 게시글 등록
    public void create(BoardVO board);

    // 게시글 수정
    public int update(BoardVO board);

    // 게시글 삭제
    public int delete(Long no);

    public void createAttachment(BoardAttachmentVO attach);

    // 특정 게시글의 첨부 파일 목록 조회
    public List<BoardAttachmentVO> getAttachmentList(Long bno);

    // 특정 첨부 파일 1개 조회
    public BoardAttachmentVO getAttachment(Long no);

    // 특정 첨부 파일 1개 삭제
    public int deleteAttachment(Long no);
}