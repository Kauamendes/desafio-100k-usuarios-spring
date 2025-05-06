package br.com.kauamendes.desafio_100k_usuarios.repository;

import br.com.kauamendes.desafio_100k_usuarios.dto.UsuarioDto;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UsuarioMemoryRepository {

    @Getter
    private final List<UsuarioDto> usuarios = new CopyOnWriteArrayList<>();

    @Getter
    private final List<UsuarioDto> superUsuarios = new CopyOnWriteArrayList<>();

    public void salvarTodos(List<UsuarioDto> novosUsuarios) {
        usuarios.clear();
        usuarios.addAll(novosUsuarios);
        superUsuarios.clear();
        superUsuarios.addAll(
                novosUsuarios.stream()
                        .filter(u -> u.isActive() && u.getScore() >= 900)
                        .toList()
        );
    }
}
