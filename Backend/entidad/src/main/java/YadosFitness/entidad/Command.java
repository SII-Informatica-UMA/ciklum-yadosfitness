package YadosFitness.entidad;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import YadosFitness.entidad.entities.Cliente;
import YadosFitness.entidad.entities.Dieta;
import YadosFitness.entidad.entities.Entrenador;
import YadosFitness.entidad.repositories.ClienteRepository;
import YadosFitness.entidad.repositories.DietaRepository;
import YadosFitness.entidad.repositories.EntrenadorRepository;
import jakarta.transaction.Transactional;
@Component
public class Command implements CommandLineRunner {
    private ClienteRepository clienteRepository;
    private EntrenadorRepository entrenadorRepository;
    private DietaRepository dietaRepository;

    public Command(ClienteRepository clienteRepository, EntrenadorRepository entrenadorRepository, DietaRepository dietaRepository) {
        this.clienteRepository = clienteRepository;
        this.entrenadorRepository = entrenadorRepository;
        this.dietaRepository = dietaRepository;
    }
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        for (String s: args) {
            System.out.println(s);
        }
        if(args.length > 0){
            for(Dieta d : dietaRepository.findByIdUsuarioid(args[0])){
                System.out.println(d);
            }
            for(Entrenador e : entrenadorRepository.findByIdEntrenadorid(args[0])){
                System.out.println(e);
            }
            for(Cliente c : clienteRepository.findByIdclienteID(args[0])){
                System.out.println(c);
            }
        }
        
    }
}
