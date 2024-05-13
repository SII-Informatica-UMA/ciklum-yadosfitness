package YadosFitness.entidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import YadosFitness.entidad.dtos.DietaDTO;
import YadosFitness.entidad.dtos.DietaNuevaDTO;
import YadosFitness.entidad.entities.Dieta;
import YadosFitness.entidad.repositories.DietaRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DietasApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private DietaRepository dietaRepository;

    @BeforeEach
    public void initializeDatabase() {
        dietaRepository.deleteAll();
    }

    private URI uri(String scheme, String host, int port, String... paths) {
        UriBuilderFactory ubf = new DefaultUriBuilderFactory();
        UriBuilder ub = ubf.builder()
                .scheme(scheme)
                .host(host).port(port);
        for (String path : paths) {
            ub = ub.path(path);
        }
        return ub.build();
    }

	private URI uriWithQuery(String scheme, String host, int port, String path, Map<String, String> queryParams) {
        UriBuilderFactory ubf = new DefaultUriBuilderFactory();
        UriBuilder ub = ubf.builder()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path(path);

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            ub.queryParam(entry.getKey(), entry.getValue());
        }

        return ub.build();
    }
	
    private RequestEntity<Void> get(String scheme, String host, int port, String path) {
        URI uri = uri(scheme, host, port, path);
        var peticion = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        return peticion;
    }

	public RequestEntity<Void> getWithQuery(String scheme, String host, int port, String path, Map<String, String> queryParams) {
		URI uri = uriWithQuery(scheme, host, port, path, queryParams);
		var peticion = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_JSON)
				.build();
		return peticion;
	}
    private RequestEntity<Void> delete(String scheme, String host, int port, String path) {
        URI uri = uri(scheme, host, port, path);
        var peticion = RequestEntity.delete(uri)
                .build();
        return peticion;
    }

    private <T> RequestEntity<T> post(String scheme, String host, int port, String path, T object) {
        URI uri = uri(scheme, host, port, path);
        var peticion = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(object);
        return peticion;
    }

	private <T> RequestEntity<T> postWithQuery(String scheme, String host, int port, String path, Map<String, String> queryParams, T object) {
		URI uri = uriWithQuery(scheme, host, port, path, queryParams);
		var peticion = RequestEntity.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.body(object);
		return peticion;
	}
    private <T> RequestEntity<T> put(String scheme, String host, int port, String path, T object) {
        URI uri = uri(scheme, host, port, path);
        var peticion = RequestEntity.put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(object);
        return peticion;
    }

    @Nested
    @DisplayName("cuando no hay dietas")
    public class DietasVacias {

        @Test
        @DisplayName("devuelve lista de dietas vacía por entrenador")
        public void devuelveListaDeDietasVaciaPorEntrenador() {
            var peticion = getWithQuery("http", "localhost", port, "/dieta", Map.of("idEntrenador", "1"));

			var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<Set<DietaDTO>>() {
        	});

			assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
			assertThat(respuesta.getBody().isEmpty());

    	}
		
		@Test
		@DisplayName("devuelve lista de dietas vacía por cliente")
		public void devuelveListaDeDietasVaciaPorCliente() {
			var peticion = getWithQuery("http", "localhost", port, "/dieta", Map.of("idCliente", "2"));

			var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<Set<DietaDTO>>() {
			});

			assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
			assertThat(respuesta.getBody().isEmpty());
		}

		@Test 
		@DisplayName("error devuelve dieta no paso parametro")
		
		public void errorDevuelveDietaNoPasoParametro() {
			var peticion = get("http", "localhost", port, "/dieta");
			var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<String>() {
			});
			assertThat(respuesta.getStatusCode().value()).isEqualTo(500);
		}

		@Test
		@DisplayName("error devuelve dieta paso dos parametros")
		public void errorDevuelveDietaPasoDosParametros() {
			var peticion = getWithQuery("http", "localhost", port, "/dieta", Map.of("idEntrenador", "1", "idCliente", "2"));
			var respuesta = restTemplate.exchange(peticion, new ParameterizedTypeReference<String>() {
			});
			assertThat(respuesta.getStatusCode().value()).isEqualTo(500);
		}

		@Test
		@DisplayName("entrenador añade una dieta")
		public void entrenadorAnadeUnaDieta() {
			var dieta = DietaNuevaDTO.builder()

						.nombre("Dieta 1")
						.descripcion("Dieta para adelgazar")
						.observaciones("No comer dulces")
						.objetivo("Perder peso")
						.duracionDias(30)
						.recomendaciones("Hacer ejercicio")
						.build();

			var peticion = postWithQuery("http", "localhost", port, "/dieta", Map.of("idEntrenador", "1"), dieta);
			var respuesta = restTemplate.exchange(peticion, DietaDTO.class);

			assertThat(respuesta.getStatusCode().value()).isEqualTo(201);
			assertThat(respuesta.getBody().getNombre()).isEqualTo("Dieta 1");
		}

	}
    @Nested
    @DisplayName("cuando hay dietas con datos")
    public class DietasConDatos {
		

        @Test
        @DisplayName("devuelve una dieta por id")
        public void devuelveUnaDietaPorId() {
            var dieta1 = new Dieta();
            dieta1.setId(1L);
            dieta1.setNombre("Dieta 1");
            dieta1.setDescripcion("Dieta para adelgazar");
            dieta1.setObservaciones("No comer dulces");
            dieta1.setObjetivo("Perder peso");
            dietaRepository.save(dieta1);

            var peticion = get("http", "localhost", port, "/dieta/1");
            var respuesta = restTemplate.exchange(peticion, DietaDTO.class);
            assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
            assertThat(respuesta.getBody().getId()).isEqualTo(1L);
        }
        @Test
        @DisplayName("elimina una dieta por id")
        public void eliminaUnaDietaPorId() {
            var dieta1 = new Dieta();
            dieta1.setId(1L);
            dieta1.setNombre("Dieta 1");
            dieta1.setDescripcion("Dieta para adelgazar");
            dieta1.setObservaciones("No comer dulces");
            dieta1.setObjetivo("Perder peso");
            dietaRepository.save(dieta1);
            var peticion = delete("http", "localhost", port, "/dieta/1");
            var respuesta = restTemplate.exchange(peticion, Void.class);
            assertThat(respuesta.getStatusCode().value()).isEqualTo(201);
        }
        //ARREGLAR
        
    }
	
}
