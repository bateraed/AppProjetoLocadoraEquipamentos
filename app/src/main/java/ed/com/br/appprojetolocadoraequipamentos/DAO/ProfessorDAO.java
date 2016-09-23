package ed.com.br.appprojetolocadoraequipamentos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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

    //SALVAR PROFESSORES
    public void  salvar(Professor professor){

        ContentValues contentValues = new ContentValues();

        contentValues.put("nome_prof", professor.getNome());
        contentValues.put("email_prof", professor.getEmail());
        contentValues.put("disciplina_prof", professor.getDisciplina());
        contentValues.put("curso_prof",  professor.getCurso());

        databaseUtil.getConexaoDataBase().insert("tb_professor",null, contentValues);
    }

    //SELECIONAR TODOS OS PROFESSORES
    public List<Professor> selecionarTodos(){
        List<Professor> professors = new ArrayList<Professor>();

        StringBuilder stringBuilderQuery = new StringBuilder();

        stringBuilderQuery.append("SELECT id, ");
        stringBuilderQuery.append("       nome_prof, ");
/*        stringBuilderQuery.append("       email_prof, ");
        stringBuilderQuery.append("       disciplina_prof, ");
        stringBuilderQuery.append("       curso_prof, ");*/
        stringBuilderQuery.append("FROM tb_professor ");
        stringBuilderQuery.append("ORDER BY nome_prof");

        Cursor cursor = databaseUtil.getConexaoDataBase().rawQuery(stringBuilderQuery.toString(),null);
        cursor.moveToFirst();

        Professor professor;

        while (!cursor.isAfterLast()){
            professor = new Professor();

            professor.setId_professor(cursor.getInt(cursor.getColumnIndex("id")));
            professor.setNome(cursor.getString(cursor.getColumnIndex("nome_prof")));
            professor.setEmail(cursor.getString(cursor.getColumnIndex("email_prof")));
            professor.setDisciplina(cursor.getString(cursor.getColumnIndex("disciplina_prof")));
            professor.setCurso(cursor.getString(cursor.getColumnIndex("curso_prof")));

            professors.add(professor);

            cursor.moveToNext();
        }

        return professors;
    }
}
