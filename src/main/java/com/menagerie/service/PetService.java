package com.menagerie.service;

import com.menagerie.dto.request.PetRequest;
import com.menagerie.dto.request.PetUpdateRequest;
import com.menagerie.dto.response.BaseResponse;

/**
 * @author gulshan
 * @apiNote PetService interface provide the methods to perform particular functionality for pet and their events.
 */
public interface PetService {

	BaseResponse addPetEntry(PetRequest request);

	BaseResponse getPet(Integer id, String species, String key, String order);

	BaseResponse udpatePetEntry(PetUpdateRequest request);

}
