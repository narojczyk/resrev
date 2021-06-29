package pl.jwn.resrev.utils;

import java.util.UUID;

public class KeyGen {
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
