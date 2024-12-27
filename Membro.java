import java.util.ArrayList;
import java.util.List;

public class Membro {
    private String nome;
    private int id;
    private int limiteEmprestimos;
    private List<Livro> livrosEmprestados;

    public Membro(String nome, int id, int limiteEmprestimos) {
        this.nome = nome;
        this.id = id;
        this.limiteEmprestimos = limiteEmprestimos;
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public void setLimiteEmprestimos(int limiteEmprestimos) {
        this.limiteEmprestimos = limiteEmprestimos;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public boolean adicionarLivroEmprestado(Livro livro) {
        if (livrosEmprestados.size() < limiteEmprestimos) {
            livrosEmprestados.add(livro);
            return true;
        } else {
            System.out.println("Limite de empréstimos atingido.");
            return false;
        }
    }

    public void removerLivroEmprestado(Livro livro) {
        livrosEmprestados.remove(livro);
    }

    @Override
    public String toString() {
        return "Membro{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", limiteEmprestimos=" + limiteEmprestimos +
                ", livrosEmprestados=" + livrosEmprestados +
                '}';
    }
}
