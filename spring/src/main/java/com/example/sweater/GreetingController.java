package com.example.sweater;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(
//            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
//            Model model
            Map<String, Object> model

    ) {
//        model.put("name", name);
        //       model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model
    ) {
        Iterable<Message> messages = messageRepo.findAll();

        model.put("some", messages);
        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model
    ) {
        Message message = new Message(text, tag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String find, Map<String, Object> model
    ) {
        Iterable<Message> messages;

        if ((find != null) && !find.isEmpty()) {
            messages = messageRepo.findByTag(find);
        } else {
            messages = messageRepo.findAll();
        }


        model.put("messages", messages);

        return "main";
    }

}