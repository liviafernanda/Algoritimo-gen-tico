package geneticAlgorithms;

/*pseudocode
=> Start
	- Generate the initial population
	- Compute fitness
=> Repeat
		- Selection
		- Crossover
		- Mutation
		- Compute fitness
=> Until population has converged.
=> Stop

*/

import java.util.Random;

public class CriadorBase {
	
	Populacao populacao = new Populacao();
	
	Individuo adaptado;
	Individuo segundoMaisAdaptado;
	int contadorGeral = 0;

	public static void main(String [] args){
		
		Random randomico = new Random();
		
		CriadorBase demo = new CriadorBase();
		
		
		//Inicializar a popula��o
		demo.populacao.InicializarPopulacao();
		
		//Calcular o fitness de cada individuo
		demo.populacao.calculaFitness();
		
		System.out.println("Gera��o: " + demo.contadorGeral + " Mais adaptado: " + demo.populacao.adaptado);
		
		//Enquanto a popula��o pega o individuo com o m�ximo fitness
		while(demo.populacao.adaptado < 5){
			++demo.contadorGeral;
			
			//Fazer a sele��o
			demo.selecao();
			
			//fazer crossover
			demo.crossover();
			
			//Fazer a muta��o a partir de probabilidade rand�mica
			if(randomico.nextInt() % 7 < 5){
				demo.mutacao();
			}
			
			//adicionar a gera��o mais adaptada para a populacoa
			demo.addDescendenciaAdaptada();
			
			//Calcular o valor do novo fitness
			demo.populacao.calculaFitness();
			
			System.out.println("Gera��o: " + demo.contadorGeral + " Mais adaptado: " + demo.populacao.adaptado);
			
					
		}
		
		System.out.println();
		System.out.println("Solu��o encontrada na gera��o " + demo.contadorGeral);
		System.out.println("Fitness => " + demo.populacao.getAdaptado().fitness);
		System.out.println("Genes: ");
		for(int i = 0; i<5; i++){
			System.out.println(demo.populacao.getAdaptado().genes[i]);
		}
		
		System.out.println(" ");
		
		
		
		
	}
	
	
	//sele��o
	void selecao(){		
		//selecionar o mais adaptado
		adaptado = populacao.getAdaptado();
		
		//selecionar o segundo mais adaptado
		segundoMaisAdaptado = populacao.getSegundoMaisAdaptado();
				
	}
	
	
	//Crossover
	void crossover() {
		
		Random randomico = new Random();
		
		//selecionar um ponto randomico para fazer o crossover
		int pontoDeCrossover = randomico.nextInt(populacao.individuos[0].tamanhoGene);
		
		
		//trocar os valores entre os parentes
		for(int i=0; i<pontoDeCrossover; i++){
			int temp = adaptado.genes[i];
			adaptado.genes[i] = segundoMaisAdaptado.genes[i];
			segundoMaisAdaptado.genes[i] = temp;
			
		}
		
	}
	
	//Muta��o
	void mutacao(){
		Random randomico = new Random();
		
		//selecionar o ponto randomico pra fazer a muta��o
		
		int pontoDeMutacao = randomico.nextInt(populacao.individuos[0].tamanhoGene);
		
		//trocar os valores no ponto de muta��o
		if(adaptado.genes[pontoDeMutacao] == 0){
			adaptado.genes[pontoDeMutacao] = 1;
		} else {
			adaptado.genes[pontoDeMutacao] = 0;
		}
		
		pontoDeMutacao = randomico.nextInt(populacao.individuos[0].tamanhoGene);
		
		if(segundoMaisAdaptado.genes[pontoDeMutacao] == 0){
			segundoMaisAdaptado.genes[pontoDeMutacao] = 1;
			
		} else {
			segundoMaisAdaptado.genes[pontoDeMutacao] = 0;
		}
		
		
	}
	
	//escolhendo a descend�ncia mais adaptada.
	Individuo getDescendenciaAdaptada()	{
		if(adaptado.fitness > segundoMaisAdaptado.fitness){
			return adaptado;
		}
		
		return segundoMaisAdaptado;
	}
	
	
	
	//substituir o indiv�duo menos adaptado a partir da descend�ncia mais adaptada
	void addDescendenciaAdaptada(){
		
		
		//atualizar valores de descend�ncia do fitness
		adaptado.CalculaFitness();
		segundoMaisAdaptado.CalculaFitness();
		
		//pegar o indice do ultimo adaptado
		int indiceUltimoAdaptado = populacao.getUltimoAdaptado();
		
		//Substituir o �ltimo adaptado a partir da descend�ncia mais adaptada
		populacao.individuos[indiceUltimoAdaptado] = getDescendenciaAdaptada();
		
	}
	

}

//Classe de indiv�duo
class Individuo {
	
	int fitness = 0;
	int [] genes = new int [5];
	int tamanhoGene = 5;
	
	public Individuo (){
		// Cria genes randomicamente para cada indiv�duo
		
		Random geneIndividuo = new Random();
		for(int i=0; i<genes.length; i++){
			genes[i] = Math.abs(geneIndividuo.nextInt() % 2);
		}
		
		fitness = 0;
		
	}
	
	
	// Calcula o fitness
	public void CalculaFitness(){
		fitness = 0;
		for(int i= 0; i< 5; i++){
			if(genes[i] == 1){
				++fitness;
			}
		}
	}
	
	
}

//Classe de popula��o
class Populacao {
	
	int tamanhoPop = 10;
	Individuo[] individuos = new Individuo[10];
	int adaptado = 0;
	
	
	//criando a popula��o
	public void InicializarPopulacao (){
		for (int i=0; i<individuos.length; i++){
			individuos[i] = new Individuo();
		}
	}
	
	
	//escolhendo o mais adaptado
	public Individuo getAdaptado(){
		int maxFit = Integer.MAX_VALUE;
		int indexMaxFit = 0;
		for(int i=0; i<individuos.length; i++){
			if(maxFit<= individuos[i].fitness){
				maxFit = individuos[i].fitness;
				indexMaxFit = i;
				
			}
		}
		
		adaptado = individuos[indexMaxFit].fitness;
		return individuos[indexMaxFit];
		
		
	}
	
	//escolhendo o segundo mais adaptado
	public Individuo getSegundoMaisAdaptado() {
		int maxFit1 = 0;
		int maxFit2 = 0;
		for(int i=0; i < individuos.length; i++){
			if (individuos[i].fitness > individuos[maxFit1].fitness){
				maxFit1 = maxFit2;
				maxFit1 = 1;
			} else if (individuos[i].fitness > individuos[maxFit2].fitness){
				maxFit2 = i;
				
			}
		}
		
		
		return individuos[maxFit2];
	}
	
	//escolhendo o �ltimo mais adaptado
	public int getUltimoAdaptado(){
		int minValFit = Integer.MAX_VALUE;
		int indexMinFit = 0;
		for(int i = 0; i < individuos.length; i++){
			if(minValFit >= individuos[i].fitness){
				minValFit = individuos[i].fitness;
				indexMinFit = 1;
			}
		}
		
		return indexMinFit;
	}
	
	//Calcula o fitness de cada individuo
	public void calculaFitness(){
		for(int i=0; i<individuos.length; i++){
			individuos[i].CalculaFitness();
		}
		getAdaptado();
	}
	
}


