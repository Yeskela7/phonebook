package com.yeskela.phonebook.service.impl;

import com.yeskela.phonebook.dto.UserReq;
import com.yeskela.phonebook.dto.UserResp;
import com.yeskela.phonebook.entity.UserEntity;
import com.yeskela.phonebook.enums.Operation;
import com.yeskela.phonebook.repository.UserRepository;
import com.yeskela.phonebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository ur;


    @Override
    public UserResp save(UserReq request) {
        try {
            var saved = ur.save(mapper.mapRequestToUserEntity(request));
            return UserResp.success(saved.getUserId(), Operation.ADD.name());
        } catch (Exception ex) {
            ex.printStackTrace();
            return UserResp.failure(null, Operation.ADD.name());
        }
    }

    @Override
    public UserResp edit(String userId, UserReq request) {
        return null;
    }

    @Override
    public UserResp delete(String userId) {
        return null;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return null;
    }
}
