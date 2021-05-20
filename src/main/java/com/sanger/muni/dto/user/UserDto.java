package com.sanger.muni.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.sanger.muni.views.UsersViews;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @JsonView(UsersViews.BaseData.class)
    private long id;
    @JsonView(UsersViews.BaseData.class)
    private long fullName;
    @JsonView(UsersViews.BaseData.class)
    private long email;
    @JsonView(UsersViews.BaseData.class)
    private long roles;
    @JsonView(UsersViews.UserTotalData.class)
    private long password;
}
