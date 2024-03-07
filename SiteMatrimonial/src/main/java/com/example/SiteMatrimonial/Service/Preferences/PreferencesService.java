
package com.example.SiteMatrimonial.Service.Preferences;

import com.example.SiteMatrimonial.Model.Preferences;
import org.springframework.stereotype.Component;

@Component
public interface PreferencesService {

    Preferences findFirstById(Integer id);
    void insert(Preferences preferences);
    void delete(Integer id);
    Preferences read(Integer id);


}

