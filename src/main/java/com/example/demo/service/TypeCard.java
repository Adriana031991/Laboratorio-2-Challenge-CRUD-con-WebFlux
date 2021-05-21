package com.example.demo.service;

import com.example.demo.model.Type;

import java.util.HashMap;
import java.util.Map;

public class TypeCard {

    public static Map<String, Type> createTypes(){
        Map<String, Type> types = new HashMap<>();
        types.put("03", Type.MASTERCARD);
        types.put("06",Type.VISA);
        types.put("12", Type.PRIME);
        return types;
    }

    public static Type validateTypeCard(Map<String, Type> types, String code){
        var validateCode = code.split("-");
        var validateMap =types.get(validateCode[0]);
        if (validateMap == null) {
            throw new IllegalArgumentException("codigo invalido");
        }
        return validateMap;
    }

}
/*
//esta forma es la que implemento david uribe
public static TypeCard generateType(String code){
        if(verifyode(code)){
            throw new IllegalArgumentException("el codigo no existe");
        }
        return getTypeCard(code);
}
private static boolean verifyode(String code) {
        return code == null || code == "";
}
private static TypeCard getTypeCard(String code){
        switch (code){
            case "03":
                return typeCard.MASTERCARD;
            case "06":
                return typeCard.VISA;
                case "12":
                return typeCard.PRIME;
            default:
                throw new IllegalArgumentException("codigo no valido");
        }
}

 */

