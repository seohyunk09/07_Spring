package org.scoula.board.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data                    // getter, setter, toString, equals, hashCode 생성
@NoArgsConstructor       // 기본 생성자
@AllArgsConstructor      // 모든 필드 생성자
@Builder                 // 빌더 패턴
public class BoardDTO {
    @ApiModelProperty(value = " 업 로 드 파 일 목 록")
    List<MultipartFile> files = new ArrayList<>();

    @ApiModelProperty(value = " 게 시글 ID", example = "1")

    private Long no;
    @ApiModelProperty(value = " 제 목")

    private String title;
    @ApiModelProperty(value = " 글 본 문")

    private String content;
    @ApiModelProperty(value = " 작 성 자")

    private String writer;
    @ApiModelProperty(value = " 등 록 일")

    private Date regDate;
    @ApiModelProperty(value = " 수 정 일")

    private Date updateDate;


    @ApiModelProperty(value = " 첨 부 파 일 목 록")
    private List<BoardAttachmentVO> attaches;

    // 실제 업로드된 파일들 (form에서 전송됨)--create
/*
    private List<MultipartFile> files = new ArrayList<>();*/

    /**
     * BoardVO를 BoardDTO로 변환하는 정적 팩토리 메서드
     *
     * @param vo 변환할 BoardVO 객체
     * @return 변환된 BoardDTO 객체 (vo가 null이면 null 반환)
     */

    public static BoardDTO of(BoardVO vo) {
        return vo == null ? null : BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .regDate(vo.getRegDate())
                .updateDate(vo.getUpdateDate())
                .attaches(vo.getAttaches())
                .regDate(vo.getRegDate())
                .build();
    }

    /**
     * 현재 BoardDTO를 BoardVO로 변환
     * @return 변환된 BoardVO 객체
     */
    public BoardVO toVo() {
        return BoardVO.builder()
                .no(no)                    // this.no와 동일
                .title(title)              // this.title과 동일
                .content(content)
                .writer(writer)
                .regDate(regDate)
                .updateDate(updateDate)
                .attaches(attaches)
                .regDate(regDate)
                .build();
    }
}