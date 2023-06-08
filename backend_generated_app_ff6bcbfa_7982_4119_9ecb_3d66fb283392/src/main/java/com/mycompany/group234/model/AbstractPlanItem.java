package com.mycompany.group234.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity(name = "AbstractPlanItem")
@Table(name = "\"AbstractPlanItem\"", schema =  "\"generated_app\"")
@Data
@Inheritance
@DiscriminatorColumn(name = "\"DType\"")
public abstract class AbstractPlanItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"PlanItemId\"")
    protected Integer planItemId;

	@Column(name = "\"DType\"", length = 1, insertable = false, updatable = false, nullable = false)
	protected String dType;

}