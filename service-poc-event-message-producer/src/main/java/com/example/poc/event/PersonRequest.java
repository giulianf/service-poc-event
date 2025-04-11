package com.example.poc.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest implements Serializable {
    private Long id;
    private Long carteNb;
    private String requestId;

}
