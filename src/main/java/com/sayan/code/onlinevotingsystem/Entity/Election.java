package com.sayan.code.onlinevotingsystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sayan.code.onlinevotingsystem.ENUMS.Status;
import com.sayan.code.onlinevotingsystem.ENUMS.VoteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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
    private VoteType type;

    private Date start_date;

    private Date end_date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    private Date created_at;

    @OneToMany(mappedBy = "election_id")
    @JsonIgnore
    private List<Candidate> candidates;
}
