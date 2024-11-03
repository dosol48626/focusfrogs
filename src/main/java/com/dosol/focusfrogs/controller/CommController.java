package com.dosol.focusfrogs.controller;

import com.dosol.focusfrogs.domain.Comm;
import com.dosol.focusfrogs.domain.User;
import com.dosol.focusfrogs.dto.CommDTO;
import com.dosol.focusfrogs.dto.CustomUserDetails;
import com.dosol.focusfrogs.repository.CommRepository;
import com.dosol.focusfrogs.repository.UserRepository;
import com.dosol.focusfrogs.service.CommService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comm")
@Log4j2
public class CommController {
    private final CommService commService;
    private final ModelMapper modelMapper;

    @GetMapping("/main")
    public void commP(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {

        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("role", role);


//        List<Comm> comm = commService.readAll();
//        model.addAttribute("comms", comm);
        //이거를 주석해라고?
        List<Comm> comms = commService.readByUserId(userDetails.getUser().getId());
        model.addAttribute("comms", comms);
    }

    @GetMapping("/register")
    public void registerGet() {
    }

    @PostMapping("/register")
    public String registerPost(CommDTO commDTO,
                               //@AuthenticationPrincipal CustomUserDetails userDetails,
                               RedirectAttributes redirectAttributes) {

        //commDTO.setUsername(userDetails.getUser().getUsername());
        //commDTO.setUser_id(userDetails.getUser().getId());

        commService.register(commDTO);
        return "redirect:/comm/main";
    }
}
