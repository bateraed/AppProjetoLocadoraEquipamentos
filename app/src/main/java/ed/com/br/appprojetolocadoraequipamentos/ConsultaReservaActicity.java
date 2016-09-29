package ed.com.br.appprojetolocadoraequipamentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import ed.com.br.appprojetolocadoraequipamentos.DAO.ReservaDAO;
import ed.com.br.appprojetolocadoraequipamentos.Model.Reserva;
import ed.com.br.appprojetolocadoraequipamentos.Util.ConsultaAdapterLine;

/**
 * Created by edinilson.silva on 14/09/2016.
 */
public class ConsultaReservaActicity extends AppCompatActivity {

    ListView listViewReservas;
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        listViewReservas = (ListView)this.findViewById(R.id.listViewReservas);
        buttonVoltar = (Button)this.findViewById(R.id.buttonVoltar);

        carregarReservasCadastradas();
        botaoVoltar();
    }

    private void carregarReservasCadastradas(){

        ReservaDAO reservaDAO = new ReservaDAO(this);

        List<Reserva> reservas = reservaDAO.selecionarTodos();
        listViewReservas.setAdapter(new ConsultaAdapterLine(this, reservas));
    }

    private void botaoVoltar(){
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMain);
                finish();
            }
        });
    }
}
