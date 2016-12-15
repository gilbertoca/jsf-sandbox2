package jsf.sandbox.view;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import jsf.sandbox.model.TituloCobranca;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

@Named
@ViewScoped
public class TituloController implements Serializable {

    @Inject
    private Manager gerente;
    private TituloCobranca tituloCobranca = new TituloCobranca();
    private boolean edit = false;
    private int indexOf;
    @Inject
    private EmissorBoletoBancoBrasil emissorBoleto;

    public void adicionar() {
        // dao.create(item);
        System.out.println("Total Titulos BEFORE ADD" + gerente.getTitulos().size());
        System.out.println("Add titulo " + tituloCobranca);

        tituloCobranca.setId(gerente.getTitulos().isEmpty() ? 1 : gerente.getTitulos().get(gerente.getTitulos().size() - 1).getId() + 1);
        gerente.getTitulos().add(tituloCobranca);
        tituloCobranca = new TituloCobranca(); // Reset placeholder.
        edit = false;

        System.out.println("Total Titulos AFTER ADD" + gerente.getTitulos().size());
    }

    public void editar(TituloCobranca titulo) {
        System.out.println("titulo passado como parametro" + titulo);
        indexOf = gerente.getTitulos().indexOf(titulo);
        tituloCobranca = gerente.getTitulos().get(indexOf);
        edit = true;
    }

    public void salvar() {
        // dao.update(tituloCobranca);

        System.out.println("titulo obtido com indexOf" + tituloCobranca);
        gerente.getTitulos().set(indexOf,tituloCobranca);
        tituloCobranca = new TituloCobranca(); // Reset placeholder.
        edit = false;
    }

    public void deletar(TituloCobranca titulo) {
        // dao.deletar(tituloCobranca);
        gerente.getTitulos().remove(titulo);
        edit = false;
    }

    public void emitir(TituloCobranca titulo) {
        byte[] pdf = this.emissorBoleto.gerar(titulo);
        enviarBoleto(pdf);
    }

    private void enviarBoleto(byte[] pdf) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=boleto" + tituloCobranca.getId() + ".pdf");

        try {
            OutputStream output = response.getOutputStream();
            output.write(pdf);
            response.flushBuffer();

        } catch (Exception e) {
            throw new RuntimeException("Erro gerando boleto", e);

        }

        FacesContext.getCurrentInstance().responseComplete();

    }

    public List<TituloCobranca> getTitulos() {
        System.out.println("Total Titulos " + gerente.getTitulos().size());
        return gerente.getTitulos();
    }

    public TituloCobranca getTituloCobranca() {
        return tituloCobranca;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setTituloCobranca(TituloCobranca titulo) {
        this.tituloCobranca = titulo;
    }

    public Manager getGerente() {
        return gerente;
    }

    public void setGerente(Manager gerente) {
        this.gerente = gerente;
    }

    public EmissorBoletoBancoBrasil getEmissorBoleto() {
        return emissorBoleto;
    }

    public void setEmissorBoleto(EmissorBoletoBancoBrasil emissorBoleto) {
        this.emissorBoleto = emissorBoleto;
    }
}
