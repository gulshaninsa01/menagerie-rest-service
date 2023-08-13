/**
 * 
 */
package com.menagerie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menagerie.constant.Constants;
import com.menagerie.dao.PetDao;
import com.menagerie.dto.PetDto;
import com.menagerie.dto.request.PetRequest;
import com.menagerie.dto.response.BaseResponse;
import com.menagerie.entity.Pet;
import com.menagerie.exception.custom.AlreadyExistDataExceptioin;
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
