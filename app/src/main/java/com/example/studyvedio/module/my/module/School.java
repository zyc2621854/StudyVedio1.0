package com.example.studyvedio.module.my.module;

import javax.inject.Inject;

public class School {

    private String name;

    private String address;

    @Inject
    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
