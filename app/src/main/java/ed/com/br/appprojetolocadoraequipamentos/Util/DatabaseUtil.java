package ed.com.br.appprojetolocadoraequipamentos.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edinilson.silva on 09/09/2016.
 */
public class DatabaseUtil extends SQLiteOpenHelper {

    private static final String NOME_BASE_DE_DADOS = "LOCADORA.db";

    private static final int VERSAO_BASE_DE_DADOS = 1;

    public DatabaseUtil(Context context) {
        super(context, NOME_BASE_DE_DADOS, null , VERSAO_BASE_DE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder stringBuilderCreateTabelaReserva = new StringBuilder();

        stringBuilderCreateTabelaReserva.append("CREATE TABLE tb_reserva (");
        stringBuilderCreateTabelaReserva.append("       id_reserva INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTabelaReserva.append("       nomeProfessor TEXT NOT NULL, ");
        stringBuilderCreateTabelaReserva.append("       equipamento TEXT NOT NULL, ");
        stringBuilderCreateTabelaReserva.append("       sala TEXT NOT NULL, ");
        stringBuilderCreateTabelaReserva.append("       data TEXT NOT NULL, ");
        stringBuilderCreateTabelaReserva.append("       horarioInicial TEXT NOT NULL, ");
        stringBuilderCreateTabelaReserva.append("       horarioFinal TEXT NOT NULL);");

        db.execSQL(stringBuilderCreateTabelaReserva.toString());

        StringBuilder stringBuilderCreateTabelaProfessor = new StringBuilder();

        stringBuilderCreateTabelaProfessor.append("CREATE TABLE tb_reserva (");
        stringBuilderCreateTabelaProfessor.append("       id_reserva INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTabelaProfessor.append("       nomeProfessor TEXT NOT NULL, ");
        stringBuilderCreateTabelaProfessor.append("       equipamento TEXT NOT NULL, ");
        stringBuilderCreateTabelaProfessor.append("       sala TEXT NOT NULL, ");
        stringBuilderCreateTabelaProfessor.append("       data TEXT NOT NULL, ");
        stringBuilderCreateTabelaProfessor.append("       horarioInicial TEXT NOT NULL, ");
        stringBuilderCreateTabelaProfessor.append("       horarioFinal TEXT NOT NULL);");

        db.execSQL(stringBuilderCreateTabelaProfessor.toString());

        StringBuilder stringBuilderCreateTabelaEquipamento = new StringBuilder();

        stringBuilderCreateTabelaEquipamento.append("CREATE TABLE tb_reserva (");
        stringBuilderCreateTabelaEquipamento.append("       id_reserva INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTabelaEquipamento.append("       nomeProfessor TEXT NOT NULL, ");
        stringBuilderCreateTabelaEquipamento.append("       equipamento TEXT NOT NULL, ");
        stringBuilderCreateTabelaEquipamento.append("       sala TEXT NOT NULL, ");
        stringBuilderCreateTabelaEquipamento.append("       data TEXT NOT NULL, ");
        stringBuilderCreateTabelaEquipamento.append("       horarioInicial TEXT NOT NULL, ");
        stringBuilderCreateTabelaEquipamento.append("       horarioFinal TEXT NOT NULL);");

        db.execSQL(stringBuilderCreateTabelaEquipamento.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS tb_reserva");
        onCreate(db);
    }

    public SQLiteDatabase getConexaoDataBase(){
        return  this.getWritableDatabase();
    }
}
