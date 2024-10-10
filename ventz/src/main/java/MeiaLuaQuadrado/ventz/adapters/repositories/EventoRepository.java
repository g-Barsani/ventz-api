package MeiaLuaQuadrado.ventz.adapters.repositories;

import MeiaLuaQuadrado.ventz.adapters.in.Evento;
import MeiaLuaQuadrado.ventz.adapters.in.Ingresso;
import MeiaLuaQuadrado.ventz.adapters.in.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository <Evento, Integer>{
    List<Evento> findAll();

    Optional<Evento> findByIdEvento(Integer id);

    @Query("SELECT e FROM Evento e WHERE e.usuario.idUsuario = :idUsuario")
    List<Evento> findByUsuarioId(Integer idUsuario);

    @Modifying
    @Transactional
    @Query("UPDATE Evento e SET e.ingressosUtilizados = e.ingressosUtilizados + 1 WHERE e.idEvento = :idEvento")
    void adicionarIngressoUtilizado(Integer idEvento);


    @Override
    void deleteById(Integer id);

}
