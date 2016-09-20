package ed.com.br.appprojetolocadoraequipamentos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listViewOpcoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criarComponentes();
        carregarOpcoesLista();
        criarEventos();

    }

    private void criarComponentes() {
        listViewOpcoes = (ListView)findViewById(R.id.listViewOpcoes);
    }

    private void carregarOpcoesLista(){
        String[] itens = new String[3];

        itens[0] = "Reserve um Equipamento";
        itens[1] = "Todas as Reservas";
        itens[2] = "Listar Professores";

        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);

        listViewOpcoes.setAdapter(arrayItens);
    }

    protected  void criarEventos(){
        listViewOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String opcaoMenu = ((TextView)view).getText().toString();
                redirecionaTela(opcaoMenu);
            }
        });
    }

    protected void redirecionaTela(String opcaoMenu){

        Intent intentRedirecionar;

        if(opcaoMenu.equals("Reserve um Equipamento")){
            intentRedirecionar = new Intent(this, LocacaoActivity.class);
            startActivity(intentRedirecionar);
            finish();
        } else if (opcaoMenu.equals("Todas as Reservas")) {
            intentRedirecionar = new Intent(this, ConsultaReservaActicity.class);
            startActivity(intentRedirecionar);
            finish();
        } else
            Toast.makeText(getApplicationContext(), "Opção Inválida!",Toast.LENGTH_SHORT).show();

    }
}
