
package com.example.SiteMatrimonial.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String city;
    private String status;
    private String occupation;
    private String shortDescription;
    private String religion;
    private Boolean divorced;
    private Boolean kids;
    private Boolean male;
    private Boolean female;



    @JsonIgnore
    @OneToOne
    private User user;

    public Profile(Integer id, String name, Integer age, String city, String status, String occupation, String shortDescription, String religion, Boolean divorced, Boolean kids, Boolean male, Boolean female) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.status = status;
        this.occupation = occupation;
        this.shortDescription = shortDescription;
        this.religion = religion;
        this.divorced = divorced;
        this.kids = kids;
        this.male = male;
        this.female = female;
    }
}

