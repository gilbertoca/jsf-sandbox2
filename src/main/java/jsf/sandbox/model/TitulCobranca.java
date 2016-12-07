package jsf.sandbox.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author gilberto.andrade
 */
public class TitulCobranca {
    private ContaBancaria conta;
    private Sacado sacado;
    private String tipo = "DM";
    private boolean aceite = false;
    private String documento;
    private String nossoNumero;
    private LocalDate dtEmissao;
    private LocalDate dtVencimento;
    private BigDecimal valor;
    private BigDecimal juros;
    private BigDecimal multa;
    
}
