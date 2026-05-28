package com.codigo.cowork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {

    @GetMapping("/api/info")
    public Map<String, String> obtenerInfo() {

        Map<String, String> respuesta = new HashMap<>();

        respuesta.put("nombreApp", "cowork-api");
        respuesta.put("version", "1.0.0");
        respuesta.put("autor", "IVONNE");

        return respuesta;
    }
}
