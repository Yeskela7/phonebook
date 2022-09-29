package com.yeskela.phonebook.service;

import com.yeskela.phonebook.dto.UserReq;
import com.yeskela.phonebook.dto.UserResp;
import com.yeskela.phonebook.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserResp save(UserReq request);

    UserResp edit(String userId, UserReq request);

    UserResp delete(String userId);

    List<UserEntity> getAllUsers();
}
