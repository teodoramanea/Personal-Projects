
package com.example.SiteMatrimonial.Service.Preferences;

import com.example.SiteMatrimonial.Model.Preferences;
import com.example.SiteMatrimonial.Repository.PreferencesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class PreferencesServiceImplementation implements PreferencesService {
    @Autowired
    private PreferencesRepo preferencesRepo;


    @Override
    public Preferences findFirstById(Integer id) {
       return  preferencesRepo.findFirstById(id);
    }

    @Override
    public void insert(Preferences preferences) {
        preferencesRepo.save(preferences);
    }

    @Override
    public void delete(Integer id) {
        preferencesRepo.deleteById(id);
    }

    @Override
    public Preferences read(Integer id) {
        return preferencesRepo.readById(id);
    }



}

