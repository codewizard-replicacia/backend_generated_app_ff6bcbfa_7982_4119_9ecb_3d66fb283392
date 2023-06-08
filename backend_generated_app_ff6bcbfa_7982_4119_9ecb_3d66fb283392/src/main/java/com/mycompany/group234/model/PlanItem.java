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


 
import com.mycompany.group234.model.Person;
import com.mycompany.group234.model.Document;
import com.mycompany.group234.model.Airline;
import com.mycompany.group234.model.Airport;
import com.mycompany.group234.model.Trip;
import com.mycompany.group234.model.PlanItem;
import com.mycompany.group234.model.Event;
import com.mycompany.group234.model.PublicTransportation;
import com.mycompany.group234.model.Flight;
import com.mycompany.group234.model.Employee;
import com.mycompany.group234.model.Manager;
import com.mycompany.group234.model.complex.Location;
import com.mycompany.group234.model.complex.City;
import com.mycompany.group234.model.complex.AirportLocation;
import com.mycompany.group234.model.complex.EventLocation;
import com.mycompany.group234.enums.PersonGender;
import com.mycompany.group234.enums.Feature;
import com.mycompany.group234.converter.PersonGenderConverter;
import com.mycompany.group234.converter.FeatureConverter;
import com.mycompany.group234.converter.DurationConverter;
import com.mycompany.group234.converter.UUIDToByteConverter;
import com.mycompany.group234.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "PlanItem")
@Data
@DiscriminatorValue(value = "1")
                        
public class PlanItem extends AbstractPlanItem {
  public PlanItem () {
    super();
    dType = "1";
  }
	  
	  
  @Column(name = "\"ConfirmationCode\"", nullable = true )
  private String confirmationCode;
  
	  
  @Column(name = "\"StartsAt\"")
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date startsAt;  
  
	  
  @Column(name = "\"EndsAt\"")
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date endsAt;  
  
	  
  @Convert(converter = DurationConverter.class)
  @Column(name = "\"Duration\"", nullable = false )
  private Duration duration;
  
  
	@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "\"PlanItemTicket\"", referencedColumnName = "\"DocId\"", insertable = false, updatable = false)
private Document ticket;

@Column(name = "\"PlanItemTicket\"")
private String planItemTicket;
  
  
   
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "PlanItem [" 
  + "PlanItemId= " + planItemId  + ", " 
  + "ConfirmationCode= " + confirmationCode  + ", " 
  + "StartsAt= " + startsAt  + ", " 
  + "EndsAt= " + endsAt  + ", " 
  + "Duration= " + duration 
 + "]";
	}
	
}