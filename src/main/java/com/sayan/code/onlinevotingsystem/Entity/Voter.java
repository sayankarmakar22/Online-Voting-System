package com.sayan.code.onlinevotingsystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sayan.code.onlinevotingsystem.ENUMS.ActiveStatus;
import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voter")
public class Voter {

    @Id
    private String epic_id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 12)
    private String dob;

    @Column(name = "phone_number",unique = true,length = 13)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private ActiveStatus active;

    @Column(nullable = false,length = 300)
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "vote_status_id")
    private VoteStatus vote_id;


    @ManyToOne
    @JoinColumn(name = "constituency_id")
    @JsonIgnore
    private Constituency constituency;


}
