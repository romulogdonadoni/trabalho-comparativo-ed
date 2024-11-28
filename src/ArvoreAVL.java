public class ArvoreAVL {
    private class No {
        int valor;
        No esquerda;
        No direita;
        int altura;

        No(int valor) {
            this.valor = valor;
            this.altura = 1;
        }
    }

    private No raiz;

    private int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    private int getFatorBalanceamento(No no) {
        return no == null ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
        } else {
            return no;
        }

        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;

        int fatorBalanceamento = getFatorBalanceamento(no);

        if (fatorBalanceamento > 1 && valor < no.esquerda.valor) {
            return rotacaoDireita(no);
        }
        if (fatorBalanceamento < -1 && valor > no.direita.valor) {
            return rotacaoEsquerda(no);
        }
        if (fatorBalanceamento > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (fatorBalanceamento < -1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(No no, int valor) {
        if (no == null) {
            return false;
        }
        if (valor == no.valor) {
            return true;
        }
        return valor < no.valor 
            ? buscarRecursivo(no.esquerda, valor)
            : buscarRecursivo(no.direita, valor);
    }
} 