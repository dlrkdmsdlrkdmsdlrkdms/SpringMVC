package com.busanit.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
    // HandlerMapping
    // 1. URL 요청(Request)을 접수, 기본 메소드 GET
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hello(Model model){
        // 2. 요청 주소에 맞는 메소드 수행
        // 3. 모델 객체 가져오기
        model.addAttribute("username","홍길동");   // 4. 모델 변수 등록
        return "greeting";  // 5. 뷰 템플리 페이지 반환(View Resolver)
    }

    // MVC 패턴 만들기
    // '/bye' 요청이 왔을 때, goodbye.mustache, 홍길동님 {{username}} 안녕히 가세요 구현 해보기

    @RequestMapping(value = "/bye",method = RequestMethod.GET)
    public String bye(Model model){
        model.addAttribute("username","홍길동");
        return "goodbye";
    }

}
