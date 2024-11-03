package com.dosol.focusfrogs.repository;

import com.dosol.focusfrogs.domain.Comm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommRepository extends JpaRepository<Comm, Long> {

}
