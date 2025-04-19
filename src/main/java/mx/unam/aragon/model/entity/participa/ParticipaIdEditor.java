package mx.unam.aragon.model.entity.participa;

import java.beans.PropertyEditorSupport;

public class ParticipaIdEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && !text.isEmpty()) {
            String[] parts = text.split("-");
            if (parts.length == 2) {
                Long idPelicula = Long.valueOf(parts[0]);
                Long idActor = Long.valueOf(parts[1]);
                setValue(new ParticipaId(idPelicula, idActor));
            }
        }
    }

    @Override
    public String getAsText() {
        ParticipaId id = (ParticipaId) getValue();
        return id != null ? id.getIdPelicula() + "-" + id.getIdActor() : "";
    }
}
