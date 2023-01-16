package com.tests.task9_11.dto;

import com.tests.task9_11.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class SaveProductDto {

    @NotBlank
    private String name;

    @Min(1)
    private Double price;

    private String description;

    @NotNull
    private Category category;


}
