package ed.com.br.appprojetolocadoraequipamentos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import ed.com.br.appprojetolocadoraequipamentos.DAO.ReservaDAO;
import ed.com.br.appprojetolocadoraequipamentos.Model.Equipamento;
import ed.com.br.appprojetolocadoraequipamentos.Model.Reserva;
import ed.com.br.appprojetolocadoraequipamentos.Model.Sala;
import ed.com.br.appprojetolocadoraequipamentos.Util.Util;

public class EditarLocacaoActivity extends AppCompatActivity {

    Spinner spinnerSala, spinnerEquipamento;
    EditText editTextData, editTextHoraInicio, editTextHoraFinal, editTextProfessor;
    Button voltar, concluir;
    DatePickerDialog datePickerDialogDataLocacao;
    TimePickerDialog timePickerDialogHorarioInicial, timePickerDialogHorarioFinal;

    ArrayAdapter<String> arrayAdapterSalas, arrayAdapterEquipamentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_locacao);

        criarComponetes();
        criarEventos();
        carregaSalas();
        listarEquipamentos();
        carregarDados();
    }

    private void criarComponetes() {
        editTextProfessor = (EditText) this.findViewById(R.id.editTextProf);
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
                eventoAlterar();
            }
        });
    }

    private void eventoAlterar() {

        if(editTextProfessor.getText().toString().trim().equals("")){
            Util.alert(this, this.getString(R.string.nome_obrigatorio));
            editTextProfessor.requestFocus();
        } else if (spinnerEquipamento.getSelectedItem().equals("")){
            Util.alert(this, this.getString(R.string.equipamento_obrigatorio));
        } else if (editTextData.getText().toString().trim().equals("")){
            Util.alert(this, "Data obrigatória!");
            editTextData.requestFocus();
        } else if (editTextHoraFinal.getText().toString().trim().equals("")){
            Util.alert(this, "Horário obrigatório!");
            editTextHoraFinal.requestFocus();
        } else if (editTextHoraInicio.getText().toString().trim().equals("")){
            Util.alert(this, "Horário Obrigatório!");
            editTextHoraInicio.requestFocus();
        } else {
            Reserva reserva = new Reserva();
            reserva.setNomeProfessor(editTextProfessor.getText().toString());
            reserva.setEquipamento(spinnerEquipamento.getSelectedItem().toString());
            reserva.setSala(spinnerSala.getSelectedItem().toString());
            reserva.setData(editTextData.getText().toString().trim());
            reserva.setHorarioInicial(editTextHoraInicio.getText().toString().trim());
            reserva.setHorarioFinal(editTextHoraFinal.getText().toString().trim());

            new ReservaDAO(this).atualizar(reserva);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Locadoção de Equipamentos");
            builder.setMessage("Registro alterado com sucesso!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getApplicationContext(), ConsultaReservaActicity.class);
                    startActivity(intent);
                    finish();
                }
            });

            builder.show();
        }
    }

    private void carregaSalas() {

        Sala s1 = new Sala("A 01");
        Sala s2 = new Sala("A 02");
        Sala s3 = new Sala("B 01");
        Sala s4 = new Sala("B 02");

        List<String> salas = new ArrayList<>();
        salas.add(s1.getNome());
        salas.add(s2.getNome());
        salas.add(s3.getNome());
        salas.add(s4.getNome());

        arrayAdapterSalas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, salas);
        arrayAdapterSalas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSala.setAdapter(arrayAdapterSalas);
    }

    private void listarEquipamentos() {

        Equipamento e1 = new Equipamento("DataShow Epson Powerlite X24+");
        Equipamento e2 = new Equipamento("DataShow Epson Powerlite S18+");
        Equipamento e3 = new Equipamento("DataShow PHILIPS PPX2480");
        Equipamento e4 = new Equipamento("DataShow SONY VPLDX140");
        Equipamento e5 = new Equipamento("Lousa Digital");

        List<String> equipamentos = new ArrayList<String>();
        equipamentos.add(e1.getNome());
        equipamentos.add(e2.getNome());
        equipamentos.add(e3.getNome());
        equipamentos.add(e4.getNome());
        equipamentos.add(e5.getNome());

        arrayAdapterEquipamentos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, equipamentos);
        arrayAdapterEquipamentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerEquipamento.setAdapter(arrayAdapterEquipamentos);
    }

    private void carrgarSalaBanco(String idSala){
        for (int i = 0; i < arrayAdapterSalas.getCount(); i++){
            if((arrayAdapterSalas.getItem(i)).equals(idSala)){
                spinnerSala.setSelection(i);
                break;
            }
        }
    }

    private void carregarEquipamentoBanco(String idEquipamento){
        for (int i = 0; i < arrayAdapterEquipamentos.getCount(); i++){
            if((arrayAdapterEquipamentos.getItem(i)).equals(idEquipamento)){
                spinnerSala.setSelection(i);
                break;
            }
        }
    }

    protected void carregarDados(){

        ReservaDAO reservaDAO = new ReservaDAO(this);
        Bundle extra = this.getIntent().getExtras();
        int id_reserva = extra.getInt("id");

        Reserva reserva = reservaDAO.getReserva(id_reserva);

        editTextProfessor.setText(reserva.getNomeProfessor());
        this.carregarEquipamentoBanco(reserva.getEquipamento());
        this.carrgarSalaBanco(reserva.getSala());
        editTextData.setText(reserva.getData());
        editTextHoraInicio.setText(reserva.getHorarioInicial());
        editTextHoraFinal.setText(reserva.getHorarioFinal());
    }



}
