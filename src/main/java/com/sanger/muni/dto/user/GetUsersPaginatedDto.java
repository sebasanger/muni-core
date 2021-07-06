package com.sanger.muni.dto.user;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersPaginatedDto {
	private Long id;
	private String username;
	private String avatar;
	private String fullName;
	private String email;
	private Set<String> roles;
	private String numeroLegajo;

}
