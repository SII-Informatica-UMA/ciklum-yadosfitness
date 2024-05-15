package YadosFitness.entidad;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import YadosFitness.entidad.entities.Dieta;


import YadosFitness.entidad.repositories.DietaRepository;

import jakarta.transaction.Transactional;
@Component
public class Command implements CommandLineRunner {
    
    private DietaRepository dietaRepository;

    public Command(DietaRepository dietaRepository) {
        this.dietaRepository = dietaRepository;
    }
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        for (String s: args) {
            System.out.println(s);
        }
        if(args.length > 0){
            for(Dieta d : dietaRepository.findAllByNombre(args[0])){
                System.out.println(d);
            }
        }
        
    }
}
