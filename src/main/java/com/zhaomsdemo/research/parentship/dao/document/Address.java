package com.zhaomsdemo.research.parentship.dao.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    String street;
    String city;
    String zipCode;
    String state;
    String county;
    String detail;
}
