package jsf.sandbox.view;

import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import org.jrimum.bopepo.BancosSuportados;

@ManagedBean
@ViewScoped
public class ContaBancariaController {
    
    @ManagedProperty("#{manager}")
    private Manager gerente;    
    private Cedente cedente;
    private ContaBancaria conta = new ContaBancaria();
    private BancosSuportados bancoSuportado;

    public void salvar() {
        System.out.println("Número da conta: " + conta.getConta());
        gerente.getContas().add(conta);
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
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

    public BancosSuportados[] getBancos() {
        return BancosSuportados.values();
    }    
    public Manager getGerente() {
        return gerente;
    }

    public void setGerente(Manager gerente) {
        this.gerente = gerente;
    }
        
}
