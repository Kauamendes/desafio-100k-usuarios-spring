package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDto {

    private Date date;
    private String action;
}
