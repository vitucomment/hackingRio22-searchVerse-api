package com.switchverse.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.switchverse.model.Verses;
import com.switchverse.repository.VerseRepository;

@RestController
@RequestMapping("/metaverses")
public class MetaverseController {

	@Autowired
	private VerseRepository verseRepository;

	@GetMapping
	public List<Verses> listarTodos() {
		return verseRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Verses> listarPorId(@PathVariable(value = "id") long id) {
		try {
			if (verseRepository.existsById(id))
				return new ResponseEntity<Verses>(verseRepository.findById(id), HttpStatus.FOUND);
			else
				return new ResponseEntity<Verses>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Verses>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/buscar_empresa/{empresa}")
	public List<Verses> listarPorEmpresa(@PathVariable(value = "empresa") String empresa) {
		return verseRepository.findByEmpresa(empresa);
	}

	@GetMapping("/buscar_categoria/{categoria}")
	public List<Verses> listarPorCategoria(@PathVariable(value = "categoria") String categoria) {
		return verseRepository.findByCategoria(categoria);
	}
	
	@GetMapping("/buscar_plataforma/{plataforma}")
	public List<Verses> listarPorPlataforma(@PathVariable(value = "plataforma") String plataforma) {
		return verseRepository.findByPlataforma(plataforma);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Verses> adicionarMetaverse(@RequestBody Verses verses) {
		try {
			if (!verseRepository.existsByEndereco(verses.getEndereco())) {
				return new ResponseEntity<Verses>(verseRepository.save(verses), HttpStatus.CREATED);
			} else {
				throw new RuntimeException("Metaverso ja cadastrado");
			}
		} catch (Exception ex) {
			return new ResponseEntity<Verses>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping
	public void deleteMetaverse(@RequestBody Verses verses) {
		try {
			if (verseRepository.existsById(verses.getId()))
				verseRepository.delete(verses);
			else
				throw new RuntimeException("User not found");
		} catch (Exception ex) {
			throw new RuntimeException("BAD_REQUEST");
		}
	}

	@PutMapping
	public ResponseEntity<Verses> atualizarMetaverse(@RequestBody Verses verses) {
		try {
			if (verseRepository.existsById(verses.getId()))
				return new ResponseEntity<Verses>(verseRepository.save(verses), HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<Verses>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<Verses>(HttpStatus.BAD_REQUEST);
		}
	}

}
