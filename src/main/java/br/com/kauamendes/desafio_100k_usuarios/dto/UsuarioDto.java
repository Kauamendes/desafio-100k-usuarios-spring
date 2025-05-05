package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    private UUID id;
    private String name;
    private int age;
    private int score;
    private boolean active;
    private String country;
    private TeamDto teamDto;
    private List<LogDto> logsDto;
}

//{
//        "id": "uuid",
//        "name": "string",
//        "age": "int",
//        "score": "int",
//        "active": "bool",
//        "country": "string",
//        "team": {
//        "name": "string",
//        "leader": "bool",
//        "projects": [{ "name": "string", "completed": "bool" }]
//        },
//        "logs": [{ "date": "YYYY-MM-DD", "action": "login/logout" }]
//        }