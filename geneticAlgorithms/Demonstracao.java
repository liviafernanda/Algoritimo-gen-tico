package geneticAlgorithms;

import java.util.Random;

public class Demonstracao {
	
		Populacao populacao = new Populacao();
		
		Individuo adaptado;
		Individuo segundoMaisAdaptado;
		int contadorGeral = 0;

		public static void main(String [] args){
			
			Random randomico = new Random();
			Demonstracao demo = new Demonstracao();
			
			//Inicializar a popula��o
			demo.populacao.inicializaPopulacao(10);
			
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
				
				System.out.print("Gera��o: " + demo.contadorGeral + " Mais adaptado: " + demo.populacao.adaptado);
				System.out.print(" - Genes: ");
				for(int i = 0; i<5; i++){
					System.out.print(demo.populacao.getAdaptado().genes[i] + " - ");
				}
				
				System.out.println(" ");
				
						
			}
			
			System.out.println();
			System.out.println("Solu��o encontrada na gera��o " + demo.contadorGeral);
			System.out.println("Fitness => " + demo.populacao.getAdaptado().fitness);
			System.out.println("Genes: ");
			for(int i = 0; i<5; i++){
				System.out.print(demo.populacao.getAdaptado().genes[i] + " - ");
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
			adaptado.calculaFitness();
			segundoMaisAdaptado.calculaFitness();
			
			//pegar o indice do ultimo adaptado
			int indiceUltimoAdaptado = populacao.getUltimoAdaptado();
			
			//Substituir o �ltimo adaptado a partir da descend�ncia mais adaptada
			populacao.individuos[indiceUltimoAdaptado] = getDescendenciaAdaptada();
			
		}
		

		
	

}
