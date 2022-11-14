package com.spaceyatech.berlin.models;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
class UserTest {
private static Class<?> user;

    @BeforeAll//runs before all test methods gat excuted
    public static void setupTest(){
         //testing if class exists
        try {
            user = Class.forName("com.spaceyatech.berlin.models.User");
        }catch (ClassNotFoundException e){

             fail(e.getMessage());
             log.warn("class does not exist:{}" , e.getMessage());
        }
    }
    @Test
    public void shouldHaveFields(){
        try{
            Field field = user.getDeclaredField("username");
            Field field2 = user.getDeclaredField("email");
            log.info("field 1:{} field 2: {} exists",field,field2);
        }catch(NoSuchFieldException e){
            log.warn("field does not exist:{}" , e.getMessage());
            fail(e.getMessage());

        }
    }

}