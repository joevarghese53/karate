package com.automation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CreateUserResponsePojo {
    long id;
    String name;
    String email;
    String gender;
    String status;
}
