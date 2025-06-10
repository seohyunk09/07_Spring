package org.scoula.ex03.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller                    // Spring MVC 컨트롤러임을 선언 - 컴포넌트 스캔 대상
@RequestMapping("/sample")     // 클래스 레벨 - 모든 메서드의 기본 URL 경로 설정
@Log4j2                        // 로깅을 위한 Lombok 어노테이션
public class SampleController {

    @RequestMapping("")        // 메서드 레벨 - 세부 경로 ("" = 기본 경로만 사용)
    public void basic() {      // void 리턴 = RequestMapping과 동일한 이름의 JSP 자동 매핑
        log.info("basic............");
        // /sample 요청 시 /WEB-INF/views/sample.jsp로 forward
    }

    @GetMapping("/ex02-advanced")        // 메서드 레벨 - 세부 경로 ("" = 기본 경로만 사용)
    public String ex02Advanced(    // void 리턴 = RequestMapping과 동일한 이름의 JSP 자동 매핑
        @RequestParam(value = "name",required = false,defaultValue = "익명" ) String name,
        @RequestParam(value="age",required = true) int age){
        return  "sample/ex02";


    }

// ModelAttribute를 이용해서 page 값 Model에 추가하기


    @GetMapping("/ex02Array")
    public String ex02Array(    // void 리턴 = RequestMapping과 동일한 이름의 JSP 자동 매핑
                                @RequestParam(value = "name",required = false,defaultValue = "익명" ) String name,
                                @RequestParam(value="age",required = true) int age){
        return  "sample/ex02";


    }

}