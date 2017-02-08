package jsf.sandbox.service.cnab240;

import java.util.Date;
import org.jrimum.texgit.Record;
import org.jrimum.utilix.Objects;

public class HeaderLote {

    private Record record;

    public HeaderLote() {
    }

    public HeaderLote(Record record) {
        Objects.checkNotNull(record, "Registro a ser lido nulo!");
        this.record = record;
    }

    public Integer getCodigoBanco() {
        return record.getValue("CodigoBanco");
    }

    public void setCodigoBanco(Integer codigoBanco) {
        record.setValue("CodigoBanco", codigoBanco);
    }

    public Integer getLoteServico() {
        return record.getValue("LoteServico");
    }

    public void setLoteServico(Integer loteServico) {
        record.setValue("LoteServico", loteServico);
    }

    public String getTipoOperacao() {
        return record.getValue("TipoOperacao");
    }

    public void setTipoOperacao(String tipoOperacao) {
        record.setValue("TipoOperacao", tipoOperacao);
    }

    public Integer getTipoServico() {
        return record.getValue("TipoServico");
    }

    public void setTipoServico(Integer tipoServico) {
        record.setValue("TipoServico", tipoServico);
    }

    public String getCNAB1() {
        return record.getValue("CNAB1");
    }

    public void setCNAB1(String cNAB1) {
        record.setValue("CNAB1", cNAB1);
    }

    public Integer getLayoutLote() {
        return record.getValue("LayoutLote");
    }

    public void setLayoutLote(Integer layoutLote) {
        record.setValue("LayoutLote", layoutLote);
    }

    public String getCNAB2() {
        return record.getValue("CNAB2");
    }

    public void setCNAB2(String cNAB2) {
        record.setValue("CNAB2", cNAB2);
    }

    public Integer getTipoInscricao() {
        return record.getValue("TipoInscricao");
    }

    public void setTipoInscricao(Integer tipoInscricao) {
        record.setValue("TipoInscricao", tipoInscricao);
    }

    public Long getNumeroInscricao() {
        return record.getValue("NumeroInscricao");
    }

    public void setNumeroInscricao(Long numeroInscricao) {
        record.setValue("NumeroInscricao", numeroInscricao);
    }

    public String getCodigoConvenio() {
        return record.getValue("CodigoConvenio");
    }

    public void setCodigoConvenio(String codigoConvenio) {
        record.setValue("CodigoConvenio", codigoConvenio);
    }

    public Integer getAgenciaMantenedora() {
        return record.getValue("AgenciaMantenedora");
    }

    public void setAgenciaMantenedora(Integer agenciaMantenedora) {
        record.setValue("AgenciaMantenedora", agenciaMantenedora);
    }

    public String getDigitoVerificadorAgencia() {
        return record.getValue("DigitoVerificadorAgencia");
    }

    public void setDigitoVerificadorAgencia(String digitoVerificadorAgencia) {
        record.setValue("DigitoVerificadorAgencia", digitoVerificadorAgencia);
    }

    public Integer getNumeroContaCorrente() {
        return record.getValue("NumeroContaCorrente");
    }

    public void setNumeroContaCorrente(Integer numeroContaCorrente) {
        record.setValue("NumeroContaCorrente", numeroContaCorrente);
    }

    public String getDigitoVerificadorContaCorrente() {
        return record.getValue("DigitoVerificadorContaCorrente");
    }

    public void setDigitoVerificadorContaCorrente(String digitoVerificadorContaCorrente) {
        record.setValue("DigitoVerificadorContaCorrente", digitoVerificadorContaCorrente);
    }

    public String getDigitoVerificadorAgenciaConta() {
        return record.getValue("DigitoVerificadorAgenciaConta");
    }

    public void setDigitoVerificadorAgenciaConta(String digitoVerificadorAgenciaConta) {
        record.setValue("DigitoVerificadorAgenciaConta", digitoVerificadorAgenciaConta);
    }

    public String getNomeEmpresa() {
        return record.getValue("NomeEmpresa");
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        record.setValue("NomeEmpresa", nomeEmpresa);
    }

    public String getMensagem1() {
        return record.getValue("Mensagem1");
    }

    public void setMensagem1(String mensagem1) {
        record.setValue("Mensagem1", mensagem1);
    }

    public String getMensagem2() {
        return record.getValue("Mensagem2");
    }

    public void setMensagem2(String mensagem2) {
        record.setValue("Mensagem2", mensagem2);
    }

    public Integer getNumeroRemessaRetorno() {
        return record.getValue("NumeroRemessaRetorno");
    }

    public void setNumeroRemessaRetorno(Integer numeroRemessaRetorno) {
        record.setValue("NumeroRemessaRetorno", numeroRemessaRetorno);
    }

    public Date getDataGravacao() {
        return record.getValue("DataGravacao");
    }

    public void setDataGravacao(Date dataGravacao) {
        record.setValue("DataGravacao", dataGravacao);
    }

    public Date getDataCredito() {
        return record.getValue("DataCredito");
    }

    public void setDataCredito(Date dataCredito) {
        record.setValue("DataCredito", dataCredito);
    }

    public String getCNAB3() {
        return record.getValue("CNAB3");
    }

    public void setCNAB3(String cNAB3) {
        record.setValue("CNAB3", cNAB3);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HeaderLote [");
        builder.append("getAgenciaMantenedora=").append(getAgenciaMantenedora());
        builder.append(", getCNAB1=").append(getCNAB1());
        builder.append(", getCNAB2=").append(getCNAB2());
        builder.append(", getCNAB3=").append(getCNAB3());
        builder.append(", getCodigoBanco=").append(getCodigoBanco());
        builder.append(", getCodigoConvenio=").append(getCodigoConvenio());
        builder.append(", getDataCredito=").append(getDataCredito());
        builder.append(", getDataGravacao=").append(getDataGravacao());
        builder.append(", getDigitoVerificadorAgencia=").append(getDigitoVerificadorAgencia());
        builder.append(", getDigitoVerificadorAgenciaConta=").append(getDigitoVerificadorAgenciaConta());
        builder.append(", getDigitoVerificadorContaCorrente=").append(getDigitoVerificadorContaCorrente());
        builder.append(", getLayoutLote=").append(getLayoutLote());
        builder.append(", getLoteServico=").append(getLoteServico());
        builder.append(", getMensagem1=").append(getMensagem1());
        builder.append(", getMensagem2=").append(getMensagem2());
        builder.append(", getNomeEmpresa=").append(getNomeEmpresa());
        builder.append(", getNumeroContaCorrente=").append(getNumeroContaCorrente());
        builder.append(", getNumeroInscricao=").append(getNumeroInscricao());
        builder.append(", getNumeroRemessaRetorno=").append(getNumeroRemessaRetorno());
        builder.append(", getTipoInscricao=").append(getTipoInscricao());
        builder.append(", getTipoOperacao=").append(getTipoOperacao());
        builder.append(", getTipoServico=").append(getTipoServico());
        builder.append("]");
        return builder.toString();
    }
    
}
