package gerenciadorDeProjetosGrupoService.Apresentação;

import gerenciadorDeProjetosGrupoService.Aplicação.DTOs.GrupoRequest;
import gerenciadorDeProjetosGrupoService.Aplicação.Serviços.Interfaces.IGrupoAppServiço;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private IGrupoAppServiço grupoServiço;

    
    @GetMapping("/hello")
    public String hello() {
    	return "hello world";
    }
    
    @GetMapping("/listarGrupos")
    public ResponseEntity<List<GrupoRequest>> listarGrupos() {
        List<GrupoRequest> grupos = grupoServiço.listarGrupos();
        return ResponseEntity.ok(grupos);
    }
    
    @GetMapping("/listarGrupoPorAlunoId")
    public ResponseEntity<?> listarGrupoPorAlunoIds(Long alunoId) {
        GrupoRequest grupo = grupoServiço.listarGrupoPorAlunoId(alunoId);

        if (grupo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo não encontrado para o aluno.");
        }

        return ResponseEntity.ok(grupo);
    }
}

