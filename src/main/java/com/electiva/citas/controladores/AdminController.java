package com.electiva.citas.controladores;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.electiva.citas.modelos.Citas;
import com.electiva.citas.modelos.CitasJson;
import com.electiva.citas.modelos.Hospital;
import com.electiva.citas.modelos.Medico;
import com.electiva.citas.modelos.Paciente;
import com.electiva.citas.servicios.CitasServices;
import com.electiva.citas.servicios.HistorialServices;
import com.electiva.citas.servicios.HospitalServices;
import com.electiva.citas.servicios.Medicoservices;
import com.electiva.citas.servicios.PacienteServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;





@Controller
@RequestMapping("/admin")
public class AdminController {


    private final HospitalServices hospitalServices;
    private final Medicoservices medicoServices;
    private final CitasServices citasServices;
    private final HistorialServices historialServices;
    private final PacienteServices pacienteServices;


    public AdminController(HospitalServices hospitalServices, Medicoservices medicoServices, CitasServices citasServices, HistorialServices historialServices, PacienteServices pacienteServices) {
        this.hospitalServices = hospitalServices;
        this.medicoServices = medicoServices;
        this.citasServices = citasServices;
        this.historialServices = historialServices;
        this.pacienteServices = pacienteServices;
    }

    



    @GetMapping("/inicio")
    public String mostrarInicio(Model modelo) {

        modelo.addAttribute("Pediente", citasServices.analisis("Pendiente"));
        modelo.addAttribute("Confirmada", citasServices.analisis("Confirmada"));
        modelo.addAttribute("Cancelada", citasServices.analisis("Cancelada"));
        modelo.addAttribute("Total", citasServices.contar());

        return "adminInicio";
    }

    @GetMapping("/api/citas") // Nuevo endpoint para JSON
    @ResponseBody
    public List<CitasJson> obtenerCitasJson() {
        return citasServices.citasJson();
    }
    

    @GetMapping("/clinicas")
    public String mostrarClinicas(Model modelo) {

        modelo.addAttribute("hospital", new Hospital());
        modelo.addAttribute("hospitales", hospitalServices.obtenerHospitales());

        return "adminHospital";
    }
    
    @PostMapping("/registrar-hospital")
    public String registrarHospital(@ModelAttribute Hospital hospital) {
        hospitalServices.registrarHospital(hospital);
        return "redirect:/admin/clinicas"; 
    }

    @GetMapping("/medicos")
    public String mostrarMedicos(Model modelos) {

        modelos.addAttribute("hospitales", hospitalServices.obtenerHospitales());
        modelos.addAttribute("medicos", new Medico());
        modelos.addAttribute("lista", medicoServices.obtenerMedicos());
        return "adminMedicos";
    }

    @PostMapping("/medico-registrar")
    public String registrarMedico(@ModelAttribute("medico") Medico medico,
                                  @RequestParam("idHospital") Long idHospital) {
        medicoServices.registrarMedico(medico, idHospital);
        return "redirect:/admin/medicos"; 
    }

    @GetMapping("/citas")
    public String getMethodName(Model model) {

        model.addAttribute("citas", citasServices.obtenerCitas());
        return "adminCitas";
    }

    @GetMapping("/historial")
    public String mostrarHistorial(Model model) {

        model.addAttribute("historial", historialServices.obtenerHistoriales());
        return "adminHistorial";
    }

    @GetMapping("/pacientes")
    public String mostrarPacientes(Model modelo) {
        modelo.addAttribute("pacientes", pacienteServices.obtenerPacientes());
        return "adminPacientes";
    }


    @GetMapping("/confirmar-cita")
    public String confirmarCita(@RequestParam("idCita") Long idCita) {
        citasServices.confirmarCitas(idCita);
        return "redirect:/admin/citas";
    }
    
    @GetMapping("/cancelar-cita")
    public String confirmarCancelar(@RequestParam("idCita") Long idCita) {
        citasServices.cancelarCitas(idCita);
        return "redirect:/admin/citas";
    }

    @GetMapping(value="/eliminar-historial")
    public String eliminarHistorial(@RequestParam(value="id") Long id) {
        historialServices.eliminarHistorial(id);
        return "redirect:/admin/historial";
    }

    @GetMapping(value="/eliminar-medico")
    public String eliminarMedico(@RequestParam(value="id") Long id) {
        historialServices.eliminarHistorial(id);
        return "redirect:/admin/medicos";
    }
    
    
    
    
    

    
}
