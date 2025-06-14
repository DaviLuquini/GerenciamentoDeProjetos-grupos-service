package gerenciadorDeProjetosGrupoService;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "gerenciadorDeProjetosGrupoService.FeignClients")
public class GerenciadorDeProjetosGrupoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeProjetosGrupoServiceApplication.class, args);
	}

}
