package alura.com.br.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alura.com.br.R;
import alura.com.br.model.Aluno;

public class StudentListAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public StudentListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_list_student, parent, false);
        Aluno alunoDevolvido = alunos.get(position);

        TextView nome = viewCriada.findViewById(R.id.item_list_nome);
        TextView email = viewCriada.findViewById(R.id.item_list_email);

        nome.setText(alunoDevolvido.getNome());
        email.setText(alunoDevolvido.getEmail());
        return viewCriada;
    }

    public void remove(Aluno alunoEscolhido) {
        alunos.remove(alunoEscolhido);
        notifyDataSetChanged();
    }

    public void clear() {
        alunos.clear();
    }

    public void addAll(List<Aluno> todos) {
        alunos.addAll(todos);
        notifyDataSetChanged();
    }
}
