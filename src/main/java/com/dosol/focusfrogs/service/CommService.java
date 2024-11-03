package com.dosol.focusfrogs.service;

import com.dosol.focusfrogs.domain.Comm;
import com.dosol.focusfrogs.dto.CommDTO;
import com.dosol.focusfrogs.repository.CommRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommService {
    private final CommRepository commRepository;
    private final ModelMapper modelMapper;

    public Long register(CommDTO commDTO) {
        Comm comm = modelMapper.map(commDTO, Comm.class);
        Long num = commRepository.save(comm).getNum();
        return num;
    }

    public CommDTO readOne(Long num) {
        Optional<Comm> result = commRepository.findById(num);
        Comm comm = result.orElseThrow();
        comm.updateVisitCount();
        commRepository.save(comm);
        CommDTO commDTO = modelMapper.map(comm, CommDTO.class);
        return commDTO;
    }

    public void modify(CommDTO commDTO) {
        Optional<Comm> result = commRepository.findById(commDTO.getNum());

        Comm comm = result.orElseThrow();
        comm.change(commDTO.getTitle(), comm.getContent());
        commRepository.save(comm);
    }

    public void remove(Long num) {
        commRepository.deleteById(num);
    }

//    public List<Comm> readAll() {
//        List<Comm> result = commRepository.findAll();
//        return result;
//    } 이렇게 하니까 그냥 어떤 글이든 다 들고오네. 내가 로그인 한거만 가져와야하는데

    public List<Comm> readByUserId(Long userId) {
        return commRepository.findByUserId(userId); // 로그인한 사용자의 게시글만 가져오기
    }
    //근데 이 list를 쓰면 로그인한 사람의 게시글을 가져오겠지.
}