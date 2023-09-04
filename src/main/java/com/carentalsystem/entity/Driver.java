package com.carentalsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Driver {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String contactNo;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();
}
