package jsf.sandbox.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import jsf.sandbox.model.Sacado;
import jsf.sandbox.model.TituloCobranca;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gilberto.andrade
 */
public class GerarArquivoRemessaBBTest {
    
    private File arquivoRemessa;
    private Cedente cedente;
    private Sacado sacado;
    private ContaBancaria contaBancaria;
    private TituloCobranca tituloCobranca;
            
    @Before
    public void setUp() throws ParseException {
        cedente = new Cedente("00000000000191", "JRimum Enterprise", "logradouro", "complemento", "bairro", "cidade", "77015000");
        sacado = new Sacado("22222222222", "JavaDeveloper Pronto Para Férias", "logradouro", "complemento", "bairro", "cidade", "77015000");
        contaBancaria = new ContaBancaria("001", "616", "5", "1708027", "8", "2855943", "17", "4", "Em qualquer agência", "instrucao1", "instrucao2", cedente);
        contaBancaria.setInstrucao3("instrucao3");
        contaBancaria.setInstrucao4("instrucao4");
        contaBancaria.setInstrucao5("instrucao5");
        tituloCobranca = new TituloCobranca();
        tituloCobranca.setId(1);
        String nossoNumero = contaBancaria.getNumeroConvenio() + String.format("%010d", tituloCobranca.getId());
        tituloCobranca.setNossoNumero(nossoNumero);
        tituloCobranca.setDocumento("NP-001");
        tituloCobranca.setAceite(false);
        tituloCobranca.setConta(contaBancaria);
        tituloCobranca.setSacado(sacado);
        tituloCobranca.setTipo("NP");
        tituloCobranca.setDtEmissao(new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2016"));
        tituloCobranca.setDtVencimento(new SimpleDateFormat("dd/MM/yyyy").parse("23/01/2016"));
        tituloCobranca.setValor(new BigDecimal("540.50"));
        
        //o arquivo ficará em target/
        arquivoRemessa = new File("REMESSA-01.REM");
    }
    

    @Test
    public void deveGerarArquivoRemessa() throws Exception {
        List<TituloCobranca> listaTituloCobrancas = Arrays.asList(tituloCobranca);
        Integer nrRemessa = 01;
        String codigoMovimento = "01";
        new GerarArquivoRemessaBB().gerarArquivoRemessa(listaTituloCobrancas, cedente, contaBancaria, arquivoRemessa);
        assertTrue(arquivoRemessa.length() != 0);
    }
}
