package jsf.sandbox.view;

import jsf.sandbox.service.Manager;
import java.io.Serializable;
import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ContaBancariaController implements Serializable {
    
    @Inject
    private Manager gerente;    
    private Cedente cedente;
    private ContaBancaria conta = new ContaBancaria();
    private String bancoSuportado = "BANCO DO BRASIL S.A.";

    public void salvar() {
        conta.setNumeroDoBancoBacen("001");
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

    public Cedente getCedente() {
        return cedente;
    }

    public void setCedente(Cedente cedente) {
        this.cedente = cedente;
    }

    public Manager getGerente() {
        return gerente;
    }

    public void setGerente(Manager gerente) {
        this.gerente = gerente;
    }

    public String getBancoSuportado() {
        return bancoSuportado;
    }

    public void setBancoSuportado(String bancoSuportado) {
        this.bancoSuportado = bancoSuportado;
    }
}
