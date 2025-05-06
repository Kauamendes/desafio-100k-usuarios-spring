package br.com.kauamendes.desafio_100k_usuarios.controller;

import br.com.kauamendes.desafio_100k_usuarios.dto.*;
import br.com.kauamendes.desafio_100k_usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseDto<String> processarUsuarios(@RequestBody List<UsuarioDto> usuarios) {
        long inicio = System.currentTimeMillis();
        int quantidadeUsuarios = usuarioService.processarUsuarios(usuarios);
        long fim = System.currentTimeMillis();
        return new ResponseDto<>(quantidadeUsuarios+" Usuários processados com sucesso", fim, inicio - fim);
    }

    @GetMapping("/superusers")
    public ResponseDto<List<UsuarioDto>> buscarSuperUsers() {
        long inicio = System.currentTimeMillis();
        List<UsuarioDto> usuarios = usuarioService.buscarSuperUsuarios();
        long fim = System.currentTimeMillis();
        return new ResponseDto<>(usuarios, fim, inicio - fim);
    }

    @GetMapping("/top-countries")
    public ResponseDto<List<String>> buscarTopPaises() {
        long inicio = System.currentTimeMillis();
        List<String> paises = usuarioService.buscarTopPaises();
        long fim = System.currentTimeMillis();
        return new ResponseDto<>(paises, fim, inicio - fim);
    }

    @GetMapping("/team-insights")
    public ResponseDto<List<TeamInsightDto>> buscarInsightsDeTime() {
        long inicio = System.currentTimeMillis();
        List<TeamInsightDto> insights = usuarioService.gerarTeamInsights();
        long fim = System.currentTimeMillis();
        return new ResponseDto<>(insights, fim, inicio - fim);
    }

    @GetMapping("/active-users-per-day")
    public ResponseDto<Map<LogDto, Long>> buscarLoginsPorDia(@RequestParam(required = false) Integer min) {
        long inicio = System.currentTimeMillis();
        Map<LogDto, Long> logins = usuarioService.buscarLoginsPorDia(min);
        long fim = System.currentTimeMillis();
        return new ResponseDto<>(logins, fim, inicio - fim);
    }

    @GetMapping("/evaluation")
    public ResponseDto<List<EvaluationDto>> avaliarEndpoints() {
        long inicio = System.currentTimeMillis();
        List<EvaluationDto> resultados = usuarioService.avaliarEndpoints();
        long fim = System.currentTimeMillis();
        return new ResponseDto<>(resultados, fim, inicio - fim);
    }

}
