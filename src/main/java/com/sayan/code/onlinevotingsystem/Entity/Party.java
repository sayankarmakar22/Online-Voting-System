package com.sayan.code.onlinevotingsystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Party {
    @Id
    private String party_id;

    @Column(unique=true, nullable=false,length = 25)
    private String party_name;


}
