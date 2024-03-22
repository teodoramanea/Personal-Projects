package com.example.SiteMatrimonial.Repository;

import com.example.SiteMatrimonial.Model.Chat;
import com.example.SiteMatrimonial.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer> {
    Chat findFirstById(Integer id);
    void deleteById(Integer id);
    void deleteByUserId(Integer id);

    void deleteByUser(User user);
    Chat readById(Integer id);

}
