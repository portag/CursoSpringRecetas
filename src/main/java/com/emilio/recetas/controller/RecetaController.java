package com.emilio.recetas.controller;

import com.emilio.recetas.model.Receta;
import com.emilio.recetas.repository.RecetaRepository;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RecetaController {

    private final RecetaRepository recetaRepository;

    public RecetaController(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    // Listar todas las recetas
    @GetMapping("/recetas")
    public String listarRecetas(Model model) {
        List<Receta> recetas = recetaRepository.findAll();
        model.addAttribute("recetas", recetas);
        return "recetas"; // -> templates/recetas.html
    }

    // Mostrar formulario para crear nueva receta
    @GetMapping("/recetas/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("receta", new Receta()); 
        return "insertar"; // -> templates/insertar.html
    }

    // Guardar receta
    @PostMapping("/recetas/nueva")
    public String crearReceta(@Valid Receta receta, BindingResult result) {
        if (result.hasErrors()) {
            return "insertar";
        }

        recetaRepository.save(receta);
        return "redirect:/recetas";
    }
}
