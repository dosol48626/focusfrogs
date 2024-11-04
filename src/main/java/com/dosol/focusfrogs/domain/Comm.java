package com.dosol.focusfrogs.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    //private String username;

    //private Long user_id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private int visitcount;

    public void updateVisitCount(){
        this.visitcount++;
    }

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
    @ManyToOne
    private User user;
}
