package gerenciadorDeProjetosGrupoService.Dominio;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupo")
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private boolean disponivel = true;

	private Long professorId;

    @ElementCollection
    @CollectionTable(name = "grupo_alunos", joinColumns = @JoinColumn(name = "grupo_id"))
    @Column(name = "aluno_id")
    private List<Long> alunosIds;

    private Long projetoId;

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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public List<Long> getAlunosIds() {
		return alunosIds;
	}

	public void setAlunosIds(List<Long> alunosIds) {
		this.alunosIds = alunosIds;
	}

	public Long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}

}