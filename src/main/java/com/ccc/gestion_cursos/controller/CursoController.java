package com.ccc.gestion_cursos.controller;
import com.ccc.gestion_cursos.entity.Curso;
import com.ccc.gestion_cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;


    //home
    @GetMapping
    public String home(){
    return "redirect:/cursos";
    }


    //para mostrar los cursos
    @GetMapping("/cursos")
    public String listarCursos(Model model){
        List<Curso> cursos = cursoRepository.findAll();
        cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "cursos";
    }


    //para mostrar el formulario de crear curso
    @GetMapping("/cursos/nuevo")
    public String agregarCurso(Model model){

        //para crear el nuevo curso
        Curso curso = new Curso();
        curso.setPublicado(true);

        //enviando el nuevo curso a la vista
        model.addAttribute("curso", curso);
        model.addAttribute("pageTitle", "Nuevo curso");

        //return despues de completar la creacion
        return "curso_form";
    }

    //para guardar el curso
    ///cursos/save va a estar linkeado o siendo usada en curso_form
    @PostMapping("/cursos/save")
    //redirectAttributes es para agregar atributos a la logica que se va a implementar a continuacion
    public String guardarCurso(Curso curso, RedirectAttributes redirectAttributes){
        try{
            cursoRepository.save(curso);
            redirectAttributes.addFlashAttribute("message", "El curso ha sido guardado exitosamente");
        }catch (Exception e){
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/cursos";
    }


    //para editar curso
    @GetMapping("/cursos/{id}")
    public String editarCruso(@PathVariable Integer id,  Model model, RedirectAttributes redirectAttributes){
        try{
            Curso curso = cursoRepository.findById(id).get();
            model.addAttribute("pageTitle", "Editar curso: " + id);
            model.addAttribute("curso", curso);
            return "curso_form";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cursos";
    }


    //para eliminar curso
    @GetMapping("/cursos/delete/{id}")
    public String eliminarCurso(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
        try{
            cursoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Curso eliminado exitosamente");
        }catch(Exception exception){
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
        }
        return "redirect:/cursos";
    }

}
