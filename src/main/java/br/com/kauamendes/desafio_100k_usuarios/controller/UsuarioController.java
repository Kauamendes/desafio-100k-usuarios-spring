package br.com.kauamendes.desafio_100k_usuarios.controller;

import br.com.kauamendes.desafio_100k_usuarios.dto.*;
import br.com.kauamendes.desafio_100k_usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static br.com.kauamendes.desafio_100k_usuarios.utils.ResponseUtils.withExecutionTime;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseDto<String> processarUsuarios(@RequestBody List<UsuarioDto> usuarios) {
        return withExecutionTime(() -> {
            int quantidade = usuarioService.processarUsuarios(usuarios);
            return quantidade + " Usuários processados com sucesso";
        });
    }

    @GetMapping("/superusers")
    public ResponseDto<List<UsuarioDto>> buscarSuperUsers() {
        return withExecutionTime(usuarioService::buscarSuperUsuarios);
    }

    @GetMapping("/top-countries")
    public ResponseDto<List<String>> buscarTopPaises() {
        return withExecutionTime(usuarioService::buscarTopPaises);
    }

    @GetMapping("/team-insights")
    public ResponseDto<List<TeamInsightDto>> buscarInsightsDeTime() {
        return withExecutionTime(usuarioService::gerarTeamInsights);
    }

    @GetMapping("/active-users-per-day")
    public ResponseDto<Map<LogDto, Long>> buscarLoginsPorDia(@RequestParam(required = false) Integer min) {
        return withExecutionTime(() -> usuarioService.buscarLoginsPorDia(min));
    }

    @GetMapping("/evaluation")
    public ResponseDto<List<EvaluationDto>> avaliarEndpoints() {
        return withExecutionTime(usuarioService::avaliarEndpoints);
    }
}
