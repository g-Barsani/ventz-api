package MeiaLuaQuadrado.ventz.adapters.in;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity  // Serve para utilizar tags de bancos de dados
@Table (name = "tab_usuario")  // Nomeia tabela
public class Usuario extends MeiaLuaQuadrado.ventz.model.Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)  // Permite o auto increment do ID no banco
    @Column (name="id_usuario")
    private int idUsuario;

    @Column (name="nome", length = 255, nullable = false)
    private String nome;
    @Column (name="email", length = 255, nullable = false)
    private String email;
    @Column (name="cpf", length = 11, nullable = false)
    private String cpf;

    @Column (name="senha", length = 20, nullable = false)
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")  // 'usuario' refere-se ao campo 'usuario' na classe Ingresso
    private List<Ingresso> ingressos;

    // Correct the JoinColumn to reference the id_usuario column from Ingresso
//    @OneToMany(mappedBy = "usuario")  // 'usuario' refers to the 'Usuario' field in 'Ingresso' class
//    private List<Ingresso> ingressos;

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   public List<Ingresso> getIngressos() {
       return ingressos;
   }

   public void setIngressos(List<Ingresso> ingressos) {
       this.ingressos = ingressos;
   }
}
