package br.com.rubik_api.entity.imovel;


import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_despesa_parcela")
public class DespesaParcela {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "despesa_id")
    private ImovelDespesa despesa;
    @Column(nullable = false)
    private int numeroParcela;
    @Column(nullable = false)
    private float valorParcela;
    @Column(nullable = false)
    private Date vencimentoParcela;
    private enum Status {
        PENDENTE,
        PAGO,
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ImovelDespesa getDespesa() {
        return despesa;
    }

    public void setDespesa(ImovelDespesa despesa) {
        this.despesa = despesa;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public float getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(float valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Date getVencimentoParcela() {
        return vencimentoParcela;
    }

    public void setVencimentoParcela(Date vencimentoParcela) {
        this.vencimentoParcela = vencimentoParcela;
    }
}
