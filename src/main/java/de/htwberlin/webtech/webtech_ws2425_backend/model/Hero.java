package de.htwberlin.webtech.webtech_ws2425_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Hero {
    private int id;
    private String name;
    private String affiliation;
    private Double heightInM;
}
