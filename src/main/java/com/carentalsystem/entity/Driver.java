package com.carentalsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Driver implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String contactNo;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();
}
