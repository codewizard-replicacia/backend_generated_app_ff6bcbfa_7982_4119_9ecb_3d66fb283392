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

@Entity(name = "Trip")
@Table(name = "\"Trip\"", schema =  "\"generated_app\"")
@Data
                        
public class Trip {
	public Trip () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"TripId\"", nullable = false )
  private Integer tripId;
	  
  @Column(name = "\"Name\"", nullable = true )
  private String name;
  
	  
  @Column(name = "\"Budget\"", nullable = false )
  private Float budget;
  
	  
  @Column(name = "\"Description\"", nullable = true )
  private String description;
  
	  
	@Column(name = "\"Tags\"")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(schema = "\"generated_app\"", name = "\"TripTags\"",joinColumns = @JoinColumn(name = "\"TripId\""))
    private List<String> tags = new ArrayList<>();
	  
  @Column(name = "\"StartsAt\"")
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date startsAt;  
  
	  
  @Column(name = "\"EndsAt\"")
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date endsAt;  
  
	  
  @Column(name = "\"StartTime\"")
  @Temporal(value = TemporalType.TIME)
  private Date startTime;  
  
	  
  @Column(name = "\"EndTime\"")
  @Temporal(value = TemporalType.TIME)
  private Date endTime;  
  
  
  
  
   
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"TripPlanItems\"",
            joinColumns = @JoinColumn( name="\"PlanItemId\""),
            inverseJoinColumns = @JoinColumn( name="\"PlanItems\""), schema = "\"generated_app\"")
private List<PlanItem> planItems = new ArrayList<>();
  
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
@JoinTable(
        name="\"TripTravellers\"",
        joinColumns = @JoinColumn( name="\"TripId\""),
        inverseJoinColumns = @JoinColumn(name = "\"UserName\""),schema = "\"generated_app\"")
private List<Person> travellers = new ArrayList<>();
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "Trip [" 
  + "TripId= " + tripId  + ", " 
  + "Name= " + name  + ", " 
  + "Budget= " + budget  + ", " 
  + "Description= " + description  + ", " 
  + "Tags= " + tags  + ", " 
  + "StartsAt= " + startsAt  + ", " 
  + "EndsAt= " + endsAt  + ", " 
  + "StartTime= " + startTime  + ", " 
  + "EndTime= " + endTime 
 + "]";
	}
	
}