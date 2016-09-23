package ed.com.br.appprojetolocadoraequipamentos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ed.com.br.appprojetolocadoraequipamentos.DAO.ProfessorDAO;
import ed.com.br.appprojetolocadoraequipamentos.DAO.ReservaDAO;
import ed.com.br.appprojetolocadoraequipamentos.Model.Equipamento;
import ed.com.br.appprojetolocadoraequipamentos.Model.Professor;
import ed.com.br.appprojetolocadoraequipamentos.Model.Reserva;
import ed.com.br.appprojetolocadoraequipamentos.Util.Util;

/**
 * Created by edinilson.silva on 14/09/2016.
 */
public class LocacaoActivity extends Activity {

    Spinner spinnerProfessor, spinnerSala, spinnerEquipamento;
    EditText editTextData, editTextHoraInicio, editTextHoraFinal;
    Button voltar, concluir;
    DatePickerDialog datePickerDialogDataLocacao;
    TimePickerDialog timePickerDialogHorarioInicial, timePickerDialogHorarioFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locacao);

        criarComponetes();
        criarEventos();
        localizcao();
        carregaSalas();
        listarProfessor();
        listarEquipamentos();
    }

    private void criarComponetes() {
        spinnerProfessor = (Spinner) this.findViewById(R.id.spinnerProfessor);
        spinnerSala = (Spinner) this.findViewById(R.id.spinnerSala);
        spinnerEquipamento = (Spinner) this.findViewById(R.id.spinnerEquipamento);
        editTextData = (EditText) this.findViewById(R.id.editTextDataLocacao);
        editTextHoraInicio = (EditText) this.findViewById(R.id.editTextHorarioInicial);
        editTextHoraFinal = (EditText) this.findViewById(R.id.editTextHorarioFinal);
        voltar = (Button) this.findViewById(R.id.buttonVoltar);
        concluir = (Button) this.findViewById(R.id.buttonConcluir);
    }

    private void criarEventos() {

        final Calendar calendarDataAtual = Calendar.getInstance();
        int anoAtual = calendarDataAtual.get(Calendar.YEAR);
        int mesAtual = calendarDataAtual.get(Calendar.MONTH);
        int diaAtual = calendarDataAtual.get(Calendar.DAY_OF_MONTH);

        final Calendar horarioAtual = Calendar.getInstance();
        int horaAtual = horarioAtual.get(Calendar.HOUR_OF_DAY);
        int minutoAtual = horarioAtual.get(Calendar.MINUTE);

        //CRIANDO DATA
        datePickerDialogDataLocacao = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
                String mes = (String.valueOf(mesSelecionado + 1).length() == 1 ? "0" + (mesSelecionado + 1) : String.valueOf(mesSelecionado));

                editTextData.setText(diaSelecionado + "/" + mes + "/" + anoSelecionado);
            }
        }, anoAtual, mesAtual, diaAtual);

        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogDataLocacao.show();
            }
        });

        //CRIANDO HORÁRIOS INICIAL
        timePickerDialogHorarioInicial = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker arg0, int hora, int minutos) {

                editTextHoraInicio.setText(hora + ":" + minutos);
            }
        }, horaAtual, minutoAtual, true);

        editTextHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialogHorarioInicial.show();
            }
        });

        //CRIANDO HORÁRIO FINAL
        timePickerDialogHorarioFinal = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hora, int minutos) {
                editTextHoraFinal.setText(hora + ":" + minutos);
            }
        }, horaAtual, minutoAtual, true);

        editTextHoraFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialogHorarioFinal.show();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVoltar = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentVoltar);
                finish();
            }
        });

        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventoSalvar();
            }
        });
    }

    private void localizcao() {
        Locale locale = new Locale("pt", "BR");
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
    }

    private void carregaSalas() {

        ArrayAdapter<String> arrayAdapter;

        List<String> itens = new ArrayList<>();

        itens.add("A01");
        itens.add("A02");
        itens.add("A03");
        itens.add("B01");
        itens.add("B02");
        itens.add("B03");
        itens.add("C01");
        itens.add("C02");
        itens.add("C03");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itens);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSala.setAdapter(arrayAdapter);
    }

    private void listarEquipamentos() {

        Equipamento e1 = new Equipamento(1, "DataShow", 5);
        Equipamento e2 = new Equipamento(2, "Lousa", 2);

        List<String> equipamentos = new ArrayList<>();
        equipamentos.add(e1.getNome());
        equipamentos.add(e2.getNome());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, equipamentos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerEquipamento.setAdapter(arrayAdapter);
    }

    private void listarProfessor() {

        Professor professor = new Professor();
        ProfessorDAO professorDAO = new ProfessorDAO(this);
        List<Professor> professors = professorDAO.selecionarTodos();

        ArrayAdapter<Professor> arrayAdapter = new ArrayAdapter<Professor>(this, android.R.layout.simple_spinner_item,professors);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProfessor.setAdapter(arrayAdapter);
    }

    private void eventoSalvar(){

        Reserva reserva = new Reserva();
        reserva.setNomeProfessor(spinnerProfessor.getSelectedItem().toString());
        reserva.setEquipamento(spinnerEquipamento.getSelectedItem().toString());
        reserva.setSala(spinnerSala.getSelectedItem().toString());
        if(editTextData.getText().toString().trim().equals("")){
            Util.alert(this, "Data obrigatória!");
            editTextData.requestFocus();
        } else if (editTextHoraFinal.getText().toString().trim().equals("")){
            Util.alert(this, "Horário obrigatório!");
            editTextHoraFinal.requestFocus();
        } else if (editTextHoraInicio.getText().toString().trim().equals("")){
            Util.alert(this, "Horário Obrigatório!");
            editTextHoraInicio.requestFocus();
        }
        reserva.setData(editTextData.getText().toString().trim());
        reserva.setHorarioInicial(editTextHoraInicio.getText().toString().trim());
        reserva.setHorarioFinal(editTextHoraFinal.getText().toString().trim());

        new ReservaDAO(this).salvar(reserva);
        Util.alert(this, this.getString(R.string.regsistro_salvo_sucesso) );
        limparCampos();
    }

    private void limparCampos(){
        editTextData.setText(null);
        editTextHoraFinal.setText(null);
        editTextHoraInicio.setText(null);
    }
}
