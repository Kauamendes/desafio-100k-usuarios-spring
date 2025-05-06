package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

        private T data;
        private long timestamp;
        private long processingTimeMs;
}
