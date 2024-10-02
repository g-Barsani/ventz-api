package MeiaLuaQuadrado.ventz.adapters.out.controller;

import MeiaLuaQuadrado.ventz.adapters.in.Ingresso;
import MeiaLuaQuadrado.ventz.adapters.in.Usuario;
import MeiaLuaQuadrado.ventz.adapters.repositories.IngressoRepository;
import MeiaLuaQuadrado.ventz.adapters.repositories.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    @Autowired
    IngressoRepository ingressoRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/buscarTodos")
    public String getIngressos()  throws JsonProcessingException {
        List<Ingresso> ingressos = ingressoRepository.findAll();

        return objectMapper.writeValueAsString(ingressos);
    }

    @PostMapping("/inserirIngresso")
    public void inserirIngresso(@RequestBody Ingresso ingresso){


            ingressoRepository.save(ingresso);
    }

    @GetMapping("/buscarPorUsuario/{idUsuario}")
    public String getIngressosByUsuarioId(@PathVariable("idUsuario") Integer idUsuario) throws JsonProcessingException {
        // Busca os ingressos pelo ID do usuário
        List<Ingresso> ingressos = ingressoRepository.findByUsuarioId(idUsuario);

        if (ingressos.isEmpty()) {
            return "Ingresso não encontrado para esse usuario";
        }

        return objectMapper.writeValueAsString(ingressos);  // Retorna a lista de ingressos
    }

    @GetMapping("/buscarPorId/{idIngresso}")
    public String getIngressosByIdIngresso(@PathVariable("idIngresso") Integer idIngresso) throws JsonProcessingException {
        // Busca os ingressos pelo ID do usuário
        Ingresso ingressos = ingressoRepository.findByIdIngresso(idIngresso).orElse(null);

        if (ingressos == null) {
            return "Ingresso não encontrado";
        }

        return objectMapper.writeValueAsString(ingressos);  // Retorna a lista de ingressos
    }
    // Método para utilizar o ingresso
    @PutMapping("/utilizarIngresso/{idIngresso}")
    public String utilizarIngresso(@PathVariable("idIngresso") Integer idIngresso) {
        // Busca o ingresso pelo ID
        Optional<Ingresso> ingressoOpt = ingressoRepository.findByIdIngresso(idIngresso);

        if (ingressoOpt.isPresent()) {
            Ingresso ingresso = ingressoOpt.get();

            // Verifica se o ingresso está disponível
            if (ingresso.getDisponivel()) {
                // Marca o ingresso como indisponível (utilizado)
                ingresso.setDisponivel(false);
                ingressoRepository.save(ingresso);

                return "Ingresso utilizado com sucesso.";
            } else {
                return "O ingresso já foi utilizado.";
            }
        } else {
            return "Ingresso não encontrado.";
        }
    }
}
