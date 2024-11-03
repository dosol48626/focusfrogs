package com.dosol.focusfrogs.repository;

import com.dosol.focusfrogs.domain.Comm;
import com.dosol.focusfrogs.domain.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class CommRepositoryTests {
    @Autowired
    private CommRepository commRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert() {
        User user = userRepository.findById(3L).orElseThrow(() -> new IllegalArgumentException("User not found"));

        IntStream.rangeClosed(1, 5).forEach(i -> {
            Comm comm = Comm.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .user(user)
                    .username(user.getUsername())
                    .build();

            commRepository.save(comm);
        });
    }

}
