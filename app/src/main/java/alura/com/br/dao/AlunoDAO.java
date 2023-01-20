package alura.com.br.dao;

import androidx.annotation.Nullable;

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
        aluno = new Aluno();
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno a : alunos) {
            if (a.getId().equals(aluno.getId())) {
                alunoEncontrado = a;
            }
        }
        return alunoEncontrado;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
     Aluno alunoEscolhido = buscaAlunoPeloId(aluno);
     alunos.remove(alunoEscolhido);
    }
}
