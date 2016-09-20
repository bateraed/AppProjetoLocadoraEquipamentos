package ed.com.br.appprojetolocadoraequipamentos.DAO;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ed.com.br.appprojetolocadoraequipamentos.Model.Equipamento;

/**
 * Created by edinilson.silva on 16/09/2016.
 */
public class EquipamentoDAO {

    public List<Equipamento> selecionarTodos(){
        ArrayAdapter<Equipamento> arrayAdapter;

        List<Equipamento> itens = new ArrayList<Equipamento>();

        itens.add(new Equipamento(1,"Datashow", 10));
        itens.add(new Equipamento(2,"Caixa de som", 5));
        itens.add(new Equipamento(3,"Microfone", 5));
        itens.add(new Equipamento(4,"Lousa", 1));

        return itens;
    }

}

