package com.example.fitness.web.controllers;

import com.example.fitness.core.dto.PageDTO;
import com.example.fitness.core.dto.users.UserCreateDTO;
import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.entity.UserEntity;
import com.example.fitness.service.api.IAdminService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
@Validated
public class AdminController {

	private final IAdminService adminService;
	private final ConversionService conversionService;

	public AdminController(IAdminService adminService, ConversionService conversionService) {
		this.adminService = adminService;
		this.conversionService = conversionService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<PageDTO<UserDTO>> getPage(
			@RequestParam(name = "page", required = false, defaultValue = "0")
			Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "20")
			Integer size){

		Page<UserEntity> users = adminService.getPage(page, size);
		Set<UserDTO> usersSet = new HashSet<>();
		for(UserEntity entity : users.getContent()) {
			UserDTO dto = conversionService.convert(entity, UserDTO.class);
			usersSet.add(dto);
		}

		PageDTO.PageBuilder<UserDTO> builder = PageDTO.builder();
		PageDTO<UserDTO> pageOfUsers = builder.setNumber(users.getNumber())
				.setSize(users.getSize())
				.setTotal_pages(users.getTotalPages())
				.setTotal_elements(users.getTotalElements())
				.setFirst(users.isFirst())
				.setLast(users.isLast())
				.setNumber_of_elements(users.getNumberOfElements())
				.setContent(usersSet).build();

		return ResponseEntity.status(HttpStatus.OK).body(pageOfUsers);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@Valid @RequestBody UserCreateDTO dto){
		UserEntity entity = conversionService.convert(dto, UserEntity.class);
		adminService.add(entity);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body("Пользователь добавлен");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{uuid}")
	public ResponseEntity<UserDTO> getUser(@PathVariable(name = "uuid") UUID uuid) {
		UserEntity entity = adminService.get(uuid);
		UserDTO dto = conversionService.convert(entity, UserDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{uuid}/dt_update/{dt_update}")
	public ResponseEntity<?> updateUser(@PathVariable(name = "uuid") UUID uuid,
	                                    @PathVariable(name = "dt_update") Long dtUpdate,
	                                   @Valid @RequestBody UserCreateDTO dto) {
		UserEntity entity = conversionService.convert(dto, UserEntity.class);
		LocalDateTime dt = conversionService.convert(dtUpdate, LocalDateTime.class);
		adminService.update(entity, uuid, dt);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Пользователь изменён");
	}
}