package com.example.poc.event.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    @Nullable
    private Long id;
    @Nullable
    private String name;
    @Nullable
    private Long carteNb;

}
