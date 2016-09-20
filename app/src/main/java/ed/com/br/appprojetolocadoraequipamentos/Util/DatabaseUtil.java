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

        StringBuilder stringBuilderCreatetable = new StringBuilder();

        stringBuilderCreatetable.append("CREATE TABLE tb_reserva (");
        stringBuilderCreatetable.append("       id_reserva INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreatetable.append("       nomeProfessor TEXT NOT NULL, ");
        stringBuilderCreatetable.append("       equipamento TEXT NOT NULL, ");
        stringBuilderCreatetable.append("       sala TEXT NOT NULL, ");
        stringBuilderCreatetable.append("       data TEXT NOT NULL, ");
        stringBuilderCreatetable.append("       horarioInicial TEXT NOT NULL, ");
        stringBuilderCreatetable.append("       horarioFinal TEXT NOT NULL);");

        db.execSQL(stringBuilderCreatetable.toString());
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
