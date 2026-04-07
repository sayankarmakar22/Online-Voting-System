package com.sayan.code.onlinevotingsystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Candidate> candidateList;

    @OneToMany(mappedBy = "constituency")
    private List<Voter> voters;


}
