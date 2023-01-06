package alura.com.br.ui.activity;

import static alura.com.br.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ListStudentActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de aluno";
    private FloatingActionButton newStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_list_student);
        setTitle(TITULO_APPBAR);
        Toast.makeText(this,"Bem vindo a sua Agenda",Toast.LENGTH_SHORT).show();//Aparece uma msg quando abre o app

        configuraFabNovoAluno();

    }

    private void configuraFabNovoAluno() {
         newStudent = findViewById(R.id.activity_list_student_fb_new_student);

        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListStudentActivity.this,FormStudentActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunos();
    }

    private void listaAlunos() {
        AlunoDAO dao = new AlunoDAO();
        ListView view = findViewById(R.id.acitivity_list_students_listview);
        view.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()));
    }
}
