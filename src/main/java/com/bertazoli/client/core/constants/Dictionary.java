package com.bertazoli.client.core.constants;

import com.google.inject.Singleton;

@Singleton
public class Dictionary {

    public static final String DATEFORMAT = "MM/dd/yyyy";
    
    public static final String EMPTYNOTALLOWED = "Empty not allowed";

    public static final String INVALIDEMAIL = "Invalid email";
    
    public static String MINLENGTH(Integer minLength) {
        return "Minimum length is " + minLength;
    }

    public static String MAXLENGTH(Integer maxLength) {
        return "Maximun length is " + maxLength;
    }
}
