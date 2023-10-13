package com.busanit.springmvc.controller;

import com.busanit.springmvc.dto.MemberForm;
import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.entity.Member;
import com.busanit.springmvc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {

    // @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/members/join")
    public String join(MemberForm dto) {
        log.info(dto.toString());
        Member member = dto.toEntity();
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        memberRepository.save(member);
        return "redirect:/members" + saved.getId();
    }

    // Read
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id : " + id);
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",member);
        return "members/show";
    }
    // READ ALL
    @GetMapping("/members")
    public String show(Model model){
        Iterable<Member> all = memberRepository.findAll();
        model.addAttribute("memberList",all);
        return "members/index";
    }
    public void initData() {
        // 임시 mock data
        memberRepository.save(new Member(1L, "제목1", "내용1"));
        memberRepository.save(new Member(2L, "제목2", "내용2"));
        memberRepository.save(new Member(3L, "제목3", "내용3"));
    }

    // UPDATE
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",member);
        return "members/edit";
    }
    @PostMapping("members/{id}/update")
    public String update(MemberForm dto, @PathVariable Long id){
        log.info(dto.toString());
        Member member = dto.toEntity();
        Member target = memberRepository.findById(id).orElse(null);
        if (target != null){
            member.setId(id);
            memberRepository.save(member);
        }
        return "redirect:/members/" + id;
    }
}

