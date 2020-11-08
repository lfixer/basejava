package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(r.toString());
    }
}
