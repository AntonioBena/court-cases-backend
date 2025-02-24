package com.interview.court.cases.model.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T>{
    private List<T> content;
    private int pageIndex;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
}