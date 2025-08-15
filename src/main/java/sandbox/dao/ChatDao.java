package sandbox.dao;

import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Repository
public class ChatDao {

    public String preguntarModelo(String pregunta) {
        try {
            // URL de la API local de Ollama
            String url = "http://localhost:11434/api/generate";

            // 1. Construir el cuerpo de la solicitud para Ollama
            Map<String, Object> body = new HashMap<>();
            body.put("model", "gemma:2b");  // Modelo descargado
            body.put("prompt", pregunta);
            body.put("stream", false);     // Para recibir respuesta completa en una sola llamada

            // 2. Configurar headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            // 3. Enviar solicitud
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            // 4. Procesar respuesta específica de Ollama
            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();
                return (String) responseBody.get("response");
            } else {
                return "Error en Ollama: " + response.getStatusCode();
            }

        } catch (Exception e) {
            return "Error consultando modelo local: " + e.getMessage();
        }
    }
}