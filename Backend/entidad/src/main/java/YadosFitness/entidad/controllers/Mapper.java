package YadosFitness.entidad.controllers;

import YadosFitness.dtos.*;
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
        return Dieta.builder()
            .nombre(dietaNuevaDTO.getNombre())
            .descripcion(dietaNuevaDTO.getDescripcion())
            .observaciones(dietaNuevaDTO.getObservaciones())
            .objetivo(dietaNuevaDTO.getObjetivo())
            .duracionDias(dietaNuevaDTO.getDuracionDias())
            .alimentos(dietaNuevaDTO.getAlimentos())
            .recomendaciones(dietaNuevaDTO.getRecomendaciones())
            .build();
    }
    public static Dieta toDietaId(DietaDTO dieta) {
        return Dieta.builder()
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
}
