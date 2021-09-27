package pe.upeu.edu.examenparcial1.services;

import java.util.List;

import pe.upeu.edu.examenparcial1.model.Paciente;

public interface SPaciente {
	Paciente create(Paciente paciente);
	List<Paciente> readAll();
	Paciente read(Long id);
	void delete(Long id);
	Paciente update(Paciente paciente);
}
