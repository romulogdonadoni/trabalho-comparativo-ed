# Análise de Estruturas de Dados e Algoritmos de Ordenação

## Descrição
Este projeto implementa e compara o desempenho de diferentes estruturas de dados (Vetor, Árvore Binária e Árvore AVL) e algoritmos de ordenação (Bubble Sort e Quick Sort) com diferentes conjuntos de dados.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- `src/`: Arquivos fonte do projeto
  - `App.java`: Classe principal com os testes de desempenho
  - `Vetor.java`: Implementação da estrutura de dados Vetor
  - `ArvoreBinaria.java`: Implementação da Árvore Binária de Busca
  - `ArvoreAVL.java`: Implementação da Árvore AVL
  - `Cronometro.java`: Classe utilitária para medição de tempo

## Funcionalidades

- Comparação de desempenho entre estruturas de dados:
  - Vetor com busca sequencial e binária
  - Árvore Binária de Busca
  - Árvore AVL
- Testes com diferentes tipos de dados:
  - Dados ordenados
  - Dados em ordem inversa
  - Dados aleatórios
- Comparação de algoritmos de ordenação:
  - Bubble Sort
  - Quick Sort


## Resultados dos Testes

O programa executa testes com conjuntos de dados de diferentes tamanhos (100, 1000, 10000) e apresenta:
- Tempo de inserção em cada estrutura
- Tempo de busca para diferentes elementos
- Comparação de desempenho entre Bubble Sort e Quick Sort

## Pré-requisitos

- Java JDK 11 ou superior
- IDE Java (recomendado: VS Code com Extension Pack for Java)

## Compilação e Execução

1. Compile o projeto:
```bash
javac -d bin src/*.java
```

2. Execute o programa:
```bash
java -cp bin App
```

## Desenvolvimento

Para desenvolver usando VS Code:
1. Instale a extensão "Extension Pack for Java"
2. Abra a pasta do projeto
3. Use o botão "Run" ou pressione F5 para executar