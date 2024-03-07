package com.example.SiteMatrimonial.Controller;

import com.example.SiteMatrimonial.Model.Chat;
import com.example.SiteMatrimonial.Service.Chat.ChatServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/Chat")
public class ChatController {
    private final ChatServiceImplementation chatServiceImplementation;

    @PostMapping("/Insert")
    public void insert(@RequestBody Chat chat)
    {
        chatServiceImplementation.insert(chat);
    }

    @DeleteMapping("/Delete")
    public void delete(@RequestParam("id") Integer id)
    {
        chatServiceImplementation.delete(id);
    }

    @GetMapping("/Read")
    public void read(@RequestBody Chat chat)
    {
        System.out.println(chatServiceImplementation.read(chat.getId()));
    }


}