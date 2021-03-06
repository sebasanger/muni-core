package com.sanger.muni.dto.user;

import java.util.stream.Collectors;

import com.sanger.muni.model.UserEntity;
import com.sanger.muni.model.UserRole;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {

	private final PasswordEncoder passwordEncoder;

	private final ModelMapper modelMapper;

	public GetUsersDto convertUserEntityToGetUserDto(UserEntity user) {
		return modelMapper.map(user, GetUsersDto.class);
	}

	public GetUserDetailsDto convertUserEntityToGetUserDetailsDto(UserEntity user) {
		return GetUserDetailsDto.builder().id(user.getId()).username(user.getUsername()).fullName(user.getFullName())
				.email(user.getEmail()).enabled(user.isEnabled()).createdAt(user.getCreatedAt())
				.lastPasswordChangeAt(user.getLastPasswordChangeAt())
				.roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet())).build();
	}

	public GetUsersPaginatedDto convertUserEntityToGetUserPaginatedDto(UserEntity user) {
		return GetUsersPaginatedDto.builder().id(user.getId()).username(user.getUsername()).fullName(user.getFullName())
				.email(user.getEmail()).numeroLegajo(user.getNumeroLegajo())
				.roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet())).build();
	}

	public UserEntity convertCreateUserDtoToUserEntity(CreateUserDto newUser) {
		return UserEntity.builder().username(newUser.getEmail())
				.password(passwordEncoder.encode("myPasswordEncoded12313123")).fullName(newUser.getFullName())
				.email(newUser.getEmail()).roles(newUser.getRoles()).enabled(false).build();
	}

	public UserEntity convertUpdateUserDtoToUserEntity(UpdateUserDto user) {
		return UserEntity.builder().username(user.getEmail()).fullName(user.getFullName()).email(user.getEmail())
				.roles(user.getRoles()).build();
	}

	public UserEntity convertUpdateAcountDtoToUserEntity(UpdateAcountDto user) {
		return modelMapper.map(user, UserEntity.class);
	}

}
