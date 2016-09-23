package ed.com.br.appprojetolocadoraequipamentos.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edinilson.silva on 09/09/2016.
 */
public class DatabaseUtil2 extends SQLiteOpenHelper {

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
            KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOMEPROFESSOR + " TEXT, " + KEY_EMAIL + " TEXT, "  +
            KEY_DISCIPLINA + " TEXT, " + KEY_CURSO + " TEXT" + ")";

    //CRIANDO TABLEA EQUIPAMENTO
    private static final String CRIA_TABLEA_EQUIPAMENTO = "CREATE TABLE " + TB_EQUIP + " (" +
            KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOMEEQUIP + " TEXT, " + KEY_QUANT + " TEXT" + ")";

    //CRIANDO TABELA RESERVA
    private static final String CRIAR_TABELA_RESERVA = "CREATE TABLE " + TB_RESERVA + " (" +
            KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOME_PROF_RESERVA + " INTEGER, " +
            KEY_NOME_EQUIP_RESERVA + " INTEGER, " + KEY_SALA + " TEXT, " + KEY_DATA + " DATETIME, " +
            KEY_HORA_INICIAL + " DATETIME, " + KEY_HORA_FINAL + " DATETIME" + ")";


    public DatabaseUtil2(Context context) {
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
