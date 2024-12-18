package MeiaLuaQuadrado.ventz.adapters.out.controller;

import MeiaLuaQuadrado.ventz.adapters.in.Usuario;
import MeiaLuaQuadrado.ventz.adapters.repositories.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // http://localhost:8080/usuarios/buscarTodos
    @GetMapping("/buscarTodos")
    public ResponseEntity<String> getUsuarios() throws JsonProcessingException {

            List<Usuario> usuarios = usuarioRepository.findAll();

            if (!usuarios.isEmpty()){
                //NUNCA FACA DESSE JEITO, ISSO NÃO ECXSTE, NAO É UM JASON EXCOMUNiTIONIS
//                return ResponseEntity.ok().body(usuarios.toString());
                return ResponseEntity.ok(objectMapper.writeValueAsString(usuarios));
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USUÁRIO JÁ CADASTRADO");
            }

    }

    /*
    http://localhost:8080/usuarios/inserirUsuario

    body:

    {
        "nome": "Sapo PRETO",
            "email": "sapo.preto@example.com",
            "cpf": "66624246969",
            "senha": "123"
    }

    */
    @PostMapping("/inserirUsuario")
    public ResponseEntity<String> inserirUsuario(@RequestBody Usuario usuario) {
        String cpf = usuario.getCpf();

        // Pega no BD se algum usuario tem o cpf informado
        Optional<Usuario> usuarioCpf = usuarioRepository.findByCpf(cpf);

        // Checa se usuarioCpf é null
        boolean usuarioCadastrado = usuarioCpf.isPresent();

        if (usuarioCadastrado) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USUÁRIO JÁ CADASTRADO");
        } else {
            usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
        }
    }

    // http://localhost:8080/usuarios/buscarPorId/3
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<String> getUsuarioById(@PathVariable("id") Integer idUsuario) throws JsonProcessingException {
        // Usar orElse(null) para retornar o valor ou null se não existir
        Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario).orElse(null);

        if (usuario != null) {
            return ResponseEntity.ok(objectMapper.writeValueAsString(usuario));  // Retorna o objeto 'Usuario' como string com status 200
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");  // Retorna 404 com mensagem
        }
    }


    // http://localhost:8080/usuarios/buscarPorEmailESenha?email=sapo.preto@example.com&senha=123
    @GetMapping("/buscarPorEmailESenha")
    public ResponseEntity<String> validarUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha) throws JsonProcessingException{
        // Busca o usuário pelo email e senha
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha).orElse(null);

        // Verifica se o usuário foi encontrado
        if (usuario != null) {
            return ResponseEntity.ok(objectMapper.writeValueAsString(usuario));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credenciais inválidas");
    }
    }

    // localhost:8080/usuarios/buscarPorCpf?cpf=valorCPF
    @GetMapping("/buscarPorCpf")
    public ResponseEntity<String> getUsuarioByCpf(@RequestParam("cpf") String cpf) throws JsonProcessingException{
        // Busca o usuário pelo CPF
        Usuario usuario = usuarioRepository.findByCpf(cpf).orElse(null);

        // Verifica se o usuário foi encontrado
        if (usuario!= null) {
            return ResponseEntity.ok(objectMapper.writeValueAsString(usuario)); // Retorna os dados do usuário
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF Não cadastrado");
        }
    }
}
