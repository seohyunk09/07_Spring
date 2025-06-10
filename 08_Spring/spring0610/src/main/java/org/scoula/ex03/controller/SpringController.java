package org.scoula.ex03.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Log4j2
public class SpringController {
    // 클래스 레벨 "/sample"+ 메서드 레벨" " -> "/sample" URL 요청 매핑
    // 단, GET,POST  요청만 매핑

    @RequestMapping(value="", method ={RequestMethod.GET, RequestMethod.POST})
    public void basic(){
        log.info("[GET] /sample 요청 처리됨");
    }

  @RequestMapping(value="/basic2", method ={RequestMethod.GET, RequestMethod.POST})
    public void basic2(){
        log.info("[GET] /sample/basic2 요청 처리됨");
    }
    //"sample/board/{id}"  요청 매핑, method 관계없음
    //-{id} : 해당 위치에 존재하는 URL 값을 "id" 라고 인식

@RequestMapping("/board/{id:[0-9]+}")// id  자리가 정수인 경우
    public void selectBoard(@PathVariable("id") long id){
        log.info("입력된 id:"+id);

}
}
