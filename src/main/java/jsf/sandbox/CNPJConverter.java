package jsf.sandbox;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.jrimum.domkee.comum.pessoa.id.cprf.AbstractCPRF;
import org.jrimum.vallia.AbstractCPRFValidator;
import static org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF.CNPJ;

@FacesConverter(value = "cnpjConverter")
public class CNPJConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        new CNPJ();
        AbstractCPRFValidator.isParametrosValidos(value, CNPJ)
        return AbstractCPRF.create(value);

			FacesMessage msg =
				new FacesMessage("URL Conversion error.",
						"Invalid URL format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return (String) value;
    }
}
