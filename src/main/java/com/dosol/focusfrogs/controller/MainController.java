package com.dosol.focusfrogs.controller;

import com.dosol.focusfrogs.domain.Comm;
import com.dosol.focusfrogs.domain.User;
import com.dosol.focusfrogs.dto.CustomUserDetails;
import com.dosol.focusfrogs.repository.CommRepository;
import com.dosol.focusfrogs.repository.UserRepository;
import com.dosol.focusfrogs.service.CommService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CommRepository commRepository;
    private final ModelMapper modelMapper;
    private final CommService commService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String mainP(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {


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

        List<Comm> comms = commService.readByUserId(userDetails.getUser().getId());
        model.addAttribute("comms", comms);

        return "main";
    }
}
