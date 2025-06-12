package com.envios.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/envios")
public class EnviosController {

    @GetMapping
    public List<String> getAll() {
        return new ArrayList<>();
    }

    @PostMapping
    public String create(@RequestBody String body) {
        return "Created: " + body;
    }
}
