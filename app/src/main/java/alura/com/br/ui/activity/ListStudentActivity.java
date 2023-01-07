package alura.com.br.ui.activity;

import static alura.com.br.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;

public class ListStudentActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de aluno";
    private FloatingActionButton newStudent;
    AlunoDAO dao = new AlunoDAO();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_list_student);
        setTitle(TITULO_APPBAR);
        Toast.makeText(this, "Bem vindo a sua Agenda", Toast.LENGTH_SHORT).show();//Aparece uma msg quando abre o app
        dao.salva(new Aluno("João", "17999855", "joao@gmail.com"));
        dao.salva(new Aluno("Rai", "17999844", "rai@gmail.com"));
        configuraFabNovoAluno();

    }

    private void configuraFabNovoAluno() {
        newStudent = findViewById(R.id.activity_list_student_fb_new_student);

        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListStudentActivity.this, FormStudentActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunos();
    }

    private void listaAlunos() {
        ListView listaAluno = findViewById(R.id.acitivity_list_students_listview);
        final List<Aluno> alunos = dao.todos();
        configuraAdapter(listaAluno, alunos);
        configuraClickPorItem(listaAluno);

        listaAluno.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno)parent.getItemAtPosition(position);
                dao.remove(alunoEscolhido);
                return false;
            }
        });
    }

    private void configuraAdapter(ListView listaAluno, List<Aluno> alunos) {
        listaAluno.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));
    }

    private void configuraClickPorItem(ListView listaAluno) {
        listaAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Metodo que irá pegar as informações do aluno e enviar para o formulario
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position); //com base no item selecionado ele consegue retornar um objeto
                Intent vaiParaFormularioActivity =new Intent(ListStudentActivity.this,FormStudentActivity.class);
                vaiParaFormularioActivity.putExtra("aluno",alunoEscolhido);
                /* através do putExtra vc manda o objeto e suas informações para a outra activity
                  e nele vc também manda uma chave, para que ser usada na outra activity
                *  só pode ser usado por uma varivel do tipo intent
                */
                startActivity(vaiParaFormularioActivity);
            }
        });
    }
}
