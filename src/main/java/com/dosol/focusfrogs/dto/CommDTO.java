package com.dosol.focusfrogs.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommDTO {
    private Long num;

    private String username;

    //private Long user_id;

    private String title;

    private String content;

    private int visitcount;
}
