package gerenciadorDeProjetosGrupoService.Aplicação.DTOs;

import java.util.List;

public class GrupoRequest {
	private Long id;
	private String nome;
    private Long professorId;
    private Long projetoId;
    private List<Long> alunosIds;
	private boolean disponivel = true;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	public Long getProjetoId() {
		return projetoId;
	}
	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}
	public List<Long> getAlunosIds() {
		return alunosIds;
	}
	public void setAlunosIds(List<Long> alunosIds) {
		this.alunosIds = alunosIds;
	}
	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}
