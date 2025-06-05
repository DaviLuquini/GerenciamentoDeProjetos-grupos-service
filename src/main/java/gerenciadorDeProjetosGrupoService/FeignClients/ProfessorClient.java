package gerenciadorDeProjetosGrupoService.FeignClients;

import gerenciadorDeProjetosGrupoService.Aplicação.DTOs.ProfessorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "professor-service", path = "/api/professores")
public interface ProfessorClient {

    @GetMapping("/{id}")
    ProfessorDTO getProfessorById(@PathVariable("id") Long id);
}