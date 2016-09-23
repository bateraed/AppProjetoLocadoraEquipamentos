package ed.com.br.appprojetolocadoraequipamentos.DAO;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ed.com.br.appprojetolocadoraequipamentos.Model.Professor;
import ed.com.br.appprojetolocadoraequipamentos.Model.Reserva;
import ed.com.br.appprojetolocadoraequipamentos.Util.DatabaseUtil;

/**
 * Created by edinilson.silva on 19/09/2016.
 */
public class ProfessorDAO {

    DatabaseUtil databaseUtil;

    public ProfessorDAO(Context context){
        databaseUtil = new DatabaseUtil(context);
    }

    public void  salvar(Professor professor){

        ContentValues contentValues = new ContentValues();

        contentValues.put("nome_prof", professor.getNome());
        contentValues.put("email_prof", professor.getEmail());
        contentValues.put("disciplina_prof", professor.getDisciplina());
        contentValues.put("curso_prof",  professor.getCurso());

        databaseUtil.getConexaoDataBase().insert("tb_professor",null, contentValues);
    }
}
