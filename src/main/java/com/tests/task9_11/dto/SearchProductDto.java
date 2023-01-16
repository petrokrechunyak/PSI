package com.tests.task9_11.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
public class SearchProductDto {

    @Min(1)
    private Double price;

    @Min(1)
    private Long categoryId;

    private int page;

    private int size;


}
