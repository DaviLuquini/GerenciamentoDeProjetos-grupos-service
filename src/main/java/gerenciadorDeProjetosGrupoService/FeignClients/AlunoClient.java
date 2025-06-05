package gerenciadorDeProjetosGrupoService.FeignClients;

import gerenciadorDeProjetosGrupoService.Aplicação.DTOs.AlunoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "aluno-service", path = "/api/alunos")
public interface AlunoClient {

    @PostMapping("/buscar-por-ids")
    List<AlunoDTO> getAlunosByIds(@RequestBody List<Long> ids);
}
