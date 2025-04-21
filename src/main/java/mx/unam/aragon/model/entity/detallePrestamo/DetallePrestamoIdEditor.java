package mx.unam.aragon.model.entity.detallePrestamo;

import mx.unam.aragon.model.entity.ejemplar.EjemplarId;

import java.beans.PropertyEditorSupport;

public class DetallePrestamoIdEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && !text.isEmpty()) {
            String[] parts = text.split("-");
            if (parts.length == 3) {
                Long idPrestamo = Long.valueOf(parts[0]);
                Long idPelicula = Long.valueOf(parts[1]);
                Long  consecutivo= Long.valueOf(parts[2]);
                setValue(new DetallePrestamoId(idPrestamo, idPelicula, consecutivo));
            }
        }
    }

    @Override
    public String getAsText() {
        DetallePrestamoId id = (DetallePrestamoId) getValue();
        return id != null ? id.getIdPrestamo() + "-" + id.getIdPelicula() + "-" + id.getConsecutivo() : "";
    }
}
