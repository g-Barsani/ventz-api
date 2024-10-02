package MeiaLuaQuadrado.ventz.adapters.repositories;

import MeiaLuaQuadrado.ventz.adapters.in.Ingresso;
import MeiaLuaQuadrado.ventz.adapters.in.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {
    List<Ingresso> findAll();

    Optional<Ingresso> findByIdIngresso(Integer id);

    void deleteByIdIngresso(Integer id);


    // Método para buscar ingressos por ID do usuário
    @Query("SELECT i FROM Ingresso i WHERE i.usuario.idUsuario = :idUsuario")
    List<Ingresso> findByUsuarioId(Integer idUsuario);
}

