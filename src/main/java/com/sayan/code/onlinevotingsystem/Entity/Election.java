package com.sayan.code.onlinevotingsystem.Entity;

import com.sayan.code.onlinevotingsystem.ENUMS.Role;
import com.sayan.code.onlinevotingsystem.ENUMS.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Election {
    @Id
    private String election_id;

    @Enumerated(EnumType.STRING)
    private Role type;

    private Date start_date;
    private Date end_date;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Date created_at;

    @OneToMany(mappedBy = "election_id")
    private List<Candidate> candidates;
}
