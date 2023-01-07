package alura.com.br.dao;

import java.util.ArrayList;
import java.util.List;

import alura.com.br.model.Aluno;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static Long contadordeIds = 1L;

    public void salva(Aluno aluno) {
        aluno.setId(contadordeIds);
        alunos.add(aluno);
        contadordeIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno a : alunos) {
            if (a.getId().equals(aluno.getId())) {
                alunoEncontrado = a;
            }
        }
        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
