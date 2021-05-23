package com.hotel.app.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchRequest {
    private List<SearchCriteria> criteria;
    private Integer pageNumber = 0;
    private Integer pageItems = 20;
    private String sortBy;
    private Boolean ascending = false;
}
