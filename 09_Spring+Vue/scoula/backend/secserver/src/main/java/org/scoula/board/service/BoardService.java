package org.scoula.board.service;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;

import java.util.List;

public interface BoardService {

        Page<BoardDTO> getPage(PageRequest pageRequest);
        /**
         * 게시글 목록 조회
         * @return 게시글 DTO 목록
         */
        public List<BoardDTO> getList();

        /**
         * 특정 게시글 조회
         * @param no 게시글 번호
         * @return 게시글 DTO
         */
        public BoardDTO get(Long no);


        public BoardDTO create(BoardDTO board);
        public BoardDTO update(BoardDTO board);
        public BoardDTO delete(Long no);


        public BoardAttachmentVO getAttachment(Long no);
        public boolean deleteAttachment(Long no);
}