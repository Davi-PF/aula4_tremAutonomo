import java.util.Scanner;

abstract class Trem {
    protected int posicao;
    protected int limiteMinimo;
    protected int limiteMaximo;

    public Trem(int limiteMinimo, int limiteMaximo, int posicaoInicial) {
        this.limiteMinimo = limiteMinimo;
        this.limiteMaximo = limiteMaximo;
        posicao = posicaoInicial;
    }

    public abstract void mover(String direcao);

    public int getPosicao() {
        return posicao;
    }
}

class TremTrilhoInfinito extends Trem {
    public TremTrilhoInfinito(int posicaoInicial) {
        super(Integer.MIN_VALUE, Integer.MAX_VALUE, posicaoInicial);
    }

    @Override
    public void mover(String direcao) {
        if (direcao.equals("ESQUERDA")) {
            posicao--;
        } else if (direcao.equals("DIREITA")) {
            posicao++;
        }
    }
}

class TremTrilhoFinito extends Trem {
    public TremTrilhoFinito(int limiteMinimo, int limiteMaximo, int posicaoInicial) {
        super(limiteMinimo, limiteMaximo, posicaoInicial);
    }

    @Override
    public void mover(String direcao) {
        int novaPosicao = posicao;

        if (direcao.equals("ESQUERDA") || direcao.equals("esquerda")) {
            novaPosicao--;
        } else if (direcao.equals("DIREITA") || direcao.equals("direita")) {
            novaPosicao++;
        }

        if (novaPosicao >= limiteMinimo && novaPosicao <= limiteMaximo) {
            posicao = novaPosicao;
        }
    }
}

public class TremAutonomo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o tipo de trilho (1 para infinito, 2 para finito): ");
        int tipoTrilho = scanner.nextInt();

        Trem trem;

        if (tipoTrilho == 1) {
            System.out.print("Informe a posição inicial do trem no trilho infinito: ");
            int posicaoInicial = scanner.nextInt();
            trem = new TremTrilhoInfinito(posicaoInicial);
        } else {
            System.out.print("Informe o limite mínimo do trilho finito: ");
            int limiteMinimo = scanner.nextInt();
            System.out.print("Informe o limite máximo do trilho finito: ");
            int limiteMaximo = scanner.nextInt();
            System.out.print("Informe a posição inicial do trem no trilho finito: ");
            int posicaoInicial = scanner.nextInt();
            trem = new TremTrilhoFinito(limiteMinimo, limiteMaximo, posicaoInicial);
        }

        System.out.print("Informe a lista de comandos (separados por espaço, máximo 30 comandos): ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String listaComandos = scanner.nextLine();
        String[] comandos = listaComandos.split(" ");

        if (comandos.length > 30) {
            System.out.println("Número máximo de comandos permitido é 30.");
            scanner.close();
            return;
        }

        System.out.println("Comandos informados: " + listaComandos);
        System.out.println("Posição Inicial: " + trem.getPosicao());

        for (String comando : comandos) {
            trem.mover(comando);
        }

        System.out.println("Posição Final: " + trem.getPosicao());

        scanner.close();
    }
}
