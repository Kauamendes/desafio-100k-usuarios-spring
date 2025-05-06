package br.com.kauamendes.desafio_100k_usuarios.service;

import br.com.kauamendes.desafio_100k_usuarios.dto.*;
import br.com.kauamendes.desafio_100k_usuarios.repository.UsuarioMemoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioMemoryRepository usuarioMemoryRepository;

    public int processarUsuarios(List<UsuarioDto> usuarioDtos) {
        try {
            usuarioMemoryRepository.salvarTodos(usuarioDtos);
            return usuarioMemoryRepository.getUsuarios().size();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar usuários", e);
        }
    }

    public List<UsuarioDto> buscarSuperUsuarios() {
        return usuarioMemoryRepository.getSuperUsuarios();
    }

    public List<String> buscarTopPaises() {
        return usuarioMemoryRepository.getSuperUsuarios().stream()
                .collect(Collectors.groupingBy(UsuarioDto::getCountry, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<TeamInsightDto> gerarTeamInsights() {
        return usuarioMemoryRepository.getUsuarios().stream()
                .filter(u -> u.getTeam() != null)
                .collect(Collectors.groupingBy(u -> u.getTeam().getName()))
                .entrySet().stream()
                .map(entry -> {
                    String teamName = entry.getKey();
                    List<UsuarioDto> membros = entry.getValue();

                    long totalMembros = membros.size();
                    long totalLideres = membros.stream()
                            .filter(u -> u.getTeam().isLeader())
                            .count();

                    long projetosConcluidos = membros.stream()
                            .flatMap(u -> u.getTeam().getProjectDtos().stream())
                            .filter(ProjectDto::isCompleted)
                            .count();

                    double percentualAtivos = 100.0 * membros.stream()
                            .filter(UsuarioDto::isActive)
                            .count() / totalMembros;

                    return new TeamInsightDto(teamName, totalMembros, totalLideres, projetosConcluidos, percentualAtivos);
                })
                .toList();
    }

    public Map<LogDto,Long> buscarLoginsPorDia(Integer min) {
        Map<LogDto, Long> contagem = usuarioMemoryRepository.getUsuarios().stream()
                .flatMap(u -> u.getLogsDto().stream())
                .filter(LogDto::isLogin)
                .collect(Collectors.groupingBy(login -> login, Collectors.counting()));

        if (min != null) {
            contagem = contagem.entrySet().stream()
                    .filter(e -> e.getValue() >= min)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return contagem;
    }

    public List<EvaluationDto> avaliarEndpoints() {
        List<String> endpoints = List.of(
                "http://localhost:8080/usuarios/superusers",
                "http://localhost:8080/usuarios/top-countries",
                "http://localhost:8080/usuarios/team-insights",
                "http://localhost:8080/usuarios/active-users-per-day"
        );

        ObjectMapper mapper = new ObjectMapper();
        return endpoints.stream().map(url -> {
            try {
                long start = System.currentTimeMillis();
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int status = connection.getResponseCode();
                InputStream in = connection.getInputStream();
                Object json = mapper.readTree(in);

                long duration = System.currentTimeMillis() - start;
                return new EvaluationDto(url, status, duration, true);
            } catch (Exception e) {
                return new EvaluationDto(url, 500, 0L, false);
            }
        }).toList();
    }
}
