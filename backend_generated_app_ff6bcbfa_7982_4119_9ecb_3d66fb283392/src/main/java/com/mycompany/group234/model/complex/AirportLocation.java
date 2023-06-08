package com.mycompany.group234.model.complex;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class AirportLocation {
  public AirportLocation () {}
	
  @Column(name = "\"Loc\"", nullable = true)
  private String loc;
}
