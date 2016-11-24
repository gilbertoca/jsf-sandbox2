package jsf.sandbox;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.Modalidade;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;

@ManagedBean
@ViewScoped
public class ContaBancariaBean {
    private Cedente cedente;
    private ContaBancaria contaBancaria;
    private BancosSuportados bancoSuportado;
    private Agencia agencia;
    private NumeroDaConta numeroDaConta;
    private Carteira carteira;
    private Modalidade modalidade;

    @PostConstruct
    public void init() {
        contaBancaria = new ContaBancaria();
        agencia = new Agencia(Integer.SIZE);
    }

    public void salvar() {
        System.out.println("Número da conta: " + contaBancaria.getNumeroDaConta());
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public BancosSuportados getBancoSuportado() {
        return bancoSuportado;
    }

    public void setBancoSuportado(BancosSuportados bancoSuportado) {
        this.bancoSuportado = bancoSuportado;
    }

    public Cedente getCedente() {
        return cedente;
    }

    public void setCedente(Cedente cedente) {
        this.cedente = cedente;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public NumeroDaConta getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(NumeroDaConta numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public BancosSuportados[] getBancos() {
        return BancosSuportados.values();
    }    
}
