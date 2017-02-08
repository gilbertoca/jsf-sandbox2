package jsf.sandbox.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import jsf.sandbox.model.Sacado;
import jsf.sandbox.model.TituloCobranca;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;

public class Remessa240BB {

    public static final String LEIAUTE_BB = "BBCNAB240.xml";
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
    private final DecimalFormat decimalFormat = new DecimalFormat("###########00");
    private Cedente empresaLogada;
    private ContaBancaria conta;
        private Integer nrRemessa;
    private int i = 0;
    
    public void geraRemessa(Cedente empresaLogada, List<TituloCobranca> listaTituloCobrancas,
            ContaBancaria conta, File arquivoRemessa, Integer nrRemessa, String codigoMovimento) throws IOException {

        this.empresaLogada = empresaLogada;
        this.conta = conta;
        this.nrRemessa = nrRemessa;

        File layout = new File(URLDecoder.decode(ClassLoaders.getResource(LEIAUTE_BB).getFile(), "UTF-8"));
        FlatFile<Record> ff = Texgit.createFlatFile(layout);

        i = 0;
        ff.addRecord(createHeader(ff, i));

        ff.addRecord(createHeaderLote(ff, i));
        i++;

        try {
            for (TituloCobranca titulo : listaTituloCobrancas) {
                ff.addRecord(createDetailSegmentoP(ff, titulo, i));
                i++;
            }
            i++;
            ff.addRecord(createTrailerLote(ff, i));
            i++;
            i++;//Soma mais um porque tem que contar o cabeçalho do arquivo. 
            ff.addRecord(createTrailer(ff, i));

            FileUtils.writeLines(arquivoRemessa, ff.write(), "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Record createHeader(FlatFile<Record> ff, int seq) {

        Record headerArquivo = ff.createRecord("HeaderArquivo");
        ContaBancaria cb = this.conta;

        headerArquivo.setValue("TipoInscricaoEmpresa", "2");
        headerArquivo.setValue("UsoExclusivo", StringUtils.repeat(" ", 9));
        headerArquivo.setValue("NumInscricaoEmpresa", this.empresaLogada.getCnpj());
        headerArquivo.setValue("NumConvenio", StringUtils.leftPad(cb.getNumeroConvenio(), 9, "0") + "0014" + cb.getCarteira() + "019" + StringUtils.repeat(" ", 2));
        headerArquivo.setValue("NumAgencia", cb.getAgencia());
        headerArquivo.setValue("DigAgencia", cb.getAgenciaDigito());
        headerArquivo.setValue("NumContaCorrente", StringUtils.substring("" + cb.getConta(), 0, 5));
        headerArquivo.setValue("DigContaCorrente", cb.getContaDigito());
        headerArquivo.setValue("DigAgenciaConta", StringUtils.repeat(" ", 1));
        headerArquivo.setValue("NomeCedente", StringUtils.substring(this.empresaLogada.getNome(), 0, 30));
        headerArquivo.setValue("Brancos3", StringUtils.repeat(" ", 10));
        headerArquivo.setValue("DataGeracao", dateFormat.format(new Date()));
        headerArquivo.setValue("HoraGeracao", timeFormat.format(new Date()));
        headerArquivo.setValue("NumRemessa", this.nrRemessa);
        headerArquivo.setValue("DensidadeArquivo", StringUtils.repeat("0", 5));
        headerArquivo.setValue("Brancos4", StringUtils.repeat(" ", 20));
        headerArquivo.setValue("Brancos5", StringUtils.repeat(" ", 20));
        headerArquivo.setValue("Brancos6", StringUtils.repeat(" ", 29));

        return headerArquivo;
    }

    public Record createHeaderLote(FlatFile<Record> ff, int seq) {
        Record headerLote = ff.createRecord("HeaderLote");
        ContaBancaria cb = this.conta;

        headerLote.setValue("UsoExclusivo", StringUtils.repeat(" ", 2));
        headerLote.setValue("UsoExclusivo1", " ");
        headerLote.setValue("TipoInscricaoEmpresa", "2");
        headerLote.setValue("NumInscricaoEmpresa", this.empresaLogada.getCnpj());
        headerLote.setValue("NumConvenio", StringUtils.leftPad(cb.getNumeroConvenio(), 9, "0") + "0014" + cb.getCarteira() + "019" + StringUtils.repeat(" ", 2));
        headerLote.setValue("NumAgencia", cb.getAgencia());
        headerLote.setValue("DigAgencia", cb.getAgenciaDigito());
        headerLote.setValue("NumContaCorrente", StringUtils.substring("" + cb.getConta(), 0, 5));
        headerLote.setValue("DigContaCorrente", cb.getContaDigito());
        headerLote.setValue("DigAgenciaConta", StringUtils.repeat(" ", 1));
        headerLote.setValue("NomeCedente", StringUtils.substring(this.empresaLogada.getNome(), 0, 30));
        headerLote.setValue("Mensagem1", StringUtils.repeat(" ", 40));
        headerLote.setValue("Mensagem2", StringUtils.repeat(" ", 40));
        headerLote.setValue("NumRemessa", this.nrRemessa);
        headerLote.setValue("DataGeracao", dateFormat.format(new Date()));

        headerLote.setValue("UsoExclusivo2", StringUtils.repeat(" ", 33));

        return headerLote;
    }

    public Record createTrailerLote(FlatFile<Record> ff, int seq) {
        Record trailerLote = ff.createRecord("TraillerLote");
        trailerLote.setValue("QtdRegistros", i);
        trailerLote.setValue("UsoExclusivo1", StringUtils.repeat(" ", 9));
        trailerLote.setValue("UsoExclusivo2", StringUtils.repeat(" ", 217));

        return trailerLote;
    }

    public Record createTrailer(FlatFile<Record> ff, int seq) {
        Record trailerArquivo = ff.createRecord("TraillerArquivo");
        trailerArquivo.setValue("UsoExclusivo1", StringUtils.repeat(" ", 9));
        trailerArquivo.setValue("QtdLotes", "1");
        trailerArquivo.setValue("QtdArquivos", i);
        trailerArquivo.setValue("QtdContas", 0);
        trailerArquivo.setValue("UsoExclusivo2", StringUtils.repeat(" ", 205));
        return trailerArquivo;
    }

    public Record createDetailSegmentoP(FlatFile<Record> ff, TituloCobranca titulo, int seq) {
        Record segmentoP = ff.createRecord("SegmentoP");
        ContaBancaria cb = this.conta;

        segmentoP.setValue("NumSeqRegistro", i);
        segmentoP.setValue("NumAgencia", cb.getAgencia());
        segmentoP.setValue("DigAgencia", cb.getAgenciaDigito());
        segmentoP.setValue("NumContaCorrente", cb.getConta());
        segmentoP.setValue("DigContaCorrente", cb.getContaDigito());
        segmentoP.setValue("DigAgenciaConta", " ");
        segmentoP.setValue("NossoNumero", titulo.getNossoNumero());
        segmentoP.setValue("CodCarteira", "4");// 4 – para carteira 11/17 modalidade Descontada
        segmentoP.setValue("NumeroDoDocumento", titulo.getDocumento());
        segmentoP.setValue("Vencimento", titulo.getDtVencimento());
        segmentoP.setValue("Valor", decimalFormat.format(titulo.getValor()));
        String strAceite;
        if (titulo.isAceite()) {
            strAceite = "A";
        } else {
            strAceite = "N";
        }
        segmentoP.setValue("Aceite", strAceite);
        segmentoP.setValue("Emissao", titulo.getDtEmissao());
        segmentoP.setValue("CodMora", 1);
        segmentoP.setValue("JurosDeMora", decimalFormat.format(titulo.getJuros()));
        segmentoP.setValue("CodigoProtesto", "3");// NÃO Protestar
        segmentoP.setValue("DiasProtesto", "00");// NÃO Protestar

        i++;
        segmentoP.addInnerRecord(createDetailSegmentoQ(ff, titulo, i));

        i++;
        segmentoP.addInnerRecord(createDetailSegmentoR(ff, titulo, i));

        return segmentoP;
    }

    private Record createDetailSegmentoR(FlatFile<Record> ff, TituloCobranca titulo, int seq) {
        Record segmentoR = ff.createRecord("SegmentoR");
        segmentoR.setValue("NumSeqRegistro", i);

        int codMulta;
        String dataMulta;
        BigDecimal valorMulta;

        if (titulo.getMulta().compareTo(BigDecimal.ZERO) > 0) {
            codMulta = 1;
            dataMulta = dateFormat.format(titulo.getDtVencimento());
            valorMulta = titulo.getMulta();
        } else {
            codMulta = 0;
            dataMulta = "00000000";
            valorMulta = BigDecimal.ZERO;
        }
        segmentoR.setValue("CodMulta", codMulta);
        segmentoR.setValue("DataMulta", dataMulta);
        segmentoR.setValue("ValorMulta", valorMulta);
        return segmentoR;
    }

    public Record createDetailSegmentoQ(FlatFile<Record> ff, TituloCobranca titulo, int seq) {

        Record segmentoQ = ff.createRecord("SegmentoQ");
        Sacado pessoa = titulo.getSacado();
        segmentoQ.setValue("NumSeqRegistro", i);
        segmentoQ.setValue("TipoInscricaoSacado", "1");
        segmentoQ.setValue("NumeroInscricaoSacado", pessoa.getCpf());
        segmentoQ.setValue("NomeSacado", pessoa.getNome());
        segmentoQ.setValue("LogradouroSacado", pessoa.getLogradouro());
        segmentoQ.setValue("BairroSacado", pessoa.getBairro());
        segmentoQ.setValue("CepSacado", pessoa.getCep().split("-")[0]);
        segmentoQ.setValue("SufixoCep", pessoa.getCep().split("-")[1]);
        segmentoQ.setValue("Cidade", pessoa.getCidade());
        segmentoQ.setValue("Estado", "TO");
        return segmentoQ;

    }
}
