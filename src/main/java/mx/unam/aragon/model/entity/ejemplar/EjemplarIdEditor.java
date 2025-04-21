package mx.unam.aragon.model.entity.ejemplar;

import java.beans.PropertyEditorSupport;

public class EjemplarIdEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && !text.isEmpty()) {
            String[] parts = text.split("-");
            if (parts.length == 2) {
                Long idPelicula = Long.valueOf(parts[0]);
                Long  consecutivo= Long.valueOf(parts[1]);
                setValue(new EjemplarId(idPelicula, consecutivo));
            }
        }
    }

    @Override
    public String getAsText() {
        EjemplarId id = (EjemplarId) getValue();
        return id != null ? id.getIdPelicula() + "-" + id.getConsecutivo() : "";
    }
}
