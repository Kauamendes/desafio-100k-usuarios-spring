package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {

    private String name;
    private boolean completed;
}
