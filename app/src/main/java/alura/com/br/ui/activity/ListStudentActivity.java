package alura.com.br.ui.activity;

import static alura.com.br.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;
import alura.com.br.ui.adapter.StudentListAdapter;

public class ListStudentActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de aluno";
    private FloatingActionButton newStudent;
    AlunoDAO dao = new AlunoDAO();
    private StudentListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_list_student);
        setTitle(TITULO_APPBAR);
        Toast.makeText(this, "Bem vindo a sua Agenda", Toast.LENGTH_SHORT).show();//Aparece uma msg quando abre o app
        listaAlunos();/*veio para o onCreate pois as informações
         só precisam ser executadas uma unica vez e não toda
         s as vezes que a activity entra em on resume*/
        configuraFabNovoAluno();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {// Cria um menu de contexto
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_student_menu, menu);/*Vincula a activity de menu, usando uma tecnica de
        inflar (Vai pegar o arquivo estatico e vai transformar em um onjeto de menu)*/
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();//pega o id do menu
        if (itemId == R.id.activity_list_student_menu_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();/* .getmenuInfo é usado para pegar mais
       informações... porém do jeito que está e de um modo especifico para AdapterViews*/
            Aluno alunoEscolhido = (Aluno) adapter.getItem(menuInfo.position);
            dao.remove(alunoEscolhido);
            adapter.remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();//Enquanto tiver neeste estado ele ira limapr os dados da listView
        adapter.addAll(dao.todos());//E logo após ele ira adcionar tudo novamente na listView
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


    private void listaAlunos() {
        ListView listaAluno = findViewById(R.id.acitivity_list_students_listview);
        configuraAdapter(listaAluno);
        configuraClickPorItem(listaAluno);
        registerForContextMenu(listaAluno);
    }
//
//    private void configuraClickLongPorItem(ListView listaAluno) {
//        listaAluno.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Aluno alunoEscolhido = (Aluno)parent.getItemAtPosition(position);
//                dao.remove(alunoEscolhido);
//                adapter.remove(alunoEscolhido);
//                return false;
//            }
//        });
//    }

    private void configuraAdapter(ListView listaAluno) {
        adapter = new StudentListAdapter(this);
        listaAluno.setAdapter(adapter);
    }

    private void configuraClickPorItem(ListView listaAluno) {
        listaAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Metodo que irá pegar as informações do aluno e enviar para o formulario
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position); //com base no item selecionado ele consegue retornar um objeto
                Intent vaiParaFormularioActivity = new Intent(ListStudentActivity.this, FormStudentActivity.class);
                vaiParaFormularioActivity.putExtra("aluno", alunoEscolhido);
                /* através do putExtra vc manda o objeto e suas informações para a outra activity
                  e nele vc também manda uma chave, para que ser usada na outra activity
                *  só pode ser usado por uma varivel do tipo intent
                */
                startActivity(vaiParaFormularioActivity);
            }
        });
    }
}
