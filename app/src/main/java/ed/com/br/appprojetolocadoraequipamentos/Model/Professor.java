package ed.com.br.appprojetolocadoraequipamentos.Model;

/**
 * Created by edinilson.silva on 16/09/2016.
 */
public class Professor {

    private Integer id_professor;
    private String nome;
    private String email;
    private String disciplina;
    private String curso;

    public Integer getId_professor() {
        return id_professor;
    }

    public void setId_professor(Integer id_professor) {
        this.id_professor = id_professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Professor(String nome, String email, String disciplina, String curso) {
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
        this.curso = curso;
    }

    public Professor() {
    }
}
