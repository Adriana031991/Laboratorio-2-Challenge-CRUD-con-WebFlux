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

