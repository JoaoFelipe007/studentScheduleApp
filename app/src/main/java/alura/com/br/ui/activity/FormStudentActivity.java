package alura.com.br.ui.activity;

import static alura.com.br.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;

public class FormStudentActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_ADICIONA_ALUNO = "Adicionar novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO ="Editar aluno" ;
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_student_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_form_student_menu_salvar){
            salvaAluno();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent(); // recupera os dados enviado da lista
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);        // faz o cast do Serializable para o objeto
            campoNome.setText(aluno.getNome());
            campoEmail.setText(aluno.getEmail());
            campoTelefone.setText(aluno.getTelefone());
        } else {
            setTitle(TITULO_APPBAR_ADICIONA_ALUNO);
            aluno = new Aluno();
        }
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_form_student_name);
        campoTelefone = findViewById(R.id.activity_form_student_telefone);
        campoEmail = findViewById(R.id.activity_form_student_email);
    }

    private void salvaAluno() {
         preencheAluno();
        if (aluno.getId() > 0) {
            alunoDAO.edita(aluno);
        }else{
            alunoDAO.salva(aluno);
        }
        finish();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}