package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamInsightDto {

    private String nomeTime;
    private long totalMembros;
    private long totalLideres;
    private long projetosConcluidos;
    private double percentualAtivos;
}
