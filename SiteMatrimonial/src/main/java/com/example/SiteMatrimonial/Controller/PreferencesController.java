package com.example.SiteMatrimonial.Controller;
import com.example.SiteMatrimonial.Model.Preferences;
import com.example.SiteMatrimonial.Service.Preferences.PreferencesServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/Preferences")
public class PreferencesController {
    private final PreferencesServiceImplementation preferencesServiceImplementation;
//    @GetMapping("/GetData")
//
//    public String getMessage()
//    {
//        return "teo ce faci??";
//    }

    @PostMapping("/Insert")
    public void insert(@RequestBody Preferences preferences)
    {
        preferencesServiceImplementation.insert(preferences);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestParam("id") Integer id)
    {
        preferencesServiceImplementation.delete(id);
    }

    @GetMapping("/Read")
    public void read(@RequestParam("id")Integer id)
    {
        preferencesServiceImplementation.read(id);
    }


}
