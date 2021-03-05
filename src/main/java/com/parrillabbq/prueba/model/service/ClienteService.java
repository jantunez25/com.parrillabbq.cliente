package com.parrillabbq.prueba.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrillabbq.prueba.model.dao.IClienteDao;
import com.parrillabbq.prueba.model.entity.Cliente;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Cliente findByID(int idCliente) {
		// TODO Auto-generated method stub
		return clienteDao.findById(idCliente).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(int idCliente) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(idCliente);
	}

}
