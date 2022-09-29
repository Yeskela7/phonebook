package com.yeskela.phonebook;

import com.yeskela.phonebook.dto.UserReq;
import com.yeskela.phonebook.dto.UserResp;
import com.yeskela.phonebook.entity.UserEntity;
import com.yeskela.phonebook.repository.UserRepository;
import com.yeskela.phonebook.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PhonebookApplicationTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepo;


    @Test
    void save() {
        given(userRepo.save(any())).willReturn(UserEntity.builder().build());
        UserResp saved = userService.save(UserReq.builder().build());
        then(userRepo).should().save(any());
        assertThat(saved).isNotNull();
    }

    @Test
    void saveShouldFail() {
        given(userRepo.save(any())).willThrow(new RuntimeException());
        UserResp saved = userService.save(UserReq.builder().build());
        assertThat(saved).isNotNull();
    }

    @Test
    void edit() {
        UserEntity entity = UserEntity.builder().build();
        given(userRepo.findById(anyString())).willReturn(Optional.of(entity));
        given(userRepo.save(any())).willReturn(entity);
        UserResp resp = userService.edit("id", UserReq.builder().build());
        assertThat(resp).isNotNull();
    }

    @Test
    void editShouldFail() {
        given(userRepo.findById(anyString())).willReturn(Optional.empty());
        UserResp resp = userService.edit("id", UserReq.builder().build());
        assertThat(resp).isNotNull();
    }

    @Test
    void delete() {
        willDoNothing().given(userRepo).deleteById(anyString());
        UserResp resp = userService.delete("id");
        assertThat(resp).isNotNull();
    }

    @Test
    void deleteShouldFail() {
        willThrow(new RuntimeException()).given(userRepo).deleteById(anyString());
        UserResp resp = userService.delete("id");
        assertThat(resp).isNotNull();
    }

    @Test
    void getAllUsers() {
        given(userRepo.findAll()).willReturn(List.of(UserEntity.builder().build()));
        List<UserEntity> allUsers = userService.getAllUsers();
        assertThat(allUsers).hasSize(1);
    }
}