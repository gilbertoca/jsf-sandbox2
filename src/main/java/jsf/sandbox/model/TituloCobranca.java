package jsf.sandbox.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gilberto.andrade
 */
public class TituloCobranca {
    private Integer id;
    private ContaBancaria conta;
    private Sacado sacado;
    private String tipo = "DM";
    private boolean aceite = false;
    private String documento;
    private String nossoNumero;
    private Date dtEmissao;
    private Date dtVencimento;
    private Date dtDescontoAte;
    private BigDecimal valor = BigDecimal.ZERO;
    private BigDecimal desconto = BigDecimal.ZERO;
    private BigDecimal juros = BigDecimal.ZERO;
    private BigDecimal multa = BigDecimal.ZERO;
    private Situacao situacao = Situacao.PENDENTE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }

    public Sacado getSacado() {
        return sacado;
    }

    public void setSacado(Sacado sacado) {
        this.sacado = sacado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAceite() {
        return aceite;
    }

    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public Date getDtDescontoAte() {
        return dtDescontoAte;
    }

    public void setDtDescontoAte(Date dtDescontoAte) {
        this.dtDescontoAte = dtDescontoAte;
    }
    
    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }
    
    public BigDecimal getJuros() {
        return juros;
    }

    public void setJuros(BigDecimal juros) {
        this.juros = juros;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TituloCobranca other = (TituloCobranca) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TituloCobranca [");
        builder.append("aceite=").append(aceite);
        builder.append(", conta=").append(conta.getConta());
        builder.append(", documento=").append(documento);
        builder.append(", dtEmissao=").append(dtEmissao);
        builder.append(", dtVencimento=").append(dtVencimento);
        builder.append(", dtDescontoAte=").append(dtDescontoAte);
        builder.append(", nossoNumero=").append(nossoNumero);
        builder.append(", sacado=").append(sacado.getNome());
        builder.append(", situacao=").append(situacao);
        builder.append(", tipo=").append(tipo);
        builder.append(", juros=").append(juros);
        builder.append(", multa=").append(multa);
        builder.append(", desconto=").append(desconto);
        builder.append(", valor=").append(valor);
        builder.append("]");
        return builder.toString();
    }
    
    
    
}
