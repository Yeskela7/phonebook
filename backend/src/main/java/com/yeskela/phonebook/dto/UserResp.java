package com.yeskela.phonebook.dto;


import com.yeskela.phonebook.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResp {

    private String userId;
    private String operationType;
    private String operationStatus;

    public static UserResp success(String userId, String operationType) {
        return UserResp.builder().userId(userId).operationType(operationType).operationStatus(Status.SUCCESS.name()).build();
    }

    public static UserResp failure(String userId, String operationType) {
        return UserResp.builder().userId(userId).operationType(operationType).operationStatus(Status.FAILURE.name()).build();
    }
}