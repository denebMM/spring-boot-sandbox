package sandbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sandbox.dao.ChatDao;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatDao chatDao;

    @GetMapping("/preguntar")
    public String preguntar(@RequestParam String q) {
        String respuesta = chatDao.preguntarModelo(q);

        // Manejo mejorado de errores
        if (respuesta.contains("Error") || respuesta.contains("iniciar")) {
            return "⚠️ " + respuesta;
        }
        return respuesta;
    }
}