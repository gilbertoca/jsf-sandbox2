package jsf.sandbox.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import jsf.sandbox.model.TituloCobranca;
import jsf.sandbox.service.cnab240.ArquivoFlatFile;
import jsf.sandbox.service.cnab240.DetalheLoteSegmentoP;
import jsf.sandbox.service.cnab240.DetalheLoteSegmentoQ;
import jsf.sandbox.service.cnab240.DetalheLoteSegmentoR;
import jsf.sandbox.service.cnab240.HeaderArquivo;
import jsf.sandbox.service.cnab240.HeaderLote;
import jsf.sandbox.service.cnab240.TrailerArquivo;
import jsf.sandbox.service.cnab240.TrailerLote;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;

public class GerarArquivoRemessaBB {

    private final ArquivoFlatFile templateFile;

    public GerarArquivoRemessaBB() {
        //carrega o template/modelo de remessa do BB
        templateFile = new ArquivoFlatFile(getFileFromResource("leiaute-remessa-banco-brasil240.xml"));
    }

    private File getFileFromResource(String templatePath) {
        return new File(ArquivoFlatFile.class.getResource(templatePath).getFile());
    }

    public void gerarArquivoRemessa(List<TituloCobranca> listaParcelaReceber, Cedente empresa, ContaBancaria conta, File arquivoRemessa) throws Exception {
        if (conta.getAgencia() == null) {
            throw new Exception("Conta não está vinculada a uma agência bancária.\nGeração do arquivo não permitida.");
        }

        //cria um objeto FlatFile
        FlatFile<Record> ff = templateFile.getFlatFile();

        //header do arquivo
        HeaderArquivo headerArquivo = new HeaderArquivo(ff.createRecord("HeaderArquivo"));
        headerArquivo.setTipoInscricaoEmpresa(2);//1-CPF 2-CNPJ
        headerArquivo.setNumeroInscricaoEmpresa(Long.valueOf(empresa.getCnpj()));
        headerArquivo.setCodigoConvenio(conta.getNumeroConvenio());
        headerArquivo.setAgenciaMantenedora(Integer.valueOf(conta.getAgencia()));
        headerArquivo.setDigitoVerificadorAgencia(conta.getAgenciaDigito());
        headerArquivo.setNumeroContaCorrente(Integer.valueOf(conta.getConta()));
        headerArquivo.setDigitoVerificadorContaCorrente(conta.getContaDigito());
        headerArquivo.setDigitoVerificadorAgenciaConta(" ");
        headerArquivo.setNomeEmpresa(empresa.getNome());
        headerArquivo.setNomeBanco("BANCO DO BRASIL S.A.");
        headerArquivo.setCodigoRemessaRetorno(1);//REMESSA = 1; RETORNO = 2;
        headerArquivo.setDataGeracaoArquivo(new Date());
        headerArquivo.setHoraGeracaoArquivo(0);
        headerArquivo.setNumeroSequencialArquivo(0);
        headerArquivo.setNumeroVersaoArquivo(103);
        headerArquivo.setDensidadeGravacaoArquivo(0);
        headerArquivo.setUsoReservadoBanco(" ");
        headerArquivo.setUsoReservadoEmpresa(" ");

        ff.addRecord(headerArquivo.getRecord());

        //header do lote
        HeaderLote headerLote = new HeaderLote(ff.createRecord("HeaderLote"));
        headerLote.setLoteServico(1);
        headerLote.setTipoOperacao("R");
        headerLote.setTipoInscricao(2);//1-CPF 2-CNPJ
        headerLote.setNumeroInscricao(Long.valueOf(empresa.getCnpj()));
        /* 999999999 - nr convenio
         * CC - nr da carteira
         * VVV - nr da variacao da carteira
         */
        headerLote.setCodigoConvenio(conta.getNumeroConvenio() + "0014" + conta.getCarteira());
        headerLote.setAgenciaMantenedora(Integer.valueOf(conta.getAgencia()));
        headerLote.setDigitoVerificadorAgencia(conta.getAgenciaDigito());
        headerLote.setNumeroContaCorrente(Integer.valueOf(conta.getConta()));
        headerLote.setDigitoVerificadorContaCorrente(conta.getContaDigito());
        headerLote.setDigitoVerificadorAgenciaConta(" ");
        headerLote.setNomeEmpresa(empresa.getNome());
        headerLote.setMensagem1(" ");
        headerLote.setMensagem2(" ");
        headerLote.setNumeroRemessaRetorno(0);
        headerLote.setDataGravacao(new Date());
        headerLote.setDataCredito(new Date());

        ff.addRecord(headerLote.getRecord());

        //segmentos P e Q
        DetalheLoteSegmentoP segmentoP;
        DetalheLoteSegmentoQ segmentoQ;
        DetalheLoteSegmentoR segmentoR;
        int sequenciaLote = 0;
        TituloCobranca parcelaReceber;
        for (int i = 0; i < listaParcelaReceber.size(); i++) {
            parcelaReceber = listaParcelaReceber.get(i);
            segmentoP = new DetalheLoteSegmentoP(ff.createRecord("RegistroDetalheSegmentoP"));
            segmentoQ = new DetalheLoteSegmentoQ(ff.createRecord("RegistroDetalheSegmentoQ"));
            segmentoR = new DetalheLoteSegmentoR(ff.createRecord("RegistroDetalheSegmentoR"));

            segmentoP.setLoteServico(1);//deve ser igual ao número do lote
            segmentoP.setNumeroSequencialRegistro(++sequenciaLote);
            segmentoP.setCodigoMovimentoRemessa(1);//ENTRADA
            segmentoP.setAgenciaMantenedora(Integer.valueOf(conta.getAgencia()));
            segmentoP.setDigitoVerificadorAgencia(conta.getAgenciaDigito());
            segmentoP.setNumeroContaCorrente(Integer.valueOf(conta.getConta()));
            segmentoP.setDigitoVerificadorContaCorrente(conta.getContaDigito());
            segmentoP.setDigitoVerificadorAgenciaConta(" ");
            segmentoP.setIdentificadorTitulo(parcelaReceber.getNossoNumero());//nosso número
            segmentoP.setCodigoCarteira(1);
            segmentoP.setFormaCadastroTitulo(0);
            segmentoP.setTipoDocumento(" ");
            segmentoP.setIdentificadorEmissaoBloqueto(0);
            segmentoP.setIdentificacaoDistribuicao(" ");
            segmentoP.setNumeroDocumentoCobranca(parcelaReceber.getNossoNumero().substring(0, 15));
            segmentoP.setDataVencimentoTitulo(parcelaReceber.getDtVencimento());
            segmentoP.setValorNominalTitulo(parcelaReceber.getValor().multiply(BigDecimal.valueOf(100)).longValue());
            segmentoP.setAgenciaEncarregadaCobranca(0);
            segmentoP.setDigitoVerificadorAgenciaCobranca(" ");
            segmentoP.setEspecieTitulo(2);
            if (parcelaReceber.isAceite()) {
                segmentoP.setIdentificadorTituloAceito("A");
            } else {
                segmentoP.setIdentificadorTituloAceito("N");
            }
            segmentoP.setDataEmissaoTitulo(parcelaReceber.getDtEmissao());
            segmentoP.setCodigoJurosMora(3);
            segmentoP.setDataJurosMora(0);
            if (parcelaReceber.getJuros().compareTo(BigDecimal.ZERO) > 0) {
                segmentoP.setJurosMoraDia(parcelaReceber.getJuros().multiply(BigDecimal.valueOf(100)).longValue());
            } else {
                segmentoP.setJurosMoraDia(0l);
            }
            if (parcelaReceber.getDesconto().compareTo(BigDecimal.ZERO) > 0) {
                segmentoP.setCodigoDesconto1(1);
                segmentoP.setDataDesconto1(parcelaReceber.getDtDescontoAte());
                segmentoP.setValorPercentualConcedido(parcelaReceber.getDesconto().multiply(BigDecimal.valueOf(100)).longValue());
            } else {
                segmentoP.setCodigoDesconto1(0);
                segmentoP.setDataDesconto1(new Date());
                segmentoP.setValorPercentualConcedido(0l);
            }
            segmentoP.setValorIOFRecolhido(0l);
            segmentoP.setValorAbatimento(0l);
            segmentoP.setIdentificadorTituloEmpresa(" ");
            segmentoP.setCodigoProtesto(3);
            segmentoP.setNumeroDiasProtesto(0);
            segmentoP.setCodigoBaixaDevolucao(0);
            segmentoP.setNumeroDiasBaixaDevolucao(0);
            segmentoP.setCodigoMoeda(9);
            segmentoP.setNumeroContrato(0);

            segmentoQ.setLoteServico(1);//deve ser igual ao número do lote
            segmentoQ.setTipoRegistro(3);
            segmentoQ.setNumeroSequencialRegistro(++sequenciaLote);
            segmentoQ.setCodigoMovimentoRemessa(1);//ENTRADA
            segmentoQ.setTipoInscricaoSacado(1);//1 - PF | 2 - PJ
            segmentoQ.setNumeroInscricaoSacado(Long.valueOf(parcelaReceber.getSacado().getCpf()));
            segmentoQ.setNome(parcelaReceber.getSacado().getNome());
            segmentoQ.setEndereco(parcelaReceber.getSacado().getLogradouro());
            segmentoQ.setBairro(parcelaReceber.getSacado().getBairro());
            segmentoQ.setCEP(Integer.valueOf(parcelaReceber.getSacado().getCep().substring(0, 5)));
            segmentoQ.setSufixoCEP(Integer.valueOf(parcelaReceber.getSacado().getCep().substring(5, 8)));
            segmentoQ.setCidade(parcelaReceber.getSacado().getCidade());//71060080
            segmentoQ.setUF("TO");
            segmentoQ.setTipoInscricaoSacadoAvalista(0);
            segmentoQ.setNumeroInscricaoSacadoAvalista(0l);
            segmentoQ.setNomeSacadorAvalista(" ");
            segmentoQ.setCodigoBancoCorrespondenteCompe(0);
            segmentoQ.setNossoNumeroBancoCorrespondente(" ");

            segmentoP.getRecord().addInnerRecord(segmentoQ.getRecord());
            segmentoP.getRecord().addInnerRecord(segmentoR.getRecord());
            ff.addRecord(segmentoP.getRecord());
        }

        //trailer de lote
        TrailerLote trailerLote = new TrailerLote(ff.createRecord("TrailerLote"));
        trailerLote.setLoteServico(1);
        trailerLote.setQuantidadeRegistros(sequenciaLote + 2);

        ff.addRecord(trailerLote.getRecord());

        //trailer de arquivo
        TrailerArquivo trailerArquivo = new TrailerArquivo(ff.createRecord("TrailerArquivo"));
        trailerArquivo.setLoteServico(9999);
        trailerArquivo.setQuantidadeLotesArquivo(1);
        trailerArquivo.setQuantidadeRegistrosArquivo(sequenciaLote + 4);
        trailerArquivo.setQuantidadeContasConciliacao(0);

        ff.addRecord(trailerArquivo.getRecord());
        FileUtils.writeLines(arquivoRemessa, ff.write(), System.getProperty("line.separator"));
    }
}
