package jsf.sandbox.view;

import jsf.sandbox.service.Manager;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import jsf.sandbox.model.TituloCobranca;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

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


        /*
         * Banco do Brasil 001
         */
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_DO_BRASIL.create());

        /*
         * Conta/Convênio de 7 posições ou seja, acima de 1.000.000
         * Não sei por que raios se usa o n. do convênio ao invez da conta corrente
         */
        contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(tituloCobranca.getConta().getNumeroConvenio())));
        String contaDigitoDoCedenteParaExibicao = tituloCobranca.getConta().getConta() + "-" + tituloCobranca.getConta().getAgenciaDigito();
        /*
         * Carteira com no máximo 2 dígitos
         * contaBancaria.setModalidade(new Modalidade(01,"SIMPLES COM REGISTRO"));
         */
        contaBancaria.setCarteira(new Carteira(Integer.valueOf(tituloCobranca.getConta().getCarteira()), TipoDeCobranca.COM_REGISTRO));
        contaBancaria.setAgencia(new Agencia(Integer.valueOf(tituloCobranca.getConta().getAgencia()), tituloCobranca.getConta().getAgenciaDigito()));

        Sacado sacado = new Sacado(tituloCobranca.getSacado().getNome(), tituloCobranca.getSacado().getCpf());
        Endereco endereco = new Endereco();
        endereco.setUF(UnidadeFederativa.TO);
        endereco.setLocalidade(tituloCobranca.getSacado().getLogradouro());
        endereco.setCep(tituloCobranca.getSacado().getCep());
        endereco.setBairro(tituloCobranca.getSacado().getBairro());
        endereco.setLogradouro(tituloCobranca.getSacado().getLogradouro());
        endereco.setNumero("");

        sacado.addEndereco(endereco);

        Cedente cedente = new Cedente(tituloCobranca.getConta().getTitular().getNome(), tituloCobranca.getConta().getTitular().getCnpj());

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);

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
        titulo.setNossoNumero(tituloCobranca.getConta().getNumeroConvenio() + String.format("%010d", tituloCobranca.getId()));

        titulo.setNumeroDoDocumento(tituloCobranca.getId().toString());
        titulo.setValor(tituloCobranca.getValor());
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(tituloCobranca.getDtVencimento());
        //tituloCobranca.getTipo()
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(tituloCobranca.isAceite() == true ? Titulo.Aceite.A : Titulo.Aceite.N);

        Boleto boleto = new Boleto(titulo);
        /*
         * A área do boleto destinada a "Agência/Código do Cedente" deverá exibir a informação
         * "Agência (com 4 dígitos, zeros a esquerda)/Conta bancária". Ex: 0057/21109-2.
         */
        String agenciaCodigoDoCedenteParaExibicao = String.format("%s-%s / %s",
                titulo.getContaBancaria().getAgencia().getCodigo(),
                titulo.getContaBancaria().getAgencia().getDigitoVerificador(),
                contaDigitoDoCedenteParaExibicao);
        boleto.addTextosExtras("txtFcAgenciaCodigoCedente", agenciaCodigoDoCedenteParaExibicao);
        boleto.addTextosExtras("txtRsAgenciaCodigoCedente", agenciaCodigoDoCedenteParaExibicao);
        boleto.addTextosExtras("txtRsEspecie", "R$");
        boleto.addTextosExtras("txtFcEspecie", "R$");
        boleto.setLocalPagamento(tituloCobranca.getConta().getLocalPagamento());
        boleto.setInstrucao1(tituloCobranca.getConta().getInstrucao1());
        boleto.setInstrucao2(tituloCobranca.getConta().getInstrucao2());
        boleto.setInstrucao3(tituloCobranca.getConta().getInstrucao3());
        boleto.setInstrucao4(tituloCobranca.getConta().getInstrucao4());
        boleto.setInstrucao5(tituloCobranca.getConta().getInstrucao5());
        boleto.setInstrucao6(tituloCobranca.getConta().getInstrucao6());
        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        boletoViewer.setImprimeReciboEntrega(true);
        return boletoViewer.getPdfAsByteArray();
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
