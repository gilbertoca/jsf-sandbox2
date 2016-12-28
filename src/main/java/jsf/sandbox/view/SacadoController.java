package jsf.sandbox.view;

import jsf.sandbox.service.Manager;
import java.io.Serializable;
import jsf.sandbox.model.Sacado;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SacadoController implements Serializable {
    @Inject
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
