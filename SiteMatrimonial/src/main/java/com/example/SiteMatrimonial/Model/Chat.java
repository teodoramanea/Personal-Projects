
package com.example.SiteMatrimonial.Model;

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

public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String message;

   // @OneToMany(mappedBy = "chat")
    //private List<User> userList;

    @ManyToOne
    private User user;


}


