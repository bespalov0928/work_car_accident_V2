package ru.work.work_car_accident_V2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Entity
//@Table(name = "authorities")
public class Authority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authority;
}
