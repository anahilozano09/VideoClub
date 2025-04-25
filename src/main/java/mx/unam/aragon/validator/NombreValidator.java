package mx.unam.aragon.validator;

import mx.unam.aragon.model.entity.DirectorEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NombreValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DirectorEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DirectorEntity director = (DirectorEntity) target;
        if(director.getNombre().equals("FES")){
            errors.rejectValue("nombre","No valido");
        }
    }
}
