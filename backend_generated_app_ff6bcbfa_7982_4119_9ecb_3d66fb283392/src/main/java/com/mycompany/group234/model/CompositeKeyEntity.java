package com.mycompany.group234.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "CompositeKeyEntity")
@Table(schema = "\"Trippin\"", name = "\"CompositeKeyEntity\"")
@Data
@IdClass(value = CompositePrimaryKey.class)
public class CompositeKeyEntity {
    @Id
    @Column(name = "\"Code\"", length = 10)
    private String code;

    @Id
    @Column(name = "\"CodeID\"", length = 10)
    private Integer codeID;

    @Id
    @Column(name = "\"DivisonCode\"", length = 10)
    private String divisonCode;

}
