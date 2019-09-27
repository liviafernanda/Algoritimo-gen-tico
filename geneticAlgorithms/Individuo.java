package geneticAlgorithms;

import java.util.Random;

public class Individuo {
	
	int fitness = 0;
	int[] genes = new int[5];
	int tamanhoGene = 5;
	
	public Individuo() {
		
		Random randomico = new Random();
		
		//atribuir genes randomicamente para cada indivíduo.
		for(int i=0; i<genes.length; i++) {
			genes[i] = randomico.nextInt(2);
			
		}
		fitness = 0;
		
	}
	
	//calcular a fitness function
	public void calculaFitness() {
		fitness = 0;
		for(int i=0; i<tamanhoGene; i++) {
			if(genes[i] == 1) {
				++fitness;
			}			
		}
	}
	

}
