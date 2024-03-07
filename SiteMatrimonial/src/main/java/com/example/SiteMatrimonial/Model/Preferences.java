
package com.example.SiteMatrimonial.Model;

import com.example.SiteMatrimonial.Model.Chat;
import com.example.SiteMatrimonial.Model.Matchul;
import com.example.SiteMatrimonial.Model.QuizQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository

public class Preferences {
    @Id
    private Integer id;
    private String religion;
    private Boolean divorced;
    private Boolean kids;
    private Boolean local;
    private Boolean male;
    private Boolean female;
    @OneToOne
    private User user;

}



