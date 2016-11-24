package jsf.sandbox;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.jrimum.domkee.comum.pessoa.id.cprf.AbstractCPRF;
import org.jrimum.domkee.comum.pessoa.id.cprf.CPF;

@FacesConverter(value = "cpfConverter")
public class CPFConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return AbstractCPRF.create(submittedValue);
        } catch (IllegalArgumentException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " não é um CPF válido"), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof CPF) {
            return String.valueOf(((CPF) modelValue).toString());
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " não é um CPF válido"));
        }
    }
}
