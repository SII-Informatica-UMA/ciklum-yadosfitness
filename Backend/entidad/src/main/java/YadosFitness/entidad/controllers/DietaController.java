package YadosFitness.entidad.controllers;

import YadosFitness.entidad.dtos.*;
import YadosFitness.entidad.entities.Dieta;
import YadosFitness.entidad.exceptions.*;
import YadosFitness.entidad.services.*;


import java.util.List;



import org.springframework.http.HttpStatus;
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
    public List<DietaDTO> getDietas(@RequestParam(value = "idEntrenador", required = false) Long idEntrenador,@RequestParam(value = "idCliente", required = false) Long idCliente){
        if ((idEntrenador==null && idCliente==null) || (idEntrenador!=null && idCliente!=null)) {
            throw new IllegalArgumentException("Especificar solo un parametro");
        }else if (idCliente!=null){
            return  logicaDieta.dietasDeCliente(idCliente).stream().map(Mapper :: toDietaDTO).toList();
        }else{
            return logicaDieta.dietasDeEntrenador(idEntrenador).stream().map(Mapper :: toDietaDTO).toList();
        }
    }

    @PutMapping
    public ResponseEntity<DietaDTO> putDieta(@RequestParam(value = "idCliente", required = true) Long idCliente, @RequestBody DietaDTO dietaDTO){
        try {
            logicaDieta.asignarDieta(dietaDTO.getId(),idCliente);
            return ResponseEntity.ok().build();
        } catch (DietaNoExisteException e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @PostMapping
    public ResponseEntity<DietaDTO> postDieta(@RequestParam(value = "idEntrenador", required = true) Long idEntrenador,@RequestBody DietaNuevaDTO dietaDTO, UriComponentsBuilder b) {
        try {
            Dieta dieta = Mapper.toDieta(dietaDTO);
            dieta.setId(1L);
            dieta.setEntrenador(idEntrenador);
            dieta = logicaDieta.addDieta(dieta);
            DietaDTO d = Mapper.toDietaDTO(dieta);
            return ResponseEntity.created(b
                    .path("/dieta/{id}")
                    .buildAndExpand(String.format("/%d", d.getId()))
                    .toUri())
                .body(d);
        } catch (DietaExistException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietaDTO> getDieta(@PathVariable Long id) {
        try {
            return ResponseEntity.of(logicaDieta.getDietaById(id).map(Mapper::toDietaDTO));
        } catch (DietaNoExisteException e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietaDTO> putDietaId(@PathVariable Long id, @RequestBody DietaDTO dietaDTO) {
        try {
            dietaDTO.setId(id);
            logicaDieta.updateDieta(Mapper.toDietaId(dietaDTO));
            return ResponseEntity.ok().build();
        } catch (DietaNoExisteException e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDieta(@PathVariable Long id){
        try {
            logicaDieta.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (DietaNoExisteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /* 
    @ExceptionHandler(DietaNoExisteException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void noEncontrado() {}

	@ExceptionHandler(DietaExistException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public void existente() {}
    */
}
