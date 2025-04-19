package mx.unam.aragon.model.entity.listaEspera;

import java.beans.PropertyEditorSupport;

public class ListaEsperaIdEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && !text.isEmpty()) {
            String[] parts = text.split("-");
            if (parts.length == 2) {
                Long idSocio = Long.valueOf(parts[0]);
                Long idPelicula = Long.valueOf(parts[1]);
                setValue(new ListaEsperaId(idSocio, idPelicula));
            }
        }
    }

    @Override
    public String getAsText() {
        ListaEsperaId id = (ListaEsperaId) getValue();
        return id != null ? id.getIdSocio() + "-" + id.getIdPelicula() : "";
    }
}
