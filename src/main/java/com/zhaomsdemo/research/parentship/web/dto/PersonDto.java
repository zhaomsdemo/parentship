package com.zhaomsdemo.research.parentship.web.dto;

import com.zhaomsdemo.research.parentship.constant.Gender;
import com.zhaomsdemo.research.parentship.dao.document.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDto {

    String name;
    Gender gender;
    Date birthday;
    Address address1;
    Address address2;
    String father;
    String mother;
}
