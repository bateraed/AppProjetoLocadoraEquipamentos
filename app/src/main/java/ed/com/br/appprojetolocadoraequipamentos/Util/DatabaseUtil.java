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

    private static final String TB_RESERVA = "tb_reserva";
    private static final String TB_PROFESSOR = "tb_professor";
    private static final String TB_EQUIP = "tb_equip";

    private static final String KEY_ID = "id";

    //COLUNA TABELA PROFESSOR
    private static final String KEY_NOMEPROFESSOR = "nome_prof";
    private static final String KEY_EMAIL = "email_prof";
    private static final String KEY_DISCIPLINA = "disciplina_prof";
    private static final String KEY_CURSO = "curso_prof";

    //COLUNA TABELA EQUIPAMENTO
    private static final String KEY_NOMEEQUIP = "nome_equip";
    private static final String KEY_QUANT = "quant_equip";

    //COLUNA TABELA RESERVA
    private static final String KEY_NOME_PROF_RESERVA = "nome_prof_reserva";
    private static final String KEY_NOME_EQUIP_RESERVA = "nome_equip_reserva";
    private static final String KEY_SALA = "sala_reserva";
    private static final String KEY_DATA = "data_reserva";
    private static final String KEY_HORA_INICIAL = "hora_inicial_reserva";
    private static final String KEY_HORA_FINAL = "hora_final_reserva";

    //CRIANDO TABELA PROFESSORA
    private static final String CRIAR_TABELA_PROFESSOR = "CREATE TABLE " + TB_PROFESSOR + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NOMEPROFESSOR + " TEXT, " + KEY_EMAIL + " TEXT, "  +
            KEY_DISCIPLINA + " TEXT, " + KEY_CURSO + " TEXT" + ")";

    //CRIANDO TABLEA EQUIPAMENTO
    private static final String CRIAR_TABElA_EQUIPAMENTO = "CREATE TABLE " + TB_EQUIP + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NOMEEQUIP + " TEXT, " + KEY_QUANT + " TEXT" + ")";

    //CRIANDO TABELA RESERVA
    private static final String CRIAR_TABELA_RESERVA = "CREATE TABLE " + TB_RESERVA + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NOME_PROF_RESERVA + " INTEGER, " +
            KEY_NOME_EQUIP_RESERVA + " INTEGER, " + KEY_SALA + " TEXT, " + KEY_DATA + " DATETIME, " +
            KEY_HORA_INICIAL + " DATETIME, " + KEY_HORA_FINAL + " DATETIME" + ")";


    public DatabaseUtil(Context context) {
        super(context, NOME_BASE_DE_DADOS, null , VERSAO_BASE_DE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CRIANDO TABELAS
        db.execSQL(CRIAR_TABELA_PROFESSOR);
        db.execSQL(CRIAR_TABElA_EQUIPAMENTO);
        db.execSQL(CRIAR_TABELA_RESERVA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TB_PROFESSOR);
        db.execSQL("DROP TABLE IF EXISTS " + TB_EQUIP);
        db.execSQL("DROP TABLE IF EXISTS " + TB_RESERVA);

        onCreate(db);
    }

    public SQLiteDatabase getConexaoDataBase(){
        return  this.getWritableDatabase();
    }
}
