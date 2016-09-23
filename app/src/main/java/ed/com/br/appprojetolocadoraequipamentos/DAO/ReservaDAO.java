package ed.com.br.appprojetolocadoraequipamentos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ed.com.br.appprojetolocadoraequipamentos.Model.Reserva;
import ed.com.br.appprojetolocadoraequipamentos.Util.DatabaseUtil;

/**
 * Created by edinilson.silva on 09/09/2016.
 */
public class ReservaDAO {

    DatabaseUtil databaseUtil;

    public ReservaDAO(Context context){
        databaseUtil = new DatabaseUtil(context);
    }

    //SALVAR RESERVA
    public void  salvar(Reserva reserva){

        ContentValues contentValues = new ContentValues();

        contentValues.put("nome_prof_reserva", reserva.getNomeProfessor());
        contentValues.put("nome_equip_reserva", reserva.getEquipamento());
        contentValues.put("sala_reserva", reserva.getSala());
        contentValues.put("data_reserva",  reserva.getData());
        contentValues.put("hora_inicial_reserva", reserva.getHorarioInicial());
        contentValues.put("hora_final_reserva", reserva.getHorarioFinal());

        databaseUtil.getConexaoDataBase().insert("tb_reserva",null, contentValues);
    }

    //ATUALIZAR RESERVA
    public void atualizar(Reserva reserva){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nome_prof_reserva", reserva.getNomeProfessor());
        contentValues.put("nome_equip_reserva", reserva.getEquipamento());
        contentValues.put("sala_reserva", reserva.getSala());
        contentValues.put("data_reserva",  reserva.getData());
        contentValues.put("hora_inicial_reserva", reserva.getHorarioInicial());
        contentValues.put("hora_final_reserva", reserva.getHorarioFinal());

        databaseUtil.getConexaoDataBase().update("tb_reserva", contentValues, "id = ?",
                new String[]{Integer.toString(reserva.getIdLocacao())});
    }

    //EXCLUI RESERVA
    public Integer excluir(int codigo){

        return databaseUtil.getConexaoDataBase().delete("tb_reserva", "id = ?",
                new String[]{Integer.toString(codigo)});

    }

    //Consulta uma pessoa por codigo
    public Reserva getReserva(int codigo){

        Cursor cursor = databaseUtil.getConexaoDataBase().
                rawQuery("SELECT * FROM tb_reserva WHERE id = " + codigo, null);
        cursor.moveToFirst();

        Reserva reserva = new Reserva();

        reserva.setIdLocacao(cursor.getInt(cursor.getColumnIndex("id")));
        reserva.setNomeProfessor(cursor.getString(cursor.getColumnIndex("nome_prof_reserva")));
        reserva.setEquipamento(cursor.getString(cursor.getColumnIndex("nome_equip_reserva")));
        reserva.setData(cursor.getString(cursor.getColumnIndex("data_reserva")));
        reserva.setSala(cursor.getString(cursor.getColumnIndex("sala_reserva")));
        reserva.setHorarioInicial(cursor.getString(cursor.getColumnIndex("hora_inicial_reserva")));
        reserva.setHorarioFinal(cursor.getString(cursor.getColumnIndex("hora_final_reserva")));

        return reserva;
    }

    //Selecionar tadas as reservas
    public List<Reserva> selecionarTodos(){
        List<Reserva> reservas = new ArrayList<Reserva>();

        StringBuilder stringBuilderQuery = new StringBuilder();

        stringBuilderQuery.append("SELECT id, ");
        stringBuilderQuery.append("       nome_prof_reserva, ");
        stringBuilderQuery.append("       nome_equip_reserva, ");
        stringBuilderQuery.append("       sala_reserva, ");
        stringBuilderQuery.append("       data_reserva, ");
        stringBuilderQuery.append("       hora_inicial_reserva, ");
        stringBuilderQuery.append("       hora_final_reserva ");
        stringBuilderQuery.append("FROM tb_reserva ");
        stringBuilderQuery.append("ORDER BY data_reserva");

        Cursor cursor = databaseUtil.getConexaoDataBase().rawQuery(stringBuilderQuery.toString(),null);
        cursor.moveToFirst();

        Reserva reserva;

        while (!cursor.isAfterLast()){
            reserva = new Reserva();

            reserva.setIdLocacao(cursor.getInt(cursor.getColumnIndex("id")));
            reserva.setNomeProfessor(cursor.getString(cursor.getColumnIndex("nome_prof_reserva")));
            reserva.setEquipamento(cursor.getString(cursor.getColumnIndex("nome_equip_reserva")));
            reserva.setData(cursor.getString(cursor.getColumnIndex("data_reserva")));
            reserva.setSala(cursor.getString(cursor.getColumnIndex("sala_reserva")));
            reserva.setHorarioInicial(cursor.getString(cursor.getColumnIndex("hora_inicial_reserva")));
            reserva.setHorarioFinal(cursor.getString(cursor.getColumnIndex("hora_final_reserva")));

            reservas.add(reserva);

            cursor.moveToNext();
        }

        return reservas;
    }

}
