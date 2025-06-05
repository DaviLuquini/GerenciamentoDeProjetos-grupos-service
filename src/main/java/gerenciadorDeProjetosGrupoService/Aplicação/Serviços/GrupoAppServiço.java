package gerenciadorDeProjetosGrupoService.Aplicação.Serviços;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gerenciadorDeProjetosGrupoService.Aplicação.DTOs.AlunoDTO;
import gerenciadorDeProjetosGrupoService.Aplicação.DTOs.GrupoRequest;
import gerenciadorDeProjetosGrupoService.Aplicação.DTOs.ProfessorDTO;
import gerenciadorDeProjetosGrupoService.Aplicação.Serviços.Interfaces.IGrupoAppServiço;
import gerenciadorDeProjetosGrupoService.Dominio.Grupo;
import gerenciadorDeProjetosGrupoService.FeignClients.AlunoClient;
import gerenciadorDeProjetosGrupoService.FeignClients.ProfessorClient;
import gerenciadorDeProjetosGrupoService.Infraestrutura.Repositorios.IGrupoRepositorio;

@Service
public class GrupoAppServiço implements IGrupoAppServiço {

    @Autowired
    private IGrupoRepositorio grupoRepositorio;

    @Autowired
    private ProfessorClient professorClient;

    @Autowired
    private AlunoClient alunoClient;

    @Override
    public Grupo cadastrarGrupo(GrupoRequest grupoRequest) {
        // Valida se professor existe via client
        ProfessorDTO professor = professorClient.getProfessorById(grupoRequest.getProfessorId());
        if (professor == null) {
            throw new RuntimeException("Professor não encontrado");
        }

        // Valida alunos via client
        List<AlunoDTO> alunos = alunoClient.getAlunosByIds(grupoRequest.getAlunosIds());
        if (alunos.size() != grupoRequest.getAlunosIds().size()) {
            throw new RuntimeException("Alguns alunos não foram encontrados");
        }

        Grupo grupo = new Grupo();
        grupo.setNome(grupoRequest.getNome());
        grupo.setDisponivel(true);
        grupo.setProfessorId(grupoRequest.getProfessorId());
        grupo.setAlunosIds(grupoRequest.getAlunosIds());

        return grupoRepositorio.save(grupo);
    }
    
    @Override
    public boolean grupoNomeEmUso(String nome) {
        return grupoRepositorio.findByNome(nome).isPresent();
    }

    @Override
    public Grupo atualizarGrupo(GrupoRequest grupoAtualizado) {
        return grupoRepositorio.findByNome(grupoAtualizado.getNome()).map(grupo -> {
            grupo.setNome(grupoAtualizado.getNome());
            grupo.setDisponivel(grupoAtualizado.isDisponivel());
            grupo.setProfessorId(grupoAtualizado.getProfessorId());
            grupo.setAlunosIds(grupoAtualizado.getAlunosIds());
            return grupoRepositorio.save(grupo);
        }).orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
    }

    @Override
    public List<GrupoRequest> listarGrupos() {
        return grupoRepositorio.findByDisponivelTrue().stream().map(grupo -> {
            GrupoRequest dto = new GrupoRequest();
            dto.setId(grupo.getId());
            dto.setNome(grupo.getNome());
            dto.setDisponivel(grupo.isDisponivel());
            dto.setProjetoId(grupo.getProjetoId());
            dto.setProfessorId(grupo.getProfessorId());
            dto.setAlunosIds(grupo.getAlunosIds());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public GrupoRequest listarGrupoPorAlunoId(Long alunoId) {
        return grupoRepositorio.findByDisponivelTrue().stream()
            .filter(grupo -> grupo.getAlunosIds() != null && grupo.getAlunosIds().contains(alunoId))
            .findFirst()
            .map(grupo -> {
                GrupoRequest dto = new GrupoRequest();
                dto.setId(grupo.getId());
                dto.setNome(grupo.getNome());
                dto.setDisponivel(grupo.isDisponivel());
                dto.setProjetoId(grupo.getProjetoId());
                dto.setProfessorId(grupo.getProfessorId());
                dto.setAlunosIds(grupo.getAlunosIds());
                return dto;
            })
            .orElse(null);
    }

    @Override
    public void desativarGrupo(String nome) {
        Optional<Grupo> grupo = grupoRepositorio.findByNome(nome);
        if (grupo.isPresent()) {
            Grupo g = grupo.get();
            g.setDisponivel(false);
            grupoRepositorio.save(g);
        } else {
            throw new RuntimeException("Grupo não encontrado");
        }
    }

}
