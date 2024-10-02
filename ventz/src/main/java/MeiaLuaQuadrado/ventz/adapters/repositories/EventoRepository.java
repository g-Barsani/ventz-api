package MeiaLuaQuadrado.ventz.adapters.repositories;

import MeiaLuaQuadrado.ventz.adapters.in.Evento;
import MeiaLuaQuadrado.ventz.adapters.in.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository <Evento, Integer>{
    List<Evento> findAll();

    Optional<Evento> findById(Integer id);

    Optional<Usuario>findByTituloEvento(String tituloEvento);

    @Override
    void deleteById(Integer id);

}
