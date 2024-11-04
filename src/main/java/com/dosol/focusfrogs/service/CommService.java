package com.dosol.focusfrogs.service;

import com.dosol.focusfrogs.domain.Comm;
import com.dosol.focusfrogs.domain.User;
import com.dosol.focusfrogs.dto.CommDTO;
import com.dosol.focusfrogs.repository.CommRepository;
import com.dosol.focusfrogs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommService {
    private final CommRepository commRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public Long register(CommDTO commDTO, User user) {
        Comm comm = modelMapper.map(commDTO, Comm.class);
        comm.setUser(user);

        Long num = commRepository.save(comm).getNum();
        return num;
    }

//    public CommDTO readOne(Long num) {
//        Optional<Comm> result = commRepository.findById(num);
//        Comm comm = result.orElseThrow();
//        comm.updateVisitCount();
//        commRepository.save(comm);
//        CommDTO commDTO = modelMapper.map(comm, CommDTO.class);
//        return commDTO;
//    }

    public CommDTO readOne(Long num) {
        Optional<Comm> result = commRepository.findById(num);

        Comm comm = result.orElseThrow(() -> new NoSuchElementException("게시물이 존재하지 않습니다."));
        User user = userRepository.findById(comm.getUser().getId()).get();
        comm.setUser(comm.getUser());
        comm.updateVisitCount();
        commRepository.save(comm);
        CommDTO commDTO = modelMapper.map(comm, CommDTO.class);
        commDTO.setUsername(user.getUsername());
        log.info(commDTO);
        return commDTO;

    }

    public void modify(CommDTO commDTO, User user) {
        Optional<Comm> result = commRepository.findById(commDTO.getNum());

        Comm comm = result.orElseThrow();
        if (!comm.getUser().getUsername().equals(user.getUsername())) {
            return;
        }

        comm.change(commDTO.getTitle(), comm.getContent());
        commRepository.save(comm);
    }

    public void remove(Long num) {
        commRepository.deleteById(num);
    }

    public List<Comm> readAll() {
        List<Comm> comms = commRepository.findAll();
        return comms;
    }

//    public List<Comm> readByUserId(Long userId) {
//        return commRepository.findByUserId(userId); // 로그인한 사용자의 게시글만 가져오기
//    }
    //근데 이 list를 쓰면 로그인한 사람의 게시글을 가져오겠지.
}

