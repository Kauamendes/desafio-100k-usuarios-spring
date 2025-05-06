package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDto {

    private Date date;
    private String action;

    public boolean isLogin() {
        return Objects.equals("login", action);
    }
}
