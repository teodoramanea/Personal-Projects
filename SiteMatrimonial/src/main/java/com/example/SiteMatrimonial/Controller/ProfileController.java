package com.example.SiteMatrimonial.Controller;

import com.example.SiteMatrimonial.Model.Profile;
import com.example.SiteMatrimonial.Model.User;
import com.example.SiteMatrimonial.Service.Profile.ProfileServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/Profile")
public class ProfileController {
    private final ProfileServiceImplementation profileServiceImplementation;

    @PostMapping("/Insert")
    public void insert(@RequestBody Profile profile)
    {
        profileServiceImplementation.insert(profile);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestParam Integer id)
    {
        profileServiceImplementation.delete(id);
    }

    @GetMapping("/Read")
     public Profile read(@RequestParam("id") Integer id)
    {
         return profileServiceImplementation.read(id);
    }

    @GetMapping("/ReadAll")
    public List<Profile> readAll()
    {
        return profileServiceImplementation.ReadAllProfiles();
    }

    @PutMapping  ("/Update")
    public void update(@RequestParam("id")Integer userId, @RequestBody Profile profile)
    {

        profileServiceImplementation.updateById(userId, profile);
    }

    @GetMapping("/findUserId")
    public Integer findUserId(@RequestParam("id")Integer profileId)
    {
       return profileServiceImplementation.findUserId(profileId);
    }
    @GetMapping("/readAllProfileNames")
    public List<String> readAllProfileNames(@RequestParam("matchIds") List<Integer> matchIds)
    {
        return profileServiceImplementation.readAllProfileNames(matchIds);
    }

}
