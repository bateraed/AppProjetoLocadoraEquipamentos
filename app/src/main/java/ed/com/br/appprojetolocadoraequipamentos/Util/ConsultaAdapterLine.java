package ed.com.br.appprojetolocadoraequipamentos.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ed.com.br.appprojetolocadoraequipamentos.ConsultaReservaActicity;
import ed.com.br.appprojetolocadoraequipamentos.DAO.ReservaDAO;
import ed.com.br.appprojetolocadoraequipamentos.Model.Reserva;
import ed.com.br.appprojetolocadoraequipamentos.R;

/**
 * Created by edinilson.silva on 13/09/2016.
 */
public class ConsultaAdapterLine extends BaseAdapter {

    //LINK DIRTO COM A VIEW
    private static LayoutInflater layoutInflater = null;

    //ACESSO AO BANCO
    ReservaDAO reservaDAO;

    List<Reserva> reservas = new ArrayList<Reserva>();

    private ConsultaReservaActicity consultaReservaActicity;

    public ConsultaAdapterLine(ConsultaReservaActicity consultaReservaActicity, List<Reserva> reservas){
        this.reservas = reservas;
        this.consultaReservaActicity = consultaReservaActicity;
        this.layoutInflater = (LayoutInflater) this.consultaReservaActicity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.reservaDAO = new ReservaDAO(consultaReservaActicity);
    }

    @Override
    public int getCount() {
        return reservas.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consultar, null);

        TextView textViewIdReserva = (TextView)viewLinhaLista.findViewById(R.id.textViewCodigo);
        TextView textViewNomeProfessor = (TextView)viewLinhaLista.findViewById(R.id.textViewNomeProfessor);
        TextView textViewSala = (TextView)viewLinhaLista.findViewById(R.id.textViewSala);
        TextView textViewEquipamento = (TextView)viewLinhaLista.findViewById(R.id.textViewEquipamento);
        TextView textViewData = (TextView)viewLinhaLista.findViewById(R.id.textViewData);
        TextView textViewHorarioInicial = (TextView)viewLinhaLista.findViewById(R.id.textViewHorarioInicial);
        TextView textViewHorarioFinal = (TextView)viewLinhaLista.findViewById(R.id.textViewHorarioFinal);

        Button buttonExcluir = (Button)viewLinhaLista.findViewById(R.id.buttonExcluir);
        Button buttonEditar = (Button)viewLinhaLista.findViewById(R.id.buttonEditar);

        //SETANDO VALORES A VIEW
        textViewIdReserva.setText(String.valueOf(reservas.get(position).getIdLocacao()));
        textViewNomeProfessor.setText(String.valueOf(reservas.get(position).getNomeProfessor()));
        textViewEquipamento.setText(String.valueOf(reservas.get(position).getEquipamento()));
        textViewData.setText(reservas.get(position).getData());
        textViewHorarioFinal.setText(reservas.get(position).getHorarioFinal());
        textViewHorarioInicial.setText(reservas.get(position).getHorarioInicial());

        //CRIANDO EVENTOS EXCLUIR
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservaDAO.excluir(reservas.get(position).getIdLocacao());
                Toast.makeText(consultaReservaActicity, "Registro excluido com sucesso!", Toast.LENGTH_SHORT).show();
                atualizarLista();
            }
        });

        //CRIANDO EVENTOS EDITAR
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return viewLinhaLista;
    }

    private void atualizarLista(){
        this.reservas.clear();
        this.reservas = reservaDAO.selecionarTodos();
        this.notifyDataSetChanged();
    }


}
