package pe.upeu.edu.examenparcial1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upeu.edu.examenparcial1.model.Paciente;
import pe.upeu.edu.examenparcial1.repository.PacienteRepository;

@Service
public class PacienteService implements SPaciente{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Override
	public Paciente create(Paciente paciente) {
		// TODO Auto-generated method stub
		return pacienteRepository.save(paciente);
	}

	@Override
	public List<Paciente> readAll() {
		// TODO Auto-generated method stub
		return pacienteRepository.findAll();
	}

	@Override
	public Paciente read(Long id) {
		// TODO Auto-generated method stub
		return pacienteRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		pacienteRepository.deleteById(id);
	}

	@Override
	public Paciente update(Paciente paciente) {
		// TODO Auto-generated method stub
		return pacienteRepository.save(paciente);
	}
	
}
