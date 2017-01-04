package jsf.sandbox.service;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import java.io.File;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import jsf.sandbox.model.TituloCobranca;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import java.util.Calendar;
import jsf.sandbox.model.Cedente;
import jsf.sandbox.model.ContaBancaria;
import jsf.sandbox.model.Sacado;

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
        Cedente titular = tituloCobranca.getConta().getTitular();
        ContaBancaria conta = tituloCobranca.getConta();
        Sacado sacado = tituloCobranca.getSacado();
        Calendar tCalendar = Calendar.getInstance();
        tCalendar.setTime(tituloCobranca.getDtVencimento());
        
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
        
        String nossoNumero = conta.getNumeroConvenio() + String.format("%010d", tituloCobranca.getId());
        
        System.out.println("-----------Nosso numero " + nossoNumero);
        Datas datas = Datas.novasDatas()
                .comVencimento(tCalendar);

        Endereco enderecoBeneficiario = Endereco.novoEndereco()
                .comLogradouro(titular.getLogradouro())
                .comBairro(titular.getBairro())
                .comCep(titular.getCep())
                .comCidade(titular.getCidade())
                .comUf("TO");

        //Quem emite o boleto
        Beneficiario beneficiario = Beneficiario.novoBeneficiario()
                .comNomeBeneficiario(titular.getNome())
                .comAgencia(conta.getAgencia()).comDigitoAgencia(conta.getAgenciaDigito())
                //CNPJ 00000000000000
                .comDocumento(titular.getCnpj())
                .comCodigoBeneficiario(conta.getConta())
                .comDigitoCodigoBeneficiario(conta.getContaDigito())
                .comNumeroConvenio(conta.getNumeroConvenio())
                .comCarteira(conta.getCarteira())
                .comEndereco(enderecoBeneficiario)
                //Nosso Número de 17 posições
                .comNossoNumero(nossoNumero);

        Endereco enderecoPagador = Endereco.novoEndereco()
                .comLogradouro(sacado.getLogradouro())
                .comBairro(sacado.getBairro())
                .comCep(sacado.getCep())
                .comCidade(sacado.getCidade())
                .comUf("TO");

        //Quem paga o boleto
        Pagador pagador = Pagador.novoPagador()
                .comNome(sacado.getNome())
                .comDocumento(sacado.getCpf())
                .comEndereco(enderecoPagador);

        Banco banco = new BancoDoBrasil();

        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                //Nota Promissória
                .comEspecieDocumento("NP")
                .comBeneficiario(beneficiario)
                .comPagador(pagador)
                .comValorBoleto(tituloCobranca.getValor())
                .comNumeroDoDocumento(tituloCobranca.getId().toString())
                .comInstrucoes(conta.getInstrucao1(), conta.getInstrucao2(), conta.getInstrucao3(), conta.getInstrucao4(), conta.getInstrucao5())
                .comLocaisDePagamento(conta.getLocalPagamento());

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
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
