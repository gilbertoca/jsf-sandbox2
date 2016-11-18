package jsf.sandbox;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;

@ManagedBean
@ViewScoped
public class CedenteBean {

    private Cedente cedente;

    @PostConstruct
    public void init() {
        cedente = new Cedente("");
    }

    public void salvar() {
        System.out.println("cnpj/cpf: " + cedente.getCPRF().toString());
        System.out.println("nome: " + cedente.getNome());
    }

    public Cedente getCedente() {
        return cedente;
    }

    public void setCedente(Cedente cedente) {
        this.cedente = cedente;
    }

}
