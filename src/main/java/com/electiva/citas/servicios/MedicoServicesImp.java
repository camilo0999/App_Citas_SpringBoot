package com.electiva.citas.servicios;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;
import com.electiva.citas.modelos.Hospital;

import com.electiva.citas.modelos.Medico;
import com.electiva.citas.repositorios.MedicoRepository;

@Service
public class MedicoServicesImp implements Medicoservices{

    private final MedicoRepository medicoRepository;
    private final HospitalServices hospitalServices;


    public MedicoServicesImp(MedicoRepository medicoRepository, HospitalServices hospitalServices) {
        this.medicoRepository = medicoRepository;
        this.hospitalServices = hospitalServices;
    }

   

    @Override
    public void registrarMedico(Medico medico, Long idHospital) {
       
        Hospital hospital = hospitalServices.obtenerHospitalPorId(idHospital);

        medico.setHospital(hospital);

        hospital.getMedicos().add(medico);
        
        medicoRepository.save(medico);
    }



    @Override
    public java.util.List<Medico> obtenerMedicos() {

        return medicoRepository.findAll();
    }



    @Override
    public Medico buscarMedico(Long id) {

        return medicoRepository.findById(id).orElse(null);
    }

    

    
    
}
