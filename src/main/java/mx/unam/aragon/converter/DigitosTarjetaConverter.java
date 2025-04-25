package mx.unam.aragon.converter;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;

public class DigitosTarjetaConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try{
            if (text == null || text.trim().isEmpty()) {
                setValue(null);
                return;
            }

            if (!text.matches("[0-9\\s-]+")) {
                throw new IllegalArgumentException("Solo se permiten números, espacios y guiones");
            }

            String digitos = text.replaceAll("[^0-9]", "");

            if (digitos.length() != 15 && digitos.length() != 16) {
                throw new IllegalArgumentException("La tarjeta debe tener 15 o 16 dígitos");
            }

            String digitosFormato;
            if (digitos.length() == 16) {
                digitosFormato = String.format("%s-%s-%s-%s",
                        digitos.substring(0, 4),
                        digitos.substring(4, 8),
                        digitos.substring(8, 12),
                        digitos.substring(12, 16));
            } else {
                digitosFormato = String.format("%s-%s-%s",
                        digitos.substring(0, 4),
                        digitos.substring(4, 10),
                        digitos.substring(10, 15));
            }

            setValue(digitosFormato);

        }catch (Exception ex){
            text="0";
        }
    }
    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }

}
