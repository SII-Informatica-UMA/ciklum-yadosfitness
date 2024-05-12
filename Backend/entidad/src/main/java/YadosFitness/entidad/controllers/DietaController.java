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
    public ResponseEntity<DietaDTO> putDieta(@RequestParam Long idCliente, @RequestBody DietaDTO dietaDTO){
        Dieta dieta = Mapper.toDietaId(dietaDTO);
        logicaDieta.asignarDieta(dieta.getId(),idCliente);
        return ResponseEntity.of(logicaDieta.getDietaById(dieta.getId()).map(Mapper::toDietaDTO));
    }
    @PostMapping
    public ResponseEntity<DietaDTO> postDieta(@RequestParam Long idEntrenador,@RequestBody DietaNuevaDTO dietaDTO, UriComponentsBuilder b) {
        Dieta dieta = Mapper.toDieta(dietaDTO);
        dieta.setId(null);
        dieta.setEntrenador(idEntrenador);
        dieta = logicaDieta.addDieta(dieta);
        DietaDTO d = Mapper.toDietaDTO(dieta);
        return ResponseEntity.created(b
                .path("/dieta/{id}")
                .buildAndExpand(String.format("/%d", d.getId()))
                .toUri())
            .body(d);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DietaDTO> getDieta(@RequestParam Long id) {
        return ResponseEntity.of(logicaDieta.getDietaById(id).map(Mapper::toDietaDTO));
    }

    @PutMapping("/{id}")
    public DietaDTO putDietaId(@RequestParam Long id, @RequestParam DietaDTO dietaDTO) {
        dietaDTO.setId(id);
        Dieta d= logicaDieta.updateDieta(Mapper.toDietaId(dietaDTO));
        return Mapper.toDietaDTO(d);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDieta(@PathVariable Long id){
        logicaDieta.deleteById(id);
    }


    @ExceptionHandler(DietaNoExisteException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void noEncontrado() {}

	@ExceptionHandler(DietaExistException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public void existente() {}
}
