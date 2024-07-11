package com.zhaomsdemo.research.parentship;

import org.springframework.boot.SpringApplication;

public class TestParentshipApplication {

    public static void main(String[] args) {
        SpringApplication.from(ParentshipApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
