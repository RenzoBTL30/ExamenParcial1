package pe.upeu.edu.examenparcial1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.upeu.edu.examenparcial1.model.Paciente;
import pe.upeu.edu.examenparcial1.services.PacienteService;

@RestController
@RequestMapping("/api")
public class PacienteController {
	
	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping("/paciente")
	public ResponseEntity<Paciente> save(@RequestBody Paciente pac){
		try {
			Paciente pc = pacienteService.create(new Paciente(pac.getIdpaciente(),pac.getDni(), pac.getNombres(), pac.getApellidos(), pac.getDireccion(), pac.getTelefono()));
			return new ResponseEntity<>(pc, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/pacientes")
	public ResponseEntity<List<Paciente>> getPaciente(){
		try {
			List<Paciente> list = new ArrayList<>();
			list = pacienteService.readAll();
			if(list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/paciente/{id}")
	public ResponseEntity<Paciente> getUser(@PathVariable("id") long id){
		Paciente paciente = pacienteService.read(id);
			if(paciente.getIdpaciente()>0) {
				return new ResponseEntity<>(paciente, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
	}
	
	@DeleteMapping("paciente/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
		try {
			pacienteService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("paciente/update/{id}")
	public ResponseEntity<Paciente> update(@RequestBody Paciente pc, @PathVariable("id") long id){
		try {
			Paciente ul = pacienteService.read(id);
			if (ul.getIdpaciente()>0) {
				ul.setDni(pc.getDni());
				ul.setNombres(pc.getNombres());
				ul.setApellidos(pc.getApellidos());
				ul.setDireccion(pc.getDireccion());
				ul.setTelefono(pc.getTelefono());
				return new ResponseEntity<>(pacienteService.create(ul),HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
