public class App {
    public static void main(String[] args) {
        int[] tamanhos = { 100, 1000, 10000 };

        Vetor vetor = new Vetor();
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        for (int tamanho : tamanhos) {
            System.out.println("\nTestando conjunto com " + tamanho + " elementos");

            int[] dadosOrdenados = gerarDadosAleatorios(tamanho);
            int[] dadosInversos = gerarDadosAleatorios(tamanho);
            int[] dadosAleatorios = gerarDadosAleatorios(tamanho);

            dadosOrdenados = gerarDadosOrdenados(tamanho);
            dadosInversos = gerarDadosInversos(tamanho);
            dadosAleatorios = gerarDadosAleatorios(tamanho);

            testarEstrutura(vetor, dadosOrdenados, "Vetor - Ordenado");
            testarEstrutura(vetor, dadosInversos, "Vetor - Inverso");
            testarEstrutura(vetor, dadosAleatorios, "Vetor - Aleatório");

            testarEstrutura(arvoreBinaria, dadosOrdenados, "Árvore Binária - Ordenado");
            testarEstrutura(arvoreBinaria, dadosInversos, "Árvore Binária - Inverso");
            testarEstrutura(arvoreBinaria, dadosAleatorios, "Árvore Binária - Aleatório");

            testarEstrutura(arvoreAVL, dadosOrdenados, "Árvore AVL - Ordenado");
            testarEstrutura(arvoreAVL, dadosInversos, "Árvore AVL - Inverso");
            testarEstrutura(arvoreAVL, dadosAleatorios, "Árvore AVL - Aleatório");

            double tempoBubbleSort = 0;
            double tempoQuickSort = 0;

            for (int i = 0; i < 5; i++) {
                tempoBubbleSort += testarBubbleSort(dadosAleatorios);
                tempoQuickSort += testarQuickSort(dadosAleatorios);
            }

            System.out.println("\nTeste de desempenho dos algoritmos de ordenação");
            System.out.println("Bubble Sort: " + (tempoBubbleSort / 5) + " ms");
            System.out.println("Quick Sort: " + (tempoQuickSort / 5) + " ms");
        }

    }

    private static double testarBubbleSort(int[] dados) {
        long inicio = System.nanoTime();
        ordenarBubbleSort(dados);
        long fim = System.nanoTime();
        return (fim - inicio) / 1_000_000.0;
    }

    private static double testarQuickSort(int[] dados) {
        long inicio = System.nanoTime();
        ordenarQuickSort(dados);
        long fim = System.nanoTime();
        return (fim - inicio) / 1_000_000.0;
    }

    private static int[] gerarDadosOrdenados(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = i;
        }
        return dados;
    }

    private static int[] gerarDadosInversos(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = tamanho - i;
        }
        return dados;
    }

    private static int[] gerarDadosAleatorios(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = (int) (Math.random() * tamanho);
        }
        return dados;
    }

    private static int[] ordenarBubbleSort(int[] dados) {
        int[] dadosOrdenados = dados.clone();
        int n = dadosOrdenados.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (dadosOrdenados[j] > dadosOrdenados[j + 1]) {
                    int temp = dadosOrdenados[j];
                    dadosOrdenados[j] = dadosOrdenados[j + 1];
                    dadosOrdenados[j + 1] = temp;
                }
            }
        }
        return dadosOrdenados;
    }

    private static int[] ordenarQuickSort(int[] dados) {
        int[] dadosOrdenados = dados.clone();
        quickSort(dadosOrdenados, 0, dadosOrdenados.length - 1);
        return dadosOrdenados;
    }

    private static void quickSort(int[] dados, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = particionar(dados, inicio, fim);
            quickSort(dados, inicio, posicaoPivo - 1);
            quickSort(dados, posicaoPivo + 1, fim);
        }
    }

    private static int particionar(int[] dados, int inicio, int fim) {
        int pivo = dados[fim];
        int i = (inicio - 1);

        for (int j = inicio; j < fim; j++) {
            if (dados[j] <= pivo) {
                i++;
                int temp = dados[i];
                dados[i] = dados[j];
                dados[j] = temp;
            }
        }

        int temp = dados[i + 1];
        dados[i + 1] = dados[fim];
        dados[fim] = temp;

        return i + 1;
    }

    private static boolean buscaBinaria(int[] dados, int elemento) {
        int inicio = 0;
        int fim = dados.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            if (dados[meio] == elemento)
                return true;
            if (dados[meio] < elemento)
                inicio = meio + 1;
            else
                fim = meio - 1;
        }
        return false;
    }

    private static void testarEstrutura(Object estrutura, int[] dados, String descricao) {
        Cronometro cronometro = new Cronometro();
        System.out.println("\nTestando " + descricao);

        cronometro.iniciar();
        for (int valor : dados) {
            if (estrutura instanceof Vetor) {
                ((Vetor) estrutura).inserir(valor);
            } else if (estrutura instanceof ArvoreBinaria) {
                ((ArvoreBinaria) estrutura).inserir(valor);
            } else if (estrutura instanceof ArvoreAVL) {
                ((ArvoreAVL) estrutura).inserir(valor);
            }
        }
        cronometro.parar();
        System.out.printf("Tempo de inserção: %.3f ms%n", cronometro.getTempoDecorrido());

        int[] elementosBusca = {
                dados[0],
                dados[dados.length - 1],
                dados[dados.length / 2],
                -1
        };

        for (int elemento : elementosBusca) {
            cronometro.iniciar();
            boolean encontrado = false;

            if (estrutura instanceof Vetor) {
                if (descricao.contains("Ordenado")) {
                    encontrado = buscaBinaria(dados, elemento);
                } else {
                    encontrado = ((Vetor) estrutura).buscaSequencial(elemento);
                }
            } else if (estrutura instanceof ArvoreBinaria) {
                encontrado = ((ArvoreBinaria) estrutura).buscar(elemento);
            } else if (estrutura instanceof ArvoreAVL) {
                encontrado = ((ArvoreAVL) estrutura).buscar(elemento);
            }

            cronometro.parar();
            System.out.printf("Busca por %d: %.3f ms (encontrado: %b)%n",
                    elemento, cronometro.getTempoDecorrido(), encontrado);
        }
    }
}
