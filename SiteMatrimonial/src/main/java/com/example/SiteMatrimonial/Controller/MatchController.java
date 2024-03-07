
package com.example.SiteMatrimonial.Controller;

import com.example.SiteMatrimonial.Model.Matchul;
import com.example.SiteMatrimonial.Service.Match.MatchServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/Match")
public class MatchController {
    private final MatchServiceImplementation matchServiceImplementation;

    @PostMapping("/Insert")
    public void insert(@RequestParam("id") Integer userId)
    {
        matchServiceImplementation.insertMultipleMatches(userId);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestParam("id")  Integer userId, @RequestParam("match_user_id")  Integer match_userId)
    {
        matchServiceImplementation.delete(userId, match_userId);
    }

    @GetMapping("/Read")
    public void read(@RequestBody Matchul matchul)
    {
        System.out.println(matchServiceImplementation.read(matchul.getId()));
    }
    @GetMapping("/ReadMatchIds")
    public List<Integer> readMatchIds(@RequestParam("id") Integer userId)
    {
        return matchServiceImplementation.readMatchIds(userId);
    }
}
