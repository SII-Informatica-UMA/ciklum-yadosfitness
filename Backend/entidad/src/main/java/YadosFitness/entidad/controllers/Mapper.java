package YadosFitness.entidad.controllers;

import YadosFitness.entidad.dtos.*;
import YadosFitness.entidad.entities.Dieta;

public class Mapper {
    public static DietaDTO toDietaDTO(Dieta dieta) {
        return DietaDTO.builder()
            .id(dieta.getId())
            .nombre(dieta.getNombre())
            .descripcion(dieta.getDescripcion())
            .observaciones(dieta.getObservaciones())
            .objetivo(dieta.getObjetivo())
            .duracionDias(dieta.getDuracionDias())
            .alimentos(dieta.getAlimentos())
            .recomendaciones(dieta.getRecomendaciones())
            .build();
    }

    public static Dieta toDieta(DietaNuevaDTO dietaNuevaDTO) {
        Dieta dieta = new Dieta();
        dieta.setNombre(dietaNuevaDTO.getNombre());
        dieta.setDescripcion(dietaNuevaDTO.getDescripcion());
        dieta.setObservaciones(dietaNuevaDTO.getObservaciones());
        dieta.setObjetivo(dietaNuevaDTO.getObjetivo());
        dieta.setDuracionDias(dietaNuevaDTO.getDuracionDias());
        dieta.setAlimentos(dietaNuevaDTO.getAlimentos());
        dieta.setRecomendaciones(dietaNuevaDTO.getRecomendaciones());
        return dieta;
    }
    public static Dieta toDietaId(DietaDTO dieta) {
        Dieta nuevaDieta = new Dieta();
        nuevaDieta.setId(dieta.getId());
        nuevaDieta.setNombre(dieta.getNombre());
        nuevaDieta.setDescripcion(dieta.getDescripcion());
        nuevaDieta.setObservaciones(dieta.getObservaciones());
        nuevaDieta.setObjetivo(dieta.getObjetivo());
        nuevaDieta.setDuracionDias(dieta.getDuracionDias());
        nuevaDieta.setAlimentos(dieta.getAlimentos());
        nuevaDieta.setRecomendaciones(dieta.getRecomendaciones());
        return nuevaDieta;
    }
}
