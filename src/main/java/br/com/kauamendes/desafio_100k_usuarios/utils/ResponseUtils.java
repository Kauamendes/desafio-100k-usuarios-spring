package br.com.kauamendes.desafio_100k_usuarios.utils;

import br.com.kauamendes.desafio_100k_usuarios.dto.ResponseDto;

import java.util.function.Supplier;

public class ResponseUtils {

    public static <T> ResponseDto<T> withExecutionTime(Supplier<T> supplier) {
        long inicio = System.currentTimeMillis();
        T data = supplier.get();
        long fim = System.currentTimeMillis();
        return new ResponseDto<>(data, fim, fim - inicio);
    }
}
