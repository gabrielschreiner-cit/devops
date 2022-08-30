package com.gabriel.clients.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.clients.dtos.ClientDto;
import com.gabriel.clients.entities.Client;
import com.gabriel.clients.repositories.ClientRepository;
import com.gabriel.clients.services.exceptions.DatabaseException;
import com.gabriel.clients.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public Page<ClientDto> findAllPaged(PageRequest request) {
		Page<Client> list = clientRepository.findAll(request);
		return list.map(item -> new ClientDto(item));
	}

	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Optional<Client> clientOptional = clientRepository.findById(id);
		Client client = clientOptional.orElseThrow(
				() -> new ResourceNotFoundException(String.format("Sorry, entity of id %s was not found.", id)));
		return new ClientDto(client);
	}

	@Transactional(readOnly = true)
	public ClientDto insert(ClientDto dto) {
		Client entity = new Client();
		toDto(dto, entity);
		entity = clientRepository.save(entity);
		return new ClientDto(entity);
	}

	@Transactional
	public ClientDto update(Long id, ClientDto dto) {
		try {
			Client entity = clientRepository.getById(id);
			toDto(dto, entity);
			entity = clientRepository.save(entity);
			return new ClientDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(String.format("Sorry, entity of id %s was not found.", id));
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation.");
		}
	}

	public void deleteById(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Sorry, entity of id %s was not found.", id));
		}

	}

	private void toDto(ClientDto dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
