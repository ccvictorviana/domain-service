package com.br.product.teste.config;

import java.util.UUID;

public class FactoryData {

    public static String generateString(){
       return generateString(20);
    }

    public static String generateString(int size){
        StringBuilder result = new StringBuilder();

        for (int i = size; i > 0; i = i - 33) {
            result.append(UUID.randomUUID().toString()
                .replace("-", "")
                .substring((size < 33)? size: - 33)
            );
        }

        return result.toString();
    }
}
