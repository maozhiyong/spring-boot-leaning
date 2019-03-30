package com.neo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable{

    private String name;

    private String pass;
}
