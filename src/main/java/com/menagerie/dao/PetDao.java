package com.menagerie.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menagerie.entity.Pet;

/**
 * 
 * @author gulshan
 *@apiNote PetDao interface is a Repository layer that used to fetch data from database based on the 'criteria' methods or a query.
 */
@Repository
public interface PetDao extends JpaRepository<Pet, Integer> {

	boolean existsByName(String name);
	
	List<Pet> findBySpeciesContains(String title, Sort sort);

}
