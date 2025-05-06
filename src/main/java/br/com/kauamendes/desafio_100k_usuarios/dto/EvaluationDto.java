package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {

    private String endpoint;
    private int status;
    private long tempoRespostaMs;
    private boolean jsonValido;
}
