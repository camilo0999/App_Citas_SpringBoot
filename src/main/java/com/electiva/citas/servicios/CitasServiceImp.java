package com.electiva.citas.servicios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.electiva.citas.modelos.Citas;
import com.electiva.citas.modelos.CitasJson;
import com.electiva.citas.modelos.Historial;
import com.electiva.citas.modelos.Hospital;
import com.electiva.citas.modelos.Medico;
import com.electiva.citas.modelos.Paciente;
import com.electiva.citas.repositorios.CitasRepository;
import com.electiva.citas.repositorios.HistorialRepository;

@Service
public class CitasServiceImp implements CitasServices {

    private final CitasRepository citasRepository;

    private final PacienteServices pacienteServices;

    private final Medicoservices medicoServices;

    private final HistorialRepository historialRepository;

    public CitasServiceImp(CitasRepository citasRepository, PacienteServices pacienteServices, Medicoservices medicoServices, HistorialRepository historialRepository) {
        this.citasRepository = citasRepository;
        this.pacienteServices = pacienteServices;
        this.medicoServices = medicoServices;
        this.historialRepository = historialRepository;
    }

    @Override
    public void registrarCitas(Citas citas, String cedula, Long idMedico) {
        Paciente paciente = pacienteServices.buscarPaciente(cedula);

        Medico medico = medicoServices.buscarMedico(idMedico);

        Hospital hospital = medico.getHospital();

        Historial historial = new Historial(paciente, medico, citas.getFecha(),"CITA MEDICA", citas.getSintomas());

        paciente.getHistoriales().add(historial);

        historial.setPaciente(paciente);

        citas.setHospital(hospital);

        citas.setMedico(medico);

        citas.setEstado("Pendiente");

        medico.getCitas().add(citas);

        paciente.getCitas().add(citas);

        citas.setPaciente(paciente);
        
        citasRepository.save(citas);

        historialRepository.save(historial);
    }

    @Override
    public List<Citas> obtenerCitas() {
        return citasRepository.findAll();
    }

    @Override
    public void cancelarCitas(Long idCita) {
        Citas citas = citasRepository.findById(idCita).orElse(null);
        citas.setEstado("Cancelada");
        citasRepository.save(citas);
    }

    @Override
    public void confirmarCitas(Long idCita) {
        Citas citas = citasRepository.findById(idCita).orElse(null);
        citas.setEstado("Confirmada");
        citasRepository.save(citas);
    }

    @Override
    public List<CitasJson> citasJson() {
        
        List<Citas> citas = citasRepository.findAll();
        List<CitasJson> citasJsons = new ArrayList<>();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Citas cita : citas) {
            // Suponiendo que tu CitasJson tiene un constructor que acepta estos parámetros
            CitasJson citasJson = new CitasJson(
                "DR(a). "+cita.getMedico().getNombre()+" "+cita.getMedico().getApellido(), // Asegúrate de tener el método adecuado para obtener el nombre del médico
                dateFormat.format(cita.getFecha()),
                cita.getHora().toString() // Ajusta según cómo quieras manejar la hora
            );
            citasJsons.add(citasJson);
        }
        
        return citasJsons;



    }

    @Override
    public int analisis(String estado) {

        return citasRepository.countByEstadoContaining(estado);
    }

    @Override
    public Long contar() {
        
        return citasRepository.count();
    }
}
