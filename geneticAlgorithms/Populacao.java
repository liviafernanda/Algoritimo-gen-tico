package geneticAlgorithms;

public class Populacao {
	
	int tamanho = 10;
	Individuo[] individuos = new Individuo[10];
	int adaptado = 0;
	
	//inicializar a população
	public void inicializaPopulacao(int tam) {
		for(int i=0; i<individuos.length; i++) {
			individuos[i] = new Individuo();
		}
	}
	
	//pega o mais adaptado
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
	
	//escolhendo o último mais adaptado
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
			individuos[i].calculaFitness();
		}
		getAdaptado();
	}
	
}


