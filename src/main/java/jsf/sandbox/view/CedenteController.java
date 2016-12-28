package jsf.sandbox.view;

import jsf.sandbox.service.Manager;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import jsf.sandbox.model.Cedente;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CedenteController implements Serializable {
    
    @Inject
    private Manager gerente;
    private Cedente cedente = new Cedente();

    public void salvar() {
        System.out.println(cedente);
        gerente.getCedentes().add(cedente);
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
    
}
