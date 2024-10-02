package MeiaLuaQuadrado.ventz.adapters.in;

import jakarta.persistence.*;

@Entity  // Serve para utilizar tags de bancos de dados
@Table(name = "tab_ingresso")  // Nomeia tabela
public class Ingresso extends MeiaLuaQuadrado.ventz.model.Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Permite o auto increment do ID no banco
    @Column (name="id_ingresso")
    private int idIngresso;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_evento", nullable = false)
    private Evento evento;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column (name="disponivel", nullable = false)
    private Boolean disponivel;

    @Override
    public String toString() {
        return "Ingresso{" +
                "idIngresso=" + idIngresso +
                ", evento=" + evento +
                ", usuario=" + usuario.getIdUsuario() +
                ", disponivel=" + disponivel +
                '}';
    }

    public int getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
