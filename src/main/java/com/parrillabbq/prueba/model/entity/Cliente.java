package com.parrillabbq.prueba.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	@Column(nullable = false, unique = true)
	private String Usuario;
	@Column(nullable = false)
	private String Contrasenia;
	@Column(nullable = false)
	private String Nombre;
	@Column(nullable = false)
	private String Apellido;
	@Column(nullable = false, unique = true)
	private String correo;
	@Column(nullable = false)
	private String telefono;	
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getUsuario() {
		return Usuario;
	}
	
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getContrasenia() {
		return Contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		Contrasenia = contrasenia;
	}
	
	public String getNombre() {
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
