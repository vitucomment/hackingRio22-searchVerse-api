package com.switchverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.switchverse.model.Verses;



@Repository
public interface VerseRepository extends JpaRepository<Verses, Long>{
	
	Verses findById(long id);
	
	boolean existsById(long id);
	
	boolean existsByEndereco(String endereco);
	
	List<Verses> findByEmpresa(String empresa);
	
	List<Verses> findByCategoria(String categoria);
	
	List<Verses> findByPlataforma(String plataforma);
	
}
