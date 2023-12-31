package com.menagerie.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menagerie.dto.request.EventRequest;
import com.menagerie.dto.request.PetRequest;
import com.menagerie.dto.request.PetUpdateRequest;
import com.menagerie.service.PetService;

/**
 * 
 * @author gulshan
 * @apiNote PetController class contains methods that maps incoming request to
 *          specific methods based on URL match.
 *
 */
@RestController
public class PetController {

	@Autowired
	private PetService petService;

	@PostMapping(value = "/pets")
	public ResponseEntity<Object> addPetEntry(@Valid @RequestBody PetRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(petService.addPetEntry(request));
	}

	@GetMapping(value = { "/pets", "/pets/{id}" })
	public ResponseEntity<Object> getPet(@PathVariable(required = false) Integer id,
			@RequestParam(required = false) String species,
			@RequestParam(required = false, name = "sortKey") String key,
			@RequestParam(required = false) String order) {
		return ResponseEntity.status(HttpStatus.OK).body(petService.getPet(id, species, key, order));
	}

	@PutMapping(value = "/pets")
	public ResponseEntity<Object> udpatePet(@Valid @RequestBody PetUpdateRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(petService.udpatePetEntry(request));
	}

	@PostMapping(value = { "/pets/{id}" })
	public ResponseEntity<Object> addEvent(@PathVariable Integer id, @Valid @RequestBody EventRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(petService.addEvent(id, request));
	}

	@DeleteMapping("/pet/{id}")
	public ResponseEntity<Object> deletePetEntry(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(petService.deletePetEntry(id));
	}

}
