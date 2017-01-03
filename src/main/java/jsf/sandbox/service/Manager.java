package jsf.sandbox.service;

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

    public Manager() {
        Cedente cedente = new Cedente("00000000000191", "JRimum Enterprise", "logradouro", "complemento", "bairro", "cidade", "cep");
        cedentes.add(cedente);
        sacados.add(new Sacado("22222222222", "JavaDeveloper Pronto Para Férias", "logradouro", "complemento", "bairro", "cidade", "cep"));
        contas.add(new ContaBancaria("001", "616", "5", "1708027", "8", "2855943", "17", "4", "Em qualquer agência", "instrucao1", "instrucao2", cedente));
    }
    
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
