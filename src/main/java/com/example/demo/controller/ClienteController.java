package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ClienteModel;
import com.example.demo.repository.ClienteRepository;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/cliente")
public class ClienteController{
	
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping("/listar")
	public List<ClienteModel> listarClientes() {
		return repository.findAll();
	}
	
	@PostMapping("/cadastrar")
	public ClienteModel cadastrarCliente(@RequestBody ClienteModel c) {
		return repository.save(c);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PutMapping("/atualizar/{id}")
	public ClienteModel atualizar(@PathVariable Long id, 
			@RequestBody ClienteModel cliente) {
		ClienteModel clienteAtual = this.repository.findById(id).orElseThrow(
				() -> new EmptyResultDataAccessException(1));		
		BeanUtils.copyProperties(cliente, clienteAtual, "id");			
		return repository.save(cliente);
	}

}
