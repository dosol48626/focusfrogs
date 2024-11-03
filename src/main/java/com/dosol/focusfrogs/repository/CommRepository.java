package com.dosol.focusfrogs.repository;

import com.dosol.focusfrogs.domain.Comm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommRepository extends JpaRepository<Comm, Long> {
    List<Comm> findByUserId(Long userId);
    //특정 사용자 ID의 게시글을 조회하는 메서드
}
