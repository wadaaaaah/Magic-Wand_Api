package com.accenture.magicwand.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "magic_wand")
@Getter
@Setter
public class MagicWand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String narrative;

    private int age_limit;

    private Long stock;

}
