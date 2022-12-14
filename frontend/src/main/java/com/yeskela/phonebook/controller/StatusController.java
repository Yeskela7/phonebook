package com.yeskela.phonebook.controller;

import com.yeskela.phonebook.client.PhonebookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StatusController {

    private final PhonebookClient phonebookClient;

    @GetMapping("/status")
    public Object checkStatus() {
        return phonebookClient.getStatus();
    }
}
