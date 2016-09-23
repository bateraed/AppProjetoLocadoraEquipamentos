package ed.com.br.appprojetolocadoraequipamentos.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ed.com.br.appprojetolocadoraequipamentos.DAO.ProfessorDAO;
import ed.com.br.appprojetolocadoraequipamentos.MainActivity;
import ed.com.br.appprojetolocadoraequipamentos.Model.Professor;
import ed.com.br.appprojetolocadoraequipamentos.R;

/**
 * Created by FATE - Aluno on 13/09/2016.
 */
public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3000);

        carregarProfessores();

    }


    private void carregarProfessores(){

        Professor p1 = new Professor("Carlos", "carlos@fate.br", "OO", "Análise de Sistemas");
        Professor p2 = new Professor("Sergio", "sergio@fate.br", "Java", "Análise de Sistemas");
        Professor p3 = new Professor("pryscilla", "pryscilla@fate.br", "Android", "Análise de Sistemas");
        Professor p4 = new Professor("Messias", "messias@fate.br", "Inglês", "Análise de Sistemas");

        new ProfessorDAO(this).salvar(p1);
        new ProfessorDAO(this).salvar(p2);
        new ProfessorDAO(this).salvar(p3);
        new ProfessorDAO(this).salvar(p4);
    }
}
