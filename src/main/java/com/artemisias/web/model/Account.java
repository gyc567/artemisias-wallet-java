package com.artemisias.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {


    private Long entityId;


    private String number;


    private String name;


}
