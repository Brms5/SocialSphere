package com.socialSphere.util.Validate;

import java.lang.reflect.Field;

public class Validator {

    public static boolean hasNullField(Object object) {
        if (object == null) {
            return true;
        }

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); 
            try {
                if (field.get(object) == null) {
                    return true; 
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return false; 
    } 
    
}
