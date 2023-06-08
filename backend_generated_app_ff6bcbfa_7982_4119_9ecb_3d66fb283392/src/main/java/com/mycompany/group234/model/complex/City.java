package com.mycompany.group234.model.complex;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class City {
  
	
  @Column(name = "\"Name\"", nullable = true)
  private String name;
	
  @Column(name = "\"CountryRegion\"", nullable = true)
  private String countryRegion;
	
  @Column(name = "\"Region\"", nullable = true)
  private String region;
}
