package jsf.sandbox.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import jsf.sandbox.model.Sacado;

/**
 *
 * @author gilberto.andrade
 */
@ManagedBean
@SessionScoped
public class Manager implements Serializable{
    private List<Cedente> cedentes = new ArrayList<>();
    private List<Sacado> sacados = new ArrayList<>();
    private List<ContaBancaria> contas = new ArrayList<>();

    public List<Cedente> getCedentes() {
        return cedentes;
    }

    public void setCedentes(List<Cedente> cedentes) {
        this.cedentes = cedentes;
    }

    public List<Sacado> getSacados() {
        return sacados;
    }

    public void setSacados(List<Sacado> sacados) {
        this.sacados = sacados;
    }

    public List<ContaBancaria> getContas() {
        return contas;
    }

    public void setContas(List<ContaBancaria> contas) {
        this.contas = contas;
    }
}
