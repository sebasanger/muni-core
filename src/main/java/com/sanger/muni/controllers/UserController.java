package com.sanger.muni.controllers;

import java.util.List;

import javax.validation.Valid;

import com.sanger.muni.dto.auth.ChangeUserPassword;
import com.sanger.muni.dto.user.CheckEmailIsValidDto;
import com.sanger.muni.dto.user.CreateUserDto;
import com.sanger.muni.dto.user.GetUserDetailsDto;
import com.sanger.muni.dto.user.GetUsersDto;
import com.sanger.muni.dto.user.GetUsersPaginatedDto;
import com.sanger.muni.dto.user.UpdateAcountDto;
import com.sanger.muni.dto.user.UpdateUserDto;
import com.sanger.muni.dto.user.UserDtoConverter;
import com.sanger.muni.error.exceptions.UserNotFoundException;
import com.sanger.muni.model.UserEntity;
import com.sanger.muni.services.UserEntityService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserEntityService userEntityService;
	private final UserDtoConverter userDtoConverter;

	@GetMapping("")
	public ResponseEntity<?> getUsers() {
		List<UserEntity> result = userEntityService.findAll();

		if (result.isEmpty()) {
			throw new UserNotFoundException();
		} else {
			return ResponseEntity.ok().body(result);
		}
	}

	@GetMapping("/pageable")
	public ResponseEntity<?> listUsers(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(defaultValue = "") String filter) {
		Page<UserEntity> result = userEntityService.filterUser(filter, pageable);

		if (result.isEmpty()) {
			throw new UserNotFoundException();
		} else {
			Page<GetUsersPaginatedDto> dtoList = result.map(userDtoConverter::convertUserEntityToGetUserPaginatedDto);

			return ResponseEntity.ok().body(dtoList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetUserDetailsDto> findUserById(@PathVariable Long id) {
		UserEntity result = userEntityService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		GetUserDetailsDto user = userDtoConverter.convertUserEntityToGetUserDetailsDto(result);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("")
	public ResponseEntity<GetUsersDto> newUser(@Valid @RequestBody CreateUserDto newUser) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.newUser(newUser)));
	}

	@PutMapping("")
	public ResponseEntity<GetUsersDto> updateUser(@Valid @RequestBody UpdateUserDto user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.updateUser(user)));

	}

	@PutMapping("/update-acount")
	public ResponseEntity<GetUsersDto> updateAcount(@Valid @RequestBody UpdateAcountDto user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.updateAcount(user)));

	}

	@PutMapping("/changePassword")
	public ResponseEntity<GetUsersDto> updateUser(@Valid @RequestBody ChangeUserPassword user) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.updatePassword(user)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

		UserEntity user = userEntityService.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		userEntityService.delete(user);
		return ResponseEntity.noContent().build();

	}

	@PostMapping("/checkEmailIsValid")
	public boolean checkEmailIsValid(@Valid @RequestBody CheckEmailIsValidDto checkEmailIsValidDto) {
		return userEntityService.checkEmailIsValid(checkEmailIsValidDto);
	}

}
