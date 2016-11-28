package jsf.sandbox.model;

import java.util.Collection;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

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

	private Collection<Cedente> titulares;
	
	private Collection<Titulo> titulos;

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

    public Collection<Cedente> getTitulares() {
        return titulares;
    }

    public void setTitulares(Collection<Cedente> titulares) {
        this.titulares = titulares;
    }

    public Collection<Titulo> getTitulos() {
        return titulos;
    }

    public void setTitulos(Collection<Titulo> titulos) {
        this.titulos = titulos;
    }
        
}
