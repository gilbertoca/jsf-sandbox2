package jsf.sandbox.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import jsf.sandbox.model.Sacado;
import jsf.sandbox.model.TituloCobranca;

/**
 *
 * @author gilberto.andrade
 */
@Named
@SessionScoped
public class Manager implements Serializable {
    private List<Cedente> cedentes = new ArrayList<>();
    private List<Sacado> sacados = new ArrayList<>();
    private List<ContaBancaria> contas = new ArrayList<>();
    private List<TituloCobranca> titulos = new ArrayList<>();

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

    public List<TituloCobranca> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<TituloCobranca> titulos) {
        this.titulos = titulos;
    }
    
}
