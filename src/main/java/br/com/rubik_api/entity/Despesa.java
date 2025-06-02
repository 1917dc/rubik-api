package br.com.rubik_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_despesa_imovel")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // join column para reforcar atributos no banco de dados
    @ManyToOne
    @JoinColumn(name = "imovel_id", nullable = false)
    @JsonBackReference
    private Imovel imovel;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private float valor;
    @Column(nullable = false)
    private LocalDate vencimento;
    @Column(nullable = true)
    private int parcelas;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    public enum Status {
        PENDENTE,
        PAGO
    }

    public Despesa() {
    }

    public Despesa(Imovel imovel, String tipo, float valor, LocalDate vencimento, int parcelas, Status status) {
        this.imovel = imovel;
        this.tipo = tipo;
        this.valor = valor;
        this.vencimento = vencimento;
        this.parcelas = parcelas;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
