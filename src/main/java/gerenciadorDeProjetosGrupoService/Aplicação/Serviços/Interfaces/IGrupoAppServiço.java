package gerenciadorDeProjetosGrupoService.Aplicação.Serviços.Interfaces;

import java.util.List;

import gerenciadorDeProjetosGrupoService.Aplicação.DTOs.GrupoRequest;
import gerenciadorDeProjetosGrupoService.Dominio.Grupo;


public interface IGrupoAppServiço {
    
    List<GrupoRequest> listarGrupos();
    
    GrupoRequest listarGrupoPorAlunoId(Long AlunoId);
    
    Grupo cadastrarGrupo(GrupoRequest grupoRequest);
    
    boolean grupoNomeEmUso(String nome);
    
    Grupo atualizarGrupo(GrupoRequest grupoAtualizado);
    
    void desativarGrupo(String nome);
}
