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
    @OneToOne
    @JoinColumn(name = "imovel_id", unique = true, nullable = false)
    private Imovel imovel;
    private String tipo;
    private float valor;
    private Date vencimento;
    private enum status {
        PENDENTE,
        PAGO,
    };

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
