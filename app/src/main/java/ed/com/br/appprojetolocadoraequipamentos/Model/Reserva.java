package ed.com.br.appprojetolocadoraequipamentos.Model;

/**
 * Created by edinilson.silva on 14/09/2016.
 */
public class Reserva {

    private Integer idLocacao;
    private String nomeProfessor;
    private String equipamento;
    private String sala;
    private String data;
    private String horarioInicial;
    private String horarioFinal;

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }
}
