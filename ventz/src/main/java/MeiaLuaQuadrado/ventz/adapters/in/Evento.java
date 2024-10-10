package MeiaLuaQuadrado.ventz.adapters.in;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "tab_evento")
public class Evento extends MeiaLuaQuadrado.ventz.model.Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private int idEvento;
    @Column(name = "titulo_evento", length = 150, nullable = false)
    private String tituloEvento;
    @Column(name = "descricao", length = 500, nullable = true)
    private String descricao;
    @Column(name = "limite_pessoas", length = 6, nullable = false)
    private int limitePessoas;
    @Column(name = "data_inicio", nullable = false)
    private Date dataInicio;
    @Column(name = "data_termino", nullable = false)
    private Date dataTermino;
    @Column(name = "endereco", length = 500, nullable = false)
    private String endereco;
    @Column(name = "ingressos_utilizados", nullable = false)
    private int ingressosUtilizados;

    //     Chave estrangeira fkUsuarioIdUsuario
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", tituloEvento='" + tituloEvento + '\'' +
                ", descricao='" + descricao + '\'' +
                ", limitePessoas=" + limitePessoas +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", endereco='" + endereco + '\'' +
                ", ingressosUtilizados=" + ingressosUtilizados +
                ", usuario=" + usuario +
                '}';
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLimitePessoas() {
        return limitePessoas;
    }

    public void setLimitePessoas(int limitePessoas) {
        this.limitePessoas = limitePessoas;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIngressosUtilizados() {
        return ingressosUtilizados;
    }

    public void setIngressosUtilizados(int ingressosUtilizados) {
        this.ingressosUtilizados = ingressosUtilizados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}