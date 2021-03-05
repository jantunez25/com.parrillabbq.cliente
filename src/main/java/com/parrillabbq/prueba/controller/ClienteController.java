package com.parrillabbq.prueba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.parrillabbq.prueba.model.entity.Cliente;
import com.parrillabbq.prueba.model.service.IClienteService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> findAll(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/{idCliente}")
	public ResponseEntity<?> findById(@PathVariable Integer idCliente){
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.findByID(idCliente);
		} catch (DataException e) {
			response.put("mensaje", "Error en la consulta");
			response.put("error", e.getMessage().concat(":").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(idCliente.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK) ;
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente){
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clienteNew =clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en realizar insert en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario se creo con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{idCliente}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Integer idCliente) {
		Cliente clienteSeleccionado = clienteService.findByID(idCliente);
		Cliente clienteUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		if(clienteSeleccionado == null) {
			response.put("mensaje", "Error no se edito El usuario ID: ".concat(idCliente.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			clienteSeleccionado.setApellido(cliente.getApellido());;
			clienteSeleccionado.setCorreo(cliente.getCorreo());;
			clienteSeleccionado.setNombre(cliente.getNombre());;
			clienteSeleccionado.setTelefono(cliente.getTelefono());;
			clienteUpdated = clienteService.save(clienteSeleccionado);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje", "El usuario se actualizo con exito");
		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{idCliente}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateContrasenia(@RequestBody Cliente cliente, @PathVariable Integer idCliente) {
		Cliente clienteSeleccionado = clienteService.findByID(idCliente);
		Cliente clienteUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		if(clienteSeleccionado == null) {
			response.put("mensaje", "Error no se edito El usuario ID: ".concat(idCliente.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			clienteSeleccionado.setContrasenia(cliente.getContrasenia());;
			clienteUpdated = clienteService.save(clienteSeleccionado);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje", "El usuario se actualizo con exito");
		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Integer idCliente) {
		Map<String, Object> response = new HashMap<>();		
		try {
		clienteService.delete(idCliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		response.put("mensaje","El usuario se elimino con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
