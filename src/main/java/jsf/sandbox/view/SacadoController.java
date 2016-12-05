package jsf.sandbox.view;

import jsf.sandbox.model.Sacado;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class SacadoController {
    @ManagedProperty("#{manager}")
    private Manager gerente;
    private Sacado sacado = new Sacado();

    public void salvar() {
        System.out.println(sacado);
        gerente.getSacados().add(sacado);
    }

    public Sacado getSacado() {
        return sacado;
    }

    public void setSacado(Sacado sacado) {
        this.sacado = sacado;
    }

    public Manager getGerente() {
        return gerente;
    }

    public void setGerente(Manager gerente) {
        this.gerente = gerente;
    }
    

}
