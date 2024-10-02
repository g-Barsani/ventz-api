package MeiaLuaQuadrado.ventz.model;

import java.util.Date;

public abstract class Evento {
     int idEvento;
     String tituloEvento;
     String descricao;
     int limitePessoas;
     Date dataInicio;
     Date dataTermino;
     String endereco;
     int fkUsuarioIdUsuario;



}
