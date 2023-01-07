package alura.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;

public class FormStudentActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Adicionar novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent(); // recupera os dados enviado da lista
        // faz o cast do Serializable para o objeto
        aluno = (Aluno) dados.getSerializableExtra("aluno");
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
    }

    private void configuraBotaoSalvar() {
        Button saveButton = findViewById(R.id.activity_form_student_button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaAluno();
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_form_student_name);
        campoTelefone = findViewById(R.id.activity_form_student_telefone);
        campoEmail = findViewById(R.id.activity_form_student_email);
    }

    private void salvaAluno() {
//        Aluno alunoCriado =  preencheAluno();
//        alunoDAO.salva(alunoCriado);
        preencheAluno();
        alunoDAO.edita(aluno);
        finish();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email= campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}