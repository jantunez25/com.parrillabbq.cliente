package com.parrillabbq.prueba.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.parrillabbq.prueba.model.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Integer>{

}
