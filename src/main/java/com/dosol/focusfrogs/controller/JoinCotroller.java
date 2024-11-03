package com.dosol.focusfrogs.controller;

import com.dosol.focusfrogs.dto.JoinDTO;
import com.dosol.focusfrogs.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class JoinCotroller {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP() {

        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {
        log.info("@@@@@@@@@@@@@@");
        log.info(joinDTO);

        //실패 시 어디로 가는지도 정해야함

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
