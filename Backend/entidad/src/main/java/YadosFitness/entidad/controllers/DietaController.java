package YadosFitness.entidad.controllers;

import YadosFitness.entidad.dtos.*;
import YadosFitness.entidad.entities.Dieta;
import YadosFitness.entidad.exceptions.*;
import YadosFitness.entidad.services.*;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;





@RestController
@RequestMapping("/dieta")
public class DietaController {
    private LogicaDieta logicaDieta;

    public DietaController(LogicaDieta logicaDieta){
        this.logicaDieta =logicaDieta;
    }

    @GetMapping
    public ResponseEntity<List<DietaDTO>> getDietas(@RequestParam(value = "idEntrenador", required = false) Long idEntrenador,@RequestParam(value = "idCliente", required = false) Long idCliente){
        try {
            List<DietaDTO> dietas;
            if ((idEntrenador==null && idCliente==null) || (idEntrenador!=null && idCliente!=null)) {
                return ResponseEntity.badRequest().build();
            }else if (idCliente!=null){
                dietas = logicaDieta.dietasDeCliente(idCliente).stream().map(Mapper :: toDietaDTO).toList();
                return ResponseEntity.of(Optional.of(dietas));
            }else{
                dietas = logicaDieta.dietasDeEntrenador(idEntrenador).stream().map(Mapper :: toDietaDTO).toList();
                return ResponseEntity.of(Optional.of(dietas));
            }
        } catch (AcessoNoAutorizadoException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        
    }

    @PutMapping
    public ResponseEntity<DietaDTO> putDieta(@RequestParam(value = "idCliente", required = true) Long idCliente, @RequestBody DietaDTO dietaDTO){
        try {
            logicaDieta.asignarDieta(dietaDTO.getId(),idCliente);
            return ResponseEntity.ok().build();
        } catch (DietaNoExisteException e) {
            return ResponseEntity.notFound().build();
        } catch (AcessoNoAutorizadoException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
    }

    @PostMapping
    public ResponseEntity<DietaDTO> postDieta(@RequestParam(value = "idEntrenador", required = true) Long idEntrenador,@RequestBody DietaNuevaDTO dietaDTO, UriComponentsBuilder b) {
        try {
            Dieta d = Mapper.toDieta(dietaDTO);
            d.setId(null);
            d.setEntrenador(idEntrenador);
            d = logicaDieta.addDieta(d);
            var dDTO = Mapper.toDietaDTO(d);
            return ResponseEntity.created(b
                    .path("/dieta/{id}")
                    .buildAndExpand(String.format("/%d", d.getId()))
                    .toUri())
                .body(dDTO);
        } catch (DietaExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (AcessoNoAutorizadoException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietaDTO> getDieta(@PathVariable Long id) {
        try {
            return ResponseEntity.of(logicaDieta.getDietaById(id).map(Mapper::toDietaDTO));
        } catch (DietaNoExisteException e) {
            return ResponseEntity.notFound().build();
        } catch (AcessoNoAutorizadoException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietaDTO> putDietaId(@PathVariable Long id, @RequestBody DietaDTO dietaDTO) {
        try {
            if (id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            dietaDTO.setId(id);
            logicaDieta.updateDieta(Mapper.toDietaId(dietaDTO));
            return ResponseEntity.ok().build();
        } catch (DietaNoExisteException e) {
            return ResponseEntity.notFound().build();
        } catch (AcessoNoAutorizadoException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDieta(@PathVariable Long id){
        try {
            if (id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            logicaDieta.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DietaNoExisteException e) {
            return ResponseEntity.notFound().build();
        } catch (AcessoNoAutorizadoException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
