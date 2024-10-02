package MeiaLuaQuadrado.ventz.adapters.out.controller;

import MeiaLuaQuadrado.ventz.adapters.in.Evento;
import MeiaLuaQuadrado.ventz.adapters.in.Usuario;
import MeiaLuaQuadrado.ventz.adapters.repositories.EventoRepository;
import MeiaLuaQuadrado.ventz.adapters.repositories.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")  // Criação de endpoint
public class EventoController {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/buscarTodos")
    public String getEvento()  throws JsonProcessingException {
        List<Evento> eventos = eventoRepository.findAll();

        return objectMapper.writeValueAsString(eventos);
    }

    @PostMapping("/inserirEvento")
    public void inserirEvento(@RequestBody Evento evento){
        eventoRepository.save(evento);
    }
}
