package com.sayan.code.onlinevotingsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Constituency")
public class Constituency {
    @Id
    private String constituency_id;

    @Column(unique = true,nullable = false,length = 40)
    private String constituency_name;

    @OneToMany(mappedBy = "constituency")
    private List<Candidate> candidateList;

    @ManyToMany(mappedBy = "constituencies")
    private List<User> users;


}
