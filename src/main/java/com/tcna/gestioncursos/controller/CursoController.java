package com.tcna.gestioncursos.controller;
import com.tcna.gestioncursos.entity.Curso;

import com.tcna.gestioncursos.repository.CursoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String home(){

        return "redirect:/cursos";
    }

    @GetMapping("/cursos")
    public String ListarCursos(Model model){//Model permite agregar atributos para que se envie directamente e a las vistas
        List<Curso> cursos = cursoRepository.findAll();
        cursos = cursoRepository.findAll();
        model.addAttribute("cursos",cursos);
        return "cursos";
    }
    //para mostrar el formulario
    @GetMapping("/cursos/nuevo")
    public String agregarCurso(Model model){
        Curso curso= new Curso();
        curso.setPublicado(true);

        model.addAttribute("curso", curso);
        model.addAttribute("pageTitle","Nuevo curso");

        return "curso_form";
    }




    //cuando se confirme el llenado del formulario se usa esta función
    @PostMapping("/cursos/save")
    public String guardarCurso(Curso curso, RedirectAttributes redirectAttributes){
        try {
            cursoRepository.save(curso);
            redirectAttributes.addFlashAttribute("message","Curso guardo con éxito!!!!");
        }catch (Exception e){
            redirectAttributes.addAttribute("message",e.getMessage());

        }

        return "redirect:/cursos" ;
    }

    //cuando se confirme el llenado del formulario se usa esta función
    @GetMapping("/cursos/{id}")
    public String editarCurso(@PathVariable Integer id,Model model, RedirectAttributes redirectAttributes){
        try {

            Curso curso = cursoRepository.findById(id).get();
            model.addAttribute("pageTitle","Editar curso : "+ id);
            model.addAttribute("curso", curso);
            return "curso_form";
        }catch (Exception e){
            redirectAttributes.addAttribute("message",e.getMessage());

        }

        return "redirect:/cursos" ;
    }

    //cuando se confirme el llenado del formulario se usa esta función
    @GetMapping("/cursos/delete/{id}")
    public String eliminarCurso(@PathVariable Integer id,Model model, RedirectAttributes redirectAttributes){
        try {
            cursoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message","Curso eliminado con éxito!!!!");

        }catch (Exception e){
            redirectAttributes.addAttribute("message",e.getMessage());

        }

        return "redirect:/cursos" ;
    }



}
