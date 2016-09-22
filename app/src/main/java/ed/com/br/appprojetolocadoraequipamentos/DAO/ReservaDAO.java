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

        contentValues.put("nomeProfessor", reserva.getNomeProfessor());
        contentValues.put("equipamento", reserva.getEquipamento());
        contentValues.put("sala", reserva.getSala());
        contentValues.put("data",  reserva.getData());
        contentValues.put("horarioInicial", reserva.getHorarioInicial());
        contentValues.put("horarioFinal", reserva.getHorarioFinal());

        databaseUtil.getConexaoDataBase().insert("tb_reserva",null, contentValues);
    }

    //ATUALIZAR RESERVA
    public void atualizar(Reserva reserva){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nomeProfessor", reserva.getNomeProfessor());
        contentValues.put("equipamento", reserva.getEquipamento());
        contentValues.put("sala", reserva.getSala());
        contentValues.put("data",  reserva.getData());
        contentValues.put("horarioInicial", reserva.getHorarioInicial());
        contentValues.put("horarioFinal", reserva.getHorarioFinal());

        databaseUtil.getConexaoDataBase().update("tb_reserva", contentValues, "id=reserva = ?",
                new String[]{Integer.toString(reserva.getIdLocacao())});
    }

    //EXCLUI RESERVA
    public Integer excluir(int codigo){

        return databaseUtil.getConexaoDataBase().delete("tb_reserva", "id_reserva = ?",
                new String[]{Integer.toString(codigo)});

    }

    //Consulta uma pessoa por codigo
    public Reserva getReserva(int codigo){

        Cursor cursor = databaseUtil.getConexaoDataBase().
                rawQuery("SELECT * FROM tb_reserva WHERE id_reserva= " + codigo, null);
        cursor.moveToFirst();

        Reserva reserva = new Reserva();

        reserva.setIdLocacao(cursor.getInt(cursor.getColumnIndex("id_reserva")));
        reserva.setNomeProfessor(cursor.getString(cursor.getColumnIndex("nomeProfessor")));
        reserva.setEquipamento(cursor.getString(cursor.getColumnIndex("equipamento")));
        reserva.setData(cursor.getString(cursor.getColumnIndex("data")));
        reserva.setSala(cursor.getString(cursor.getColumnIndex("sala")));
        reserva.setHorarioInicial(cursor.getString(cursor.getColumnIndex("horarioInicial")));
        reserva.setHorarioFinal(cursor.getString(cursor.getColumnIndex("horarioFinal")));

        return reserva;
    }

    //Selecionar tadas as pessoas
    public List<Reserva> selecionarTodos(){
        List<Reserva> reservas = new ArrayList<Reserva>();

        StringBuilder stringBuilderQuery = new StringBuilder();

        stringBuilderQuery.append("SELECT id_reserva, ");
        stringBuilderQuery.append("       nomeProfessor, ");
        stringBuilderQuery.append("       equipamento, ");
        stringBuilderQuery.append("       sala, ");
        stringBuilderQuery.append("       data, ");
        stringBuilderQuery.append("       horarioInicial, ");
        stringBuilderQuery.append("       horarioFinal ");
        stringBuilderQuery.append("FROM tb_reserva ");
        stringBuilderQuery.append("ORDER BY data");

        Cursor cursor = databaseUtil.getConexaoDataBase().rawQuery(stringBuilderQuery.toString(),null);
        cursor.moveToFirst();

        Reserva reserva;

        while (!cursor.isAfterLast()){
            reserva = new Reserva();

            reserva.setIdLocacao(cursor.getInt(cursor.getColumnIndex("id_reserva")));
            reserva.setNomeProfessor(cursor.getString(cursor.getColumnIndex("nomeProfessor")));
            reserva.setEquipamento(cursor.getString(cursor.getColumnIndex("equipamento")));
            reserva.setData(cursor.getString(cursor.getColumnIndex("data")));
            reserva.setSala(cursor.getString(cursor.getColumnIndex("sala")));
            reserva.setHorarioInicial(cursor.getString(cursor.getColumnIndex("horarioInicial")));
            reserva.setHorarioFinal(cursor.getString(cursor.getColumnIndex("horarioFinal")));

            reservas.add(reserva);

            cursor.moveToNext();
        }

        return reservas;
    }

}
