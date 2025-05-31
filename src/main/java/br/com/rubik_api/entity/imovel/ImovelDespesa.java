package br.com.rubik_api.entity.imovel;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_despesa_imovel")
public class ImovelDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // join column para reforcar atributos no banco de dados
    @ManyToOne
    @JoinColumn(name = "imovel_id", nullable = false)
    private Imovel imovel;
    private String tipo;
    private float valor;
    private Date vencimento;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        PENDENTE,
        PAGO
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

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }
}
