import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Biblioteca {
    private List<Livro> livros;
    private List<Membro> membros;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        livros = new ArrayList<>();
        membros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarMembro(Membro membro) {
        membros.add(membro);
    }

    public void emprestarLivro(String ISBN, int membroId) {
        Optional<Livro> livroOpt = livros.stream().filter(l -> l.getISBN().equals(ISBN)).findFirst();
        Optional<Membro> membroOpt = membros.stream().filter(m -> m.getId() == membroId).findFirst();

        if (livroOpt.isPresent() && membroOpt.isPresent()) {
            Livro livro = livroOpt.get();
            Membro membro = membroOpt.get();

            if (livro.isDisponivel() && membro.adicionarLivroEmprestado(livro)) {
                livro.emprestar();
                emprestimos.add(new Emprestimo(livro, membro, LocalDate.now()));
                System.out.println("Livro emprestado com sucesso.");
            } else {
                System.out.println("Não foi possível emprestar o livro.");
            }
        } else {
            System.out.println("Livro ou Membro não encontrado.");
        }
    }

    public void devolverLivro(String ISBN, int membroId) {
        Optional<Livro> livroOpt = livros.stream().filter(l -> l.getISBN().equals(ISBN)).findFirst();
        Optional<Membro> membroOpt = membros.stream().filter(m -> m.getId() == membroId).findFirst();

        if (livroOpt.isPresent() && membroOpt.isPresent()) {
            Livro livro = livroOpt.get();
            Membro membro = membroOpt.get();

            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getLivro().equals(livro) && emprestimo.getMembro().equals(membro) && emprestimo.getDataDevolucao() == null) {
                    emprestimo.registrarDevolucao();
                    livro.devolver();
                    membro.removerLivroEmprestado(livro);
                    System.out.println("Livro devolvido com sucesso.");
                    return;
                }
            }
            System.out.println("Empréstimo não encontrado.");
        } else {
            System.out.println("Livro ou Membro não encontrado.");
        }
    }

    public void listarEmprestimos() {
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "livros=" + livros +
                ", membros=" + membros +
                ", emprestimos=" + emprestimos +
                '}';
    }
}
