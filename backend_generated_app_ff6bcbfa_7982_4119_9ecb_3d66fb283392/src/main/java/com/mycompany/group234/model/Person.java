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

@Entity(name = "Person")
@Data
@DiscriminatorValue(value = "1")
                        
public class Person extends AbstractPerson {
  public Person () {
    super();
    dType = "1";
  }
	  
	  
  @Column(name = "\"FirstName\"", nullable = false )
  private String firstName;
  
	  
  @Column(name = "\"LastName\"", nullable = true , length = 26 )
  private String lastName;
  
	  
  @Column(name = "\"Income\"", nullable = true )
  private Double income;
  
	  
  @Column(name = "\"DateOfBirth\"")
  @Temporal(value = TemporalType.DATE)
  private Date dateOfBirth;  
  
	  
  @Column(name = "\"Photo\"", nullable = true )
  private byte[] photo;
  
	  
  @Column(name = "\"MiddleName\"", nullable = true )
  private String middleName;
  
	  
  @Column(name = "\"Gender\"", nullable = false)
  @Enumerated(value = EnumType.ORDINAL)
  @Convert(converter = PersonGenderConverter.class)
  private PersonGender gender;
  
	  
  @Column(name = "\"Age\"", nullable = true )
  private Long age;
  
	  
	@Column(name = "\"Emails\"")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(schema = "\"generated_app\"", name = "\"PersonEmails\"",joinColumns = @JoinColumn(name = "\"UserName\""))
    private List<String> emails = new ArrayList<>();
	  
	@Column(name = "\"AddressInfo\"")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(schema = "\"generated_app\"", name = "\"PersonAddressInfo\"",joinColumns = @JoinColumn(name = "\"UserName\""))
    private List<Location> addressInfo = new ArrayList<>();
	  
  @Embedded
  @Column(name = "\"HomeAddress\"")
  @AttributeOverrides({
            	@AttributeOverride(name = "address", column = @Column(name = "\"HomeAddress_Address\"")) ,
            	@AttributeOverride(name = "city.name", column = @Column(name = "\"HomeAddress_City_Name\"")) ,
            	@AttributeOverride(name = "city.countryRegion", column = @Column(name = "\"HomeAddress_City_CountryRegion\"")) ,
            	@AttributeOverride(name = "city.region", column = @Column(name = "\"HomeAddress_City_Region\"")) ,
            	@AttributeOverride(name = "code", column = @Column(name = "\"HomeAddress_Code\""))  }) 
  private Location homeAddress;
  
	  
  @Column(name = "\"FavoriteFeature\"", nullable = false)
  @Enumerated(value = EnumType.ORDINAL)
  @Convert(converter = FeatureConverter.class)
  private Feature favoriteFeature;
  
	  
		@Column(name = "\"Features\"")
	@ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(schema = "\"generated_app\"", name = "\"PersonFeatures\"",joinColumns = @JoinColumn(name = "\"UserName\""))
    @Convert(converter = FeatureConverter.class)
    private List<Feature> features = new ArrayList<>();
  
  
  
   
  
  
	@ManyToMany(mappedBy = "travellers")
private List<Trip> trips;
  
  
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
private List<Document> attachments;
  
  
  
  
  
  @Override
  public String toString() {
	return "Person [" 
  + "UserName= " + userName  + ", " 
  + "FirstName= " + firstName  + ", " 
  + "LastName= " + lastName  + ", " 
  + "Income= " + income  + ", " 
  + "DateOfBirth= " + dateOfBirth  + ", " 
  + "Photo= " + photo  + ", " 
  + "MiddleName= " + middleName  + ", " 
  + "Gender= " + gender  + ", " 
  + "Age= " + age  + ", " 
  + "Emails= " + emails  + ", " 
  + "AddressInfo= " + addressInfo  + ", " 
  + "HomeAddress= " + homeAddress  + ", " 
  + "FavoriteFeature= " + favoriteFeature  + ", " 
  + "Features= " + features 
 + "]";
	}
	
}