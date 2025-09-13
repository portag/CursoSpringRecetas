package com.emilio.recetas.controller;

import com.emilio.recetas.model.Receta;
import com.emilio.recetas.model.Dificultad;
import com.emilio.recetas.repository.RecetaRepository;
import com.emilio.recetas.repository.DificultadRepository;

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
    private final DificultadRepository dificultadRepository;

    public RecetaController(RecetaRepository recetaRepository, DificultadRepository dificultadRepository) {
        this.recetaRepository = recetaRepository;
        this.dificultadRepository = dificultadRepository;
    }

    // Listar todas las recetas
    @GetMapping("/recetas")
    public String listarRecetas(Model model) {
        List<Receta> recetas = recetaRepository.findAllWithDificultad();
        model.addAttribute("recetas", recetas);
        return "recetas"; 
    }

    // Mostrar formulario para crear nueva receta
    @GetMapping("/recetas/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("receta", new Receta());
        List<Dificultad> dificultades = dificultadRepository.findAll();
        model.addAttribute("dificultades", dificultades);
        return "insertar"; 
    }

    // Guardar receta
    @PostMapping("/recetas/nueva")
    public String crearReceta(@Valid Receta receta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Volvemos a enviar la lista de dificultades si hay errores
            List<Dificultad> dificultades = dificultadRepository.findAll();
            model.addAttribute("dificultades", dificultades);
            return "insertar";
        }

        recetaRepository.save(receta);
        return "redirect:/recetas";
    }
}
