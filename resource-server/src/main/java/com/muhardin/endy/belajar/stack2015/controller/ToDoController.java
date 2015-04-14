package com.muhardin.endy.belajar.stack2015.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {

    @RequestMapping(value = "/halo", method = RequestMethod.GET)
    public Map<String, Object> halo(@RequestParam(required = false) String nama) {
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("salam", "Halo " + nama);
        return hasil;
    }
}
