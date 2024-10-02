package MeiaLuaQuadrado.ventz.adapters.repositories;

import MeiaLuaQuadrado.ventz.adapters.in.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findAll();

    Optional<Usuario> findByIdUsuario(Integer id);


    @Override
    void deleteById(Integer id);

    // Método para buscar usuário por email e senha
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    // Método para buscar usuário por CPF
    Optional<Usuario> findByCpf(String cpf);


    //void save(Usuario usuario);
}
