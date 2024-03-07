package com.example.SiteMatrimonial.Service.Chat;

import com.example.SiteMatrimonial.Model.Chat;
import org.springframework.stereotype.Component;

@Component
public interface ChatService {
    Chat findFirstById(Integer id);
    void insert(Chat chat);
    void delete(Integer id);
    Chat read(Integer id);


}
