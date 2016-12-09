package jsf.sandbox.model;

import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author gilberto.andrade
 */
public class ContaBancaria {
	private String NumeroDoBancoBacen;
	private String agencia;
	private String agenciaDigito;
	private String conta;
	private String contaDigito;
        private String numeroConvenio;
	private String carteira;
	private String modalidade;   
        //mensagens
	private String localPagamento;    
	private String instrucao1;    
	private String instrucao2;    
	private String instrucao3;    
	private String instrucao4;    
	private String instrucao5;    
	private String instrucao6;    

	private Cedente titular;
	
	private Collection<TituloCobranca> titulos;

    public String getNumeroDoBancoBacen() {
        return NumeroDoBancoBacen;
    }

    public void setNumeroDoBancoBacen(String NumeroDoBancoBacen) {
        this.NumeroDoBancoBacen = NumeroDoBancoBacen;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getAgenciaDigito() {
        return agenciaDigito;
    }

    public void setAgenciaDigito(String agenciaDigito) {
        this.agenciaDigito = agenciaDigito;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getContaDigito() {
        return contaDigito;
    }

    public void setContaDigito(String contaDigito) {
        this.contaDigito = contaDigito;
    }

    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    public String getInstrucao1() {
        return instrucao1;
    }

    public void setInstrucao1(String instrucao1) {
        this.instrucao1 = instrucao1;
    }

    public String getInstrucao2() {
        return instrucao2;
    }

    public void setInstrucao2(String instrucao2) {
        this.instrucao2 = instrucao2;
    }

    public String getInstrucao3() {
        return instrucao3;
    }

    public void setInstrucao3(String instrucao3) {
        this.instrucao3 = instrucao3;
    }

    public String getInstrucao4() {
        return instrucao4;
    }

    public void setInstrucao4(String instrucao4) {
        this.instrucao4 = instrucao4;
    }

    public String getInstrucao5() {
        return instrucao5;
    }

    public void setInstrucao5(String instrucao5) {
        this.instrucao5 = instrucao5;
    }

    public String getInstrucao6() {
        return instrucao6;
    }

    public void setInstrucao6(String instrucao6) {
        this.instrucao6 = instrucao6;
    }

    public Cedente getTitular() {
        return titular;
    }

    public void setTitular(Cedente titular) {
        this.titular = titular;
    }

    public Collection<TituloCobranca> getTitulos() {
        return titulos;
    }

    public void setTitulos(Collection<TituloCobranca> titulos) {
        this.titulos = titulos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.agencia);
        hash = 67 * hash + Objects.hashCode(this.agenciaDigito);
        hash = 67 * hash + Objects.hashCode(this.conta);
        hash = 67 * hash + Objects.hashCode(this.contaDigito);
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
        final ContaBancaria other = (ContaBancaria) obj;
        if (!Objects.equals(this.agencia, other.agencia)) {
            return false;
        }
        if (!Objects.equals(this.agenciaDigito, other.agenciaDigito)) {
            return false;
        }
        if (!Objects.equals(this.conta, other.conta)) {
            return false;
        }
        if (!Objects.equals(this.contaDigito, other.contaDigito)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ContaBancaria [");
        builder.append("NumeroDoBancoBacen=").append(NumeroDoBancoBacen);
        builder.append(", agencia=").append(agencia);
        builder.append(", agenciaDigito=").append(agenciaDigito);
        builder.append(", carteira=").append(carteira);
        builder.append(", conta=").append(conta);
        builder.append(", contaDigito=").append(contaDigito);
        builder.append(", numeroConvenio=").append(numeroConvenio);
        builder.append(", instrucao1=").append(instrucao1);
        builder.append(", instrucao2=").append(instrucao2);
        builder.append(", instrucao3=").append(instrucao3);
        builder.append(", instrucao4=").append(instrucao4);
        builder.append(", instrucao5=").append(instrucao5);
        builder.append(", instrucao6=").append(instrucao6);
        builder.append(", localPagamento=").append(localPagamento);
        builder.append(", modalidade=").append(modalidade);
        builder.append("]");
        return builder.toString();
    }
        
}
