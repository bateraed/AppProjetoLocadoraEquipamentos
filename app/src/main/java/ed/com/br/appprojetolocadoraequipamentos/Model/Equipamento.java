package ed.com.br.appprojetolocadoraequipamentos.Model;

/**
 * Created by edinilson.silva on 16/09/2016.
 */
public class Equipamento {

    private Integer id_equipamento;
    private String nome;
    private int quantidade;

    public Integer getId_equipamento() {
        return id_equipamento;
    }

    public void setId_equipamento(Integer id_equipamento) {
        this.id_equipamento = id_equipamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Equipamento(Integer id_equipamento, String nome, int quantidade) {
        this.id_equipamento = id_equipamento;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Equipamento() { }
}
