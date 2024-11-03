package com.electiva.citas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.electiva.citas.modelos.Citas;
import com.electiva.citas.modelos.Hospital;
import com.electiva.citas.modelos.Paciente;
import com.electiva.citas.servicios.CitasServices;
import com.electiva.citas.servicios.HospitalServices;
import com.electiva.citas.servicios.Medicoservices;
import com.electiva.citas.servicios.PacienteServices;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/publico")
public class PublicoController {

    private final PacienteServices pacienteServices;

    private final Medicoservices medicoServices;

    private final CitasServices citasService;

    private static final String USERNAME = "admin";

    private static final String PASSWORD = "admin";

    

    public PublicoController(PacienteServices pacienteServices, Medicoservices medicoServices, CitasServices citasService) {
        this.pacienteServices = pacienteServices;
        this.medicoServices = medicoServices;
        this.citasService = citasService;
    }


    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/citas")
    public String mostrarCitas(Model modelo) {

        modelo.addAttribute("medicos", medicoServices.obtenerMedicos());
        modelo.addAttribute("cita", new Citas());

        return "citas";
    }

    @PostMapping("/registrar-cita")
    public String registrarCita(
            @RequestParam("cedula") String cedula,
            @RequestParam("medico") Long idMedico,
            @ModelAttribute("cita") Citas cita) {
        
        citasService.registrarCitas(cita, cedula, idMedico);
        return "redirect:/publico/citas";
    }

    

    @GetMapping("/registro")
    public String mostrarRegistro(Model modelo) {

        modelo.addAttribute("paciente", new Paciente());

        return "registroPaciente";
    }

    @PostMapping("/guardar-paciente")
    public String registrarPaciente(@ModelAttribute Paciente paciente) {

        System.out.println("Paciente fecha: " + paciente.getFechaNacimiento());

        pacienteServices.registrarPaciente(paciente);
        
        return "redirect:/publico/registro"; 
    }


    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "index";
    }

    @GetMapping("/medicos")
    public String mostrarMedico(Model modelo) {

        modelo.addAttribute("medicos", medicoServices.obtenerMedicos());
        
        return "medicos";
    }
    
    @GetMapping("/buscar-cita")
    public String mostrarCita(@RequestParam("cedula") Long cedula, Model modelo) {
        Paciente paciente = pacienteServices.buscarPaciente(cedula.toString());
        modelo.addAttribute("resultados", paciente.getCitas());
        return "personaCita";
        
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return "redirect:/admin/inicio"; // Redirige a la página de inicio después de iniciar sesión
        } else {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            return "/publico/login"; // Regresa al formulario de login en caso de error
        }
    }
    
    
    
}
