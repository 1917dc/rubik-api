package br.com.rubik_api.entity.imovel;

import br.com.rubik_api.entity.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_imoveis")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(unique = true, nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false, unique = true)
    private String cep;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private int qtdQuartos;
    @Column(nullable = false)
    private int qtdBanheiro;
    @Column(nullable = false)
    private int qtdVagasGaragem;
    @Column(nullable = false)
    private Date dataAquisicao;
    @Column(nullable = false)
    private String registroCartorio;
    @Column(nullable = false)
    private String inscricaoIptu;
    @Column(nullable = false)
    private String inscricaoCaesb;
    @Column(nullable = false)
    private String inscricaoNeoenergia;
    @Column(nullable = false)
    private float valorVenal;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public int getQtdBanheiro() {
        return qtdBanheiro;
    }

    public void setQtdBanheiro(int qtdBanheiro) {
        this.qtdBanheiro = qtdBanheiro;
    }

    public int getQtdVagasGaragem() {
        return qtdVagasGaragem;
    }

    public void setQtdVagasGaragem(int qtdVagasGaragem) {
        this.qtdVagasGaragem = qtdVagasGaragem;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getRegistroCartorio() {
        return registroCartorio;
    }

    public void setRegistroCartorio(String registroCartorio) {
        this.registroCartorio = registroCartorio;
    }

    public String getInscricaoIptu() {
        return inscricaoIptu;
    }

    public void setInscricaoIptu(String inscricaoIptu) {
        this.inscricaoIptu = inscricaoIptu;
    }

    public String getInscricaoCaesb() {
        return inscricaoCaesb;
    }

    public void setInscricaoCaesb(String inscricaoCaesb) {
        this.inscricaoCaesb = inscricaoCaesb;
    }

    public String getInscricaoNeoenergia() {
        return inscricaoNeoenergia;
    }

    public void setInscricaoNeoenergia(String inscricaoNeoenergia) {
        this.inscricaoNeoenergia = inscricaoNeoenergia;
    }

    public float getValorVenal() {
        return valorVenal;
    }

    public void setValorVenal(float valorVenal) {
        this.valorVenal = valorVenal;
    }
}
