package com.fashion.blog.week8task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FetchPostDto {
    private Integer postId;
    private String searchWord;
}
