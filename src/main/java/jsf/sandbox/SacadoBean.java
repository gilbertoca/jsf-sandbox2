package jsf.sandbox;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;

@ManagedBean
@ViewScoped
public class SacadoBean {

    private Sacado sacado;
    private Endereco endereco;

    @PostConstruct
    public void init() {
        sacado = new Sacado("");
    }

    public void salvar() {
        System.out.println("cnpj/cpf: " + sacado.getCPRF().toString());
        System.out.println("nome: " + sacado.getNome());
    }

    public Sacado getSacado() {
        return sacado;
    }

    public void setSacado(Sacado sacado) {
        this.sacado = sacado;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
