package com.axc.gsm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String value;
    private String actions;
    private String lastUpdateDateTime;
    private String lastUpdateUser;
    private String history;

}
