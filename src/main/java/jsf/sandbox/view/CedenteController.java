package jsf.sandbox.view;

import jsf.sandbox.model.Cedente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class CedenteController {
    
    @ManagedProperty("#{manager}")
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
