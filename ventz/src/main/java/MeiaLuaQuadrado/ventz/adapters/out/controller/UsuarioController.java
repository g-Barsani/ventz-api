package MeiaLuaQuadrado.ventz.adapters.out.controller;

import MeiaLuaQuadrado.ventz.adapters.in.Usuario;
import MeiaLuaQuadrado.ventz.adapters.repositories.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/usuarios")  // Criação de endpoint
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/buscarTodos")
    public String getUsuarios()  throws JsonProcessingException {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return objectMapper.writeValueAsString(usuarios);
    }

    @PostMapping("/inserirUsuario")  // <<GetMapping>> Não exibe no navegador :)
    public void inserirUsario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
    }

    @GetMapping("/buscarPorId/{id}")  // <<GetMapping>> Exibe no navegador >:)
    public String getUsuarioById(@PathVariable("id") Integer idUsuario) {
        // Usar orElse(null) para retornar o valor ou null se não existir
        Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario).orElse(null);

        if (usuario != null) {
            return usuario.toString();  // Retorna o objeto 'Usuario' como string
        } else {
            return "Usuário não encontrado";
        }
    }


    @GetMapping("/buscarIdESenha")
    public String validarUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        // Busca o usuário pelo email e senha
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha).orElse(null);

        // Verifica se o usuário foi encontrado
        if (usuario != null) {
            return usuario.toString();
        } else {
            return "Credenciais inválidas :(";
        }
    }

    @GetMapping("/buscarPorCpf")
    public String getUsuarioByCpf(@RequestParam("cpf") String cpf) {
        // Busca o usuário pelo CPF
        Usuario usuario = usuarioRepository.findByCpf(cpf).orElse(null);

        // Verifica se o usuário foi encontrado
        if (usuario!= null) {
            return usuario.toString();  // Retorna os dados do usuário
        } else {
            return "CPF não cadastrado";
        }
    }
}
