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
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository ur;


    @Override
    public UserResp save(UserReq request) {
        UserEntity user = new UserEntity();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhone());
        try {
            var saved = ur.save(user);
            return UserResp.success(saved.getUserId(), Operation.ADD.name());
        } catch (Exception ex) {
            ex.printStackTrace();
            return UserResp.failure(null, Operation.ADD.name());
        }
    }

    @Override
    public UserResp edit(String userId, UserReq request) {
        try {
            var userEntity = ur.findById(userId).orElseThrow();
            userEntity.setName(request.getName());
            userEntity.setPhoneNumber(request.getPhone());
            var saved = ur.save(userEntity);
            return UserResp.success(saved.getUserId(), Operation.UPDATE.name());
        } catch (Exception ex) {
            ex.printStackTrace();
            return UserResp.failure(userId,  Operation.UPDATE.name());
        }
    }

    @Override
    public UserResp delete(String userId) {
        try {
            ur.deleteById(userId);
            return UserResp.success(userId, Operation.REMOVE.name());
        } catch (Exception ex) {
            ex.printStackTrace();
            return UserResp.failure(userId, Operation.REMOVE.name());
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.ur.findAll();
    }
}
