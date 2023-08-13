/**
 * 
 */
package com.menagerie.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.menagerie.constant.Constants;
import com.menagerie.dao.PetDao;
import com.menagerie.dto.PetDto;
import com.menagerie.dto.request.PetRequest;
import com.menagerie.dto.response.BaseResponse;
import com.menagerie.entity.Event;
import com.menagerie.entity.Pet;
import com.menagerie.exception.custom.AlreadyExistDataExceptioin;
import com.menagerie.exception.custom.ResourseNotFoundException;
import com.menagerie.service.PetService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gulshan
 * @implNotes PetServiceImpl class includes several methods(create, update,
 *            delete and get) that used to manage pets and their event records.
 */
@Slf4j
@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetDao petDao;

	@Override
	public BaseResponse addPetEntry(PetRequest request) {
		if (petDao.existsByName(request.getName())) {
			throw new AlreadyExistDataExceptioin("pet entry already exists with given name");
		}
		PetDto petDto = mapEntityToDto(petDao.save(mapDtoToEntity(request)));
		log.info("Pet entry saved in db");
		return BaseResponse.builder().status(Constants.SUCCESS).message(Constants.PET_SAVE).data(petDto).build();
	}

	private Pet mapDtoToEntity(PetRequest request) {
		return Pet.builder().name(request.getName()).owner(request.getOwner()).species(request.getSpecies())
				.sex(request.getSex()).birth(request.getBirth()).death(request.getDeath()).build();
	}

	@Override
	public BaseResponse getPet(Integer id, String species, String key, String order) {
		Sort sort = setSorting(key, order);
		if (Objects.nonNull(id)) {
			PetDto petDto = mapPetEntityToDto(
					petDao.findById(id).orElseThrow(() -> new ResourseNotFoundException(Constants.PET_NOT_FOUND)));
			return BaseResponse.builder().status(Constants.SUCCESS).message(Constants.PET_GET).data(petDto).build();
		} else if (Objects.nonNull(species) && !species.trim().equals("")) {
			List<PetDto> pets = mapEntityToDto(petDao.findBySpeciesContains(species, sort));
			return BaseResponse.builder().status(Constants.SUCCESS).message(Constants.PET_GET).data(pets).build();
		} else {
			List<PetDto> pets = mapEntityToDto(petDao.findAll(sort));
			return BaseResponse.builder().status(Constants.SUCCESS).message(Constants.PET_GET).data(pets).build();
		}
	}

	private Sort setSorting(String key, String order) {
		Sort sort = Sort.by(Sort.Direction.ASC, "id");
		if (Objects.nonNull(key) && !key.trim().equals("") && Objects.nonNull(order) && !order.trim().equals("") ) {
			if (order.contains("dsc")) {
				sort = Sort.by(Sort.Direction.DESC, key);
			} else {
				sort = Sort.by(Sort.Direction.ASC, key);
			}
		} else if (Objects.nonNull(key) && !key.trim().equals("")) {
			sort = Sort.by(Sort.Direction.ASC, key);
		} else if (Objects.nonNull(order) && !order.trim().equals("")) {
			if (order.contains("dsc")) {
				sort = Sort.by(Sort.Direction.DESC, "id");
			} else {
				sort = Sort.by(Sort.Direction.ASC, "id");
			}
		}
		return sort;
	}

	private List<PetDto> mapEntityToDto(List<Pet> pets) {
		List<PetDto> petsList = new ArrayList<>();
		pets.forEach(pet -> petsList.add(PetDto.builder().id(pet.getId()).name(pet.getName()).owner(pet.getOwner())
				.species(pet.getSpecies()).sex(pet.getSex()).birth(pet.getBirth()).death(pet.getDeath()).build()));
		return petsList;
	}
	
	private PetDto mapPetEntityToDto(Pet pet) {
		PetDto petDto = PetDto.builder().id(pet.getId()).name(pet.getName()).owner(pet.getOwner()).species(pet.getSpecies())
				.sex(pet.getSex()).birth(pet.getBirth()).death(pet.getDeath()).events(pet.getEvents()).build();
		 List<Event> events = petDto.getEvents().stream().sorted(Comparator.comparing(Event::getDate)).collect(Collectors.toList());
		 petDto.setEvents(events);
		 return petDto;
	}

	/**
	 * Convert Entity to DTO
	 * 
	 * @param pet
	 * @return
	 */
	private PetDto mapEntityToDto(Pet pet) {
		return PetDto.builder().id(pet.getId()).name(pet.getName()).owner(pet.getOwner()).species(pet.getSpecies())
				.sex(pet.getSex()).birth(pet.getBirth()).death(pet.getDeath()).build();
	}

}
