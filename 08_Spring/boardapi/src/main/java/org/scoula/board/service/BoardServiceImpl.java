package org.scoula.board.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Log4j2                      // 로깅
@Service                     // Service 계층 컴포넌트 + Bean 등록
@RequiredArgsConstructor     // final 필드 생성자 주입
public class BoardServiceImpl implements BoardService {

  private final BoardMapper boardMapper;  // Mapper 의존성 주입

  // 파일 저장될 디렉토리 경로
  private final static String BASE_DIR = "c:/upload/board";


  // 목록 조회 서비스
  @Override
  public List<BoardDTO> getList() {
    log.info("getList..........");

    return boardMapper.getList().stream()    // List<BoardVO> → Stream<BoardVO>
            .map(BoardDTO::of)               // Stream<BoardVO> → Stream<BoardDTO>
            .toList();                       // Stream<BoardDTO> → List<BoardDTO>
  }

  // 단일 조회 서비스
  @Override
  public BoardDTO get(Long no) {
    log.info("get......" + no);

    BoardVO vo = boardMapper.get(no);               // DB에서 VO 조회
    BoardDTO dto = BoardDTO.of(vo);                 // VO → DTO 변환

    return Optional.ofNullable(dto)                 // null 안전성 처리
            .orElseThrow(NoSuchElementException::new);  // 없으면 예외 발생
  }



  // 게시글 등록 서비스
  @Transactional  // 여러 DB 작업을 하나의 트랜잭션으로 처리
  @Override
  public void create(BoardDTO dto) {

    // 1. 게시글 등록
    log.info("create......" + dto);
    BoardVO vo = dto.toVo();         // DTO → VO 변환
    boardMapper.create(vo);            // DB에 저장
    dto.setNo(vo.getNo());           // 생성된 PK를 DTO에 설정

    // 2. 첨부파일 처리
    List<MultipartFile> files = dto.getFiles();
    if (files != null && !files.isEmpty()) {
      upload(vo.getNo(), files);  // 게시글 번호가 필요하므로 게시글 등록 후 처리
    }
  }



  // 게시글 수정 서비스
  @Override
  public boolean update(BoardDTO board) {
    log.info("update......" + board);

    int affectedRows = boardMapper.update(board.toVo());  // 영향받은 행 수 반환
    return affectedRows == 1;                        // 1개 행이 수정되면 성공
  }


  // 게시글 삭제 서비스
  @Override
  public boolean delete(Long no) {
    log.info("delete...." + no);

    int affectedRows = boardMapper.delete(no);     // 삭제된 행 수 반환
    return affectedRows == 1;                 // 1개 행이 삭제되면 성공
  }


  /* 파일 첨부 관련 메서드 추가 */

  // 첨부파일 단일 조회
  @Override
  public BoardAttachmentVO getAttachment(Long no) {
    return boardMapper.getAttachment(no);
  }

  // 첨부파일 삭제
  @Override
  public boolean deleteAttachment(Long no) {
    return boardMapper.deleteAttachment(no) == 1;
  }



  /**
   * 파일 업로드 처리 (private 메서드)
   * @param bno 게시글 번호
   * @param files 업로드할 파일 목록
   */
  private void upload(Long bno, List<MultipartFile> files) {
    for (MultipartFile part : files) {
      // 빈 파일은 건너뛰기
      if (part.isEmpty()) continue;

      try {
        // 파일을 서버에 저장
        String uploadPath = UploadFiles.upload(BASE_DIR, part);

        // 첨부파일 정보를 DB에 저장
        BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
        boardMapper.createAttachment(attach);

      } catch (IOException e) {
        // @Transactional이 감지할 수 있도록 RuntimeException으로 변환
        throw new RuntimeException(e);
      }
    }
  }

}