package jsf.sandbox.view;

import java.io.Serializable;
import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jrimum.bopepo.BancosSuportados;

@Named
@ViewScoped
public class ContaBancariaController implements Serializable {
    
    @Inject
    private Manager gerente;    
    private Cedente cedente;
    private ContaBancaria conta = new ContaBancaria();
    private BancosSuportados bancoSuportado = BancosSuportados.BANCO_DO_BRASIL;

    public void salvar() {
        conta.setNumeroDoBancoBacen(bancoSuportado.getCodigoDeCompensacao());
        conta.setTitular(cedente);
        System.out.println(conta + " Banco Suportado: " + bancoSuportado);
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
