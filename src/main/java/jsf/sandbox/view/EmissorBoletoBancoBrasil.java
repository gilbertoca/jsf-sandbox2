package jsf.sandbox.view;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import jsf.sandbox.service.Manager;
import java.io.File;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import jsf.sandbox.model.TituloCobranca;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

/**
 *
 * @author gilberto.andrade
 */
@Dependent
@Named
public class EmissorBoletoBancoBrasil implements Serializable {

    @Inject
    private Manager gerente;

    public byte[] gerar(TituloCobranca tituloCobranca) {

        Datas datas = Datas.novasDatas()
                .comDocumento(1, 5, 2008)
                .comProcessamento(1, 5, 2008)
                .comVencimento(2, 5, 2008);

        Endereco enderecoBeneficiario = Endereco.novoEndereco()
                .comLogradouro("Av das Empresas, 555")
                .comBairro("Bairro Grande")
                .comCep("01234-555")
                .comCidade("São Paulo")
                .comUf("SP");

        //Quem emite o boleto
        Beneficiario beneficiario = Beneficiario.novoBeneficiario()
                .comNomeBeneficiario("Fulano de Tal")
                .comAgencia("1824").comDigitoAgencia("4")
                .comCodigoBeneficiario("76000")
                .comDigitoCodigoBeneficiario("5")
                .comNumeroConvenio("1207113")
                .comCarteira("18")
                .comEndereco(enderecoBeneficiario)
                .comNossoNumero("9000206");

        Endereco enderecoPagador = Endereco.novoEndereco()
                .comLogradouro("Av dos testes, 111 apto 333")
                .comBairro("Bairro Teste")
                .comCep("01234-111")
                .comCidade("São Paulo")
                .comUf("SP");

        //Quem paga o boleto
        Pagador pagador = Pagador.novoPagador()
                .comNome("Fulano da Silva")
                .comDocumento("111.222.333-12")
                .comEndereco(enderecoPagador);

        Banco banco = new BancoDoBrasil();

        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comBeneficiario(beneficiario)
                .comPagador(pagador)
                .comValorBoleto("200.00")
                .comNumeroDoDocumento("1234")
                .comInstrucoes("instrucao 1", "instrucao 2", "instrucao 3", "instrucao 4", "instrucao 5")
                .comLocaisDePagamento("local 1", "local 2");

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

        /**
         *
         *
         * ANEXO IX – COMPOSIÇÃO DO CAMPO “NOSSO NÚMERO” – CONVÊNIO DE 7
         * POSIÇÕES O nosso número do boleto deve estar de acordo com as normas
         * estabelecidas pelo Banco Brasil.
         *
         * FORMATO “NOSSO NÚMERO” PARA CONVÊNIOS ACIMA DE 1.000.000 (UM MILHÃO):
         * A composição do nosso número deve obedecer as seguintes regras:
         * CCCCCCCNNNNNNNNNN convênios com numeração acima de 1.000.000, onde:
         * "C" - é o número do convênio fornecido pelo Banco (número fixo e não
         * pode ser alterado) "N" - é um sequencial atribuído pelo cliente
         *
         * Nosso Número de 17 posições
         * tituloCobranca.getConta().getNumeroConvenio()+tituloCobranca.getId()+
         */
//        titulo.setNossoNumero(tituloCobranca.getConta().getNumeroConvenio() + String.format("%010d", tituloCobranca.getId()));
//
//        titulo.setNumeroDoDocumento(tituloCobranca.getId().toString());
//        titulo.setValor(tituloCobranca.getValor());
//        titulo.setDataDoDocumento(new Date());
//        titulo.setDataDoVencimento(tituloCobranca.getDtVencimento());
//        //tituloCobranca.getTipo()
//        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
//        titulo.setAceite(tituloCobranca.isAceite() == true ? Titulo.Aceite.A : Titulo.Aceite.N);
        //boletoViewer.setImprimeReciboEntrega(true);
        return gerador.geraPDF();
    }

    public File gerarEmArquivo(String arquivo, TituloCobranca tituloCobranca) {
        //BoletoViewer boletoViewer = new BoletoViewer(boleto);
        //return boletoViewer.getPdfAsFile(arquivo);
        return null;
    }

    public Manager getGerente() {
        return gerente;
    }

    public void setGerente(Manager gerente) {
        this.gerente = gerente;
    }

}
