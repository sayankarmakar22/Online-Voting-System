package com.sayan.code.onlinevotingsystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class Voter {

    @Id
    private String epic_id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 12)
    private String dob;

    @Column(unique = true,length = 13)
    private String phone_number;

    @Column(nullable = false,length = 300)
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    /* Cardinalities */
    @OneToOne
    @JoinColumn(name = "vote_id")
    private Vote vote_id;

    @ManyToOne
    @JoinColumn(name = "constituency_id")
    @JsonIgnore
    private Constituency constituencies;


}
