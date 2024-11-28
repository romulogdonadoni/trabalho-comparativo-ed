public class Vetor {
    private int[] dados;
    private int tamanho;
    
    public Vetor() {
        this.dados = new int[10];
        this.tamanho = 0;
    }
    
    public void inserir(int valor) {
        if (tamanho == dados.length) {
            redimensionar();
        }
        dados[tamanho++] = valor;
    }
    
    private void redimensionar() {
        int[] novoArray = new int[dados.length * 2];
        System.arraycopy(dados, 0, novoArray, 0, dados.length);
        dados = novoArray;
    }
    
    public boolean buscaSequencial(int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (dados[i] == valor) {
                return true;
            }
        }
        return false;
    }
    
    public boolean buscaBinaria(int valor) {
        ordenarQuickSort();
        return buscaBinariaRecursiva(valor, 0, tamanho - 1);
    }
    
    private boolean buscaBinariaRecursiva(int valor, int inicio, int fim) {
        if (inicio > fim) {
            return false;
        }
        
        int meio = (inicio + fim) / 2;
        if (dados[meio] == valor) {
            return true;
        }
        
        if (dados[meio] > valor) {
            return buscaBinariaRecursiva(valor, inicio, meio - 1);
        } else {
            return buscaBinariaRecursiva(valor, meio + 1, fim);
        }
    }
    
    public void ordenarBubbleSort() {
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (dados[j] > dados[j + 1]) {
                    int temp = dados[j];
                    dados[j] = dados[j + 1];
                    dados[j + 1] = temp;
                }
            }
        }
    }
    
    public void ordenarQuickSort() {
        quickSort(0, tamanho - 1);
    }
    
    private void quickSort(int baixo, int alto) {
        if (baixo < alto) {
            int pi = particionar(baixo, alto);
            quickSort(baixo, pi - 1);
            quickSort(pi + 1, alto);
        }
    }
    
    private int particionar(int baixo, int alto) {
        int pivo = dados[alto];
        int i = (baixo - 1);
        
        for (int j = baixo; j < alto; j++) {
            if (dados[j] <= pivo) {
                i++;
                int temp = dados[i];
                dados[i] = dados[j];
                dados[j] = temp;
            }
        }
        
        int temp = dados[i + 1];
        dados[i + 1] = dados[alto];
        dados[alto] = temp;
        
        return i + 1;
    }
} 