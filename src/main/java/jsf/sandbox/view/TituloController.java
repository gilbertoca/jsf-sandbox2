package jsf.sandbox.view;

import java.util.List;
import jsf.sandbox.model.TituloCobranca;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class TituloController {
    
    @ManagedProperty("#{manager}")
    private Manager gerente;
    private TituloCobranca tituloCobranca = new TituloCobranca();
    private boolean edit;

    public void add() {
        // dao.create(item);
        tituloCobranca.setId(gerente.getTitulos().isEmpty() ? 1 : gerente.getTitulos().get(gerente.getTitulos().size() - 1).getId() + 1);
        gerente.getTitulos().add(tituloCobranca);
        tituloCobranca = new TituloCobranca(); // Reset placeholder.
    }

    public void edit(TituloCobranca tituloCobranca) {
        this.tituloCobranca = gerente.getTitulos().get(gerente.getTitulos().indexOf(tituloCobranca));
        edit = true;
    }

    public void save() {
        // dao.update(tituloCobranca);
        tituloCobranca = new TituloCobranca(); // Reset placeholder.
        edit = false;
    }

    public void delete(TituloCobranca tituloCobranca) {
        // dao.delete(tituloCobranca);
        gerente.getTitulos().remove(tituloCobranca);
    }

    public List<TituloCobranca> getTitulos() {
        return gerente.getTitulos();
    }

    public TituloCobranca getTituloCobranca() {
        return tituloCobranca;
    }

    public boolean isEdit() {
        return edit;
    }
    
    public void setTituloCobranca(TituloCobranca tituloCobranca) {
        this.tituloCobranca = tituloCobranca;
    }
    
    public Manager getGerente() {
        return gerente;
    }

    public void setGerente(Manager gerente) {
        this.gerente = gerente;
    }
    
}
