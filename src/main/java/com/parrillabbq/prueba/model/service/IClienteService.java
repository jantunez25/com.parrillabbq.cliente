package com.parrillabbq.prueba.model.service;

import java.util.List;

import com.parrillabbq.prueba.model.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
	public Cliente findByID(int idCliente);
	public Cliente save(Cliente cliente);
	public void delete(int idCliente);
}
