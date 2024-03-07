package com.example.SiteMatrimonial.Service.Chat;

import com.example.SiteMatrimonial.Model.Chat;
import com.example.SiteMatrimonial.Repository.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class ChatServiceImplementation implements ChatService {
    @Autowired
    private ChatRepo chatRepo;

    @Override
    public Chat findFirstById(Integer id) {
        return null;
    }

    @Override
    public void insert(Chat chat) {
        chatRepo.save(chat);
    }

    @Override
    public void delete(Integer id) {
        chatRepo.deleteById(id);
    }

    @Override
    public Chat read(Integer id) {
        return chatRepo.readById(id);
    }



}
