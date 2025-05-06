package br.com.kauamendes.desafio_100k_usuarios.dto;

import lombok.Getter;

@Getter
public class ResponseDto<T> {

        private final T data;
        private final long timestamp;
        private final long processingTimeMs;
        private final String tempoExecucao;

        public ResponseDto(T data, long timestamp, long processingTimeMs) {
                this.data = data;
                this.timestamp = timestamp;
                this.processingTimeMs = processingTimeMs;
                this.tempoExecucao = formatarDuracao(processingTimeMs);
        }

        private String formatarDuracao(long duracaoEmMs) {
                long segundos = duracaoEmMs / 1000;
                long milissegundos = duracaoEmMs % 1000;
                return segundos + "s " + milissegundos + "ms";
        }

        public static <T> ResponseDto<T> ok(T data, long inicio) {
                long fim = System.currentTimeMillis();
                return new ResponseDto<>(data, fim, fim - inicio);
        }
}