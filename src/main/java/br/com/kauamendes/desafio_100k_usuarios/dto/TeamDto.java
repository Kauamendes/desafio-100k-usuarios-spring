package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDto {

    private String name;
    private boolean leader;
    private List<ProjectDto> projectDtos;
}