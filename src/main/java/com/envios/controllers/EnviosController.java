package com.envios.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;

@Tag(name = "Envios", description = "API para gestión de envíos")
@RestController
@RequestMapping("/api/envios")
public class EnviosController {

    @Operation(summary = "Obtener todos los envíos")
    @GetMapping
    public List<Envio> getAllEnvios() {
        List<Envio> envios = enviosService.getAllEnvios();
        for (Envio envio : envios) {
            Link selfLink = Link.of("http://localhost:8888/api/envios/" + envio.getId()).withSelfRel();
            envio.add(selfLink);
        }
        return envios;
    }

    @Operation(summary = "Obtener un envío por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Envio> getEnvioById(@PathVariable Long id) {
        return enviosService.getEnvioById(id)
                .map(envio -> {
                    Link selfLink = Link.of("http://localhost:8888/api/envios/" + envio.getId()).withSelfRel();
                    envio.add(selfLink);
                    return ResponseEntity.ok(envio);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un envío")
    @PostMapping
    public Envio createEnvio(@RequestBody EnvioDTO dto) {
        Envio envio = enviosService.createEnvio(dto);
        Link selfLink = Link.of("http://localhost:8888/api/envios/" + envio.getId()).withSelfRel();
        envio.add(selfLink);
        return envio;
    }

    @Operation(summary = "Actualizar un envío")
    @PutMapping("/{id}")
    public ResponseEntity<Envio> updateEnvio(@PathVariable Long id, @RequestBody EnvioDTO dto) {
        return enviosService.updateEnvio(id, dto)
                .map(envio -> {
                    Link selfLink = Link.of("http://localhost:8888/api/envios/" + envio.getId()).withSelfRel();
                    envio.add(selfLink);
                    return ResponseEntity.ok(envio);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un envío")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable Long id) {
        if (enviosService.deleteEnvio(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
