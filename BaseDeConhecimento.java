import java.sql.SQLOutput;

public class BaseDeConhecimento {

	int mundo[][]; // mundo Wumpus conhecido (0: desconhecido; 1: seguro; -1: não seguro);
	boolean caminho[][];

	public BaseDeConhecimento() {
		int i, j, k;
		mundo = new int[4][4];
		caminho = new boolean[4][4];
		//inicialização do mundo inicial conhecido
		for (i = 0; i < 4; i++)
			for (j = 0; j < 4; j++) {
				mundo[i][j] = 0;
				caminho[i][j] = false;
			}
		mundo[0][0] = 1;
		caminho[0][0] = true;
	}

	public int[] ask(boolean percep[], int posicaoLinha, int posicaoColuna ,MundoWumpus mw) {
		int posicao[] = new int[2];
		boolean brisa = percep[0];
		boolean fedor = percep[1];
		boolean brilho = percep[2];
		boolean poco = percep[3];
		boolean Wumpus = percep[4];
		boolean tesouro = percep[5];
		int maximo = 3;

		if ((posicaoLinha == 0) && (posicaoColuna == 0) && !tesouro) { //sala (0,0)
			mundo[0][1] = 1;
			mundo[1][0] = 1;
			if (!caminho[posicaoLinha][posicaoColuna+1]) {
				posicao[0] = posicaoLinha;
				posicao[1] = posicaoColuna+1;
			}else {
				posicao[0] = posicaoLinha+1;
				posicao[1] = posicaoColuna;
			}
		}else {
			if (!brisa && !fedor  ) { //B[1,1] dupla (P[1,2]ouP[2,1]); se um é falso, o outro é falso
				if(tesouro){

					mw.setPercepSala(posicaoLinha,posicaoColuna,5,false);// tesouro
					if(caminho[posicaoLinha][posicaoColuna+1]){// direita
						posicao[0] = posicaoLinha;
						posicao[1] = posicaoColuna+1;
						mw.setPercepSala(posicaoLinha,posicaoColuna+1,5,true);// tesouro
						System.out.println("direita");
						return posicao;
					}
					if(caminho[posicaoLinha-1][posicaoColuna] ){// baixo
						posicao[0] = posicaoLinha-1;
						posicao[1] = posicaoColuna;
						mw.setPercepSala(posicaoLinha-1,posicaoColuna,5,true);// tesouro
						System.out.println("baixo");
						return posicao;
					}
					if(caminho[posicaoLinha][posicaoColuna-1]){// esquerda
						posicao[0] = posicaoLinha;
						posicao[1] = posicaoColuna-1;
						mw.setPercepSala(posicaoLinha,posicaoColuna-1,5,true);// tesouro
						System.out.println("esquerda");
						return  posicao;
					}
					if(caminho[posicaoLinha + 1][posicaoColuna]){// cima
						posicao[0] = posicaoLinha+1;
						posicao[1] = posicaoColuna;
						mw.setPercepSala(posicaoLinha+1,posicaoColuna,5,true);// tesouro
						System.out.println("cima");
						return  posicao;
					}

				}
				if( posicaoLinha == 0  && posicaoColuna == maximo ){//2
					mundo[posicaoLinha  + 1][posicaoColuna] = 1;
					mundo[posicaoLinha][posicaoColuna-1] = 1;
					System.out.println("2");

				}
				if(posicaoLinha == maximo && posicaoColuna == maximo ){//3
					mundo[posicaoLinha][posicaoColuna +1] = 1;
					mundo[posicaoLinha = -1][posicaoColuna] = 1;
					System.out.println("3");
				}
				if(posicaoLinha == 0 && posicaoColuna == maximo){//4
					mundo[posicaoLinha -1][posicaoColuna] = 1;
					mundo[posicaoLinha][posicaoColuna - 1] = 1;
					System.out.println("4");
				}
				if((posicaoLinha == 0) && (posicaoColuna > 0 && posicaoColuna < maximo)){//5
					mundo[posicaoLinha + 1][posicaoColuna] = 1;
					mundo[posicaoLinha][posicaoColuna - 1] = 1;
					mundo[posicaoLinha][posicaoColuna + 1] = 1;
					System.out.println("5");
				}
				if(posicaoLinha == maximo && posicaoColuna > 0 && posicaoColuna < maximo ){//6
					mundo[posicaoLinha - 1][posicaoColuna] = 1;
					mundo[posicaoLinha][posicaoColuna - 1] = 1;
					mundo[posicaoLinha][posicaoColuna + 1] = 1;
					System.out.println("6");

				}
				if((posicaoLinha > 0 && posicaoLinha < maximo) && (posicaoColuna >0  && posicaoLinha < maximo && !tesouro)){//7
					mundo[posicaoLinha + 1][posicaoColuna] = 1;
					mundo[posicaoLinha - 1][posicaoColuna] = 1;
					mundo[posicaoLinha][posicaoColuna - 1] = 1;
					mundo[posicaoLinha][posicaoColuna + 1] = 1;
					if (!caminho[posicaoLinha+1][posicaoColuna] && mundo[posicaoLinha+1][posicaoColuna]==1) {//CIMA
						posicao[0] = posicaoLinha + 1;
						posicao[1] = posicaoColuna;

					}else if(!caminho[posicaoLinha][posicaoColuna+1] && mundo[posicaoLinha][posicaoColuna+1]==1 ){//direita
						posicao[0] = posicaoLinha;
						posicao[1] = posicaoColuna  + 1;

					}else if (!caminho[posicaoLinha+1][posicaoColuna] && mundo[posicaoLinha-1][posicaoColuna]==1) {//CIMA
						posicao[0] = posicaoLinha - 1;
						posicao[1] = posicaoColuna;
					}else{
						posicao[0] = posicaoLinha-1;
						posicao[1] = posicaoColuna;

					}
				}

				if((posicaoLinha > 0 && posicaoLinha < maximo) && (posicaoColuna == 0)){//8
					mundo[posicaoLinha + 1][posicaoColuna] = 1;
					mundo[posicaoLinha-1][posicaoColuna] = 1;
					mundo[posicaoLinha][posicaoColuna + 1] = 1;
					System.out.println("8");
				}

				if((posicaoLinha > 0 && posicaoLinha < maximo) && (posicaoColuna == maximo)){//9
					mundo[posicaoLinha + 1][posicaoColuna] = 1;
					mundo[posicaoLinha-1][posicaoColuna] = 1;
					mundo[posicaoLinha][posicaoColuna - 1] = 1;
					System.out.println("9");
				}


			} else {// SE TIVER BRISA OU FEDOR
				if( posicaoLinha == 0  && posicaoColuna == maximo ){//2

					System.out.println("perigo2");

					if(brisa  && !tesouro){
						boolean[] salaCima = mw.getPercepcoesSala(posicaoLinha + 1, posicaoColuna);
						// cima
						if(mundo[posicaoLinha+1][posicaoColuna]==0){

							mw.setPercepSala(posicaoLinha + 1, posicaoColuna, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha+1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha+1][posicaoColuna]==-1 && salaCima[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha+1][posicaoColuna] = 1;
						}

						boolean[] salaEsquerda = mw.getPercepcoesSala(posicaoLinha, posicaoColuna-1);
						//esquerda
						if(mundo[posicaoLinha][posicaoColuna-1]==0) {

							mw.setPercepSala(posicaoLinha, posicaoColuna-1, 3, true);// posicao adjacente = um valor
							mundo[posicaoLinha][posicaoColuna-1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna-1]==-1 && salaEsquerda[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna-1] = 1;
						}


					}

					if (fedor  && !tesouro){
						boolean[] salaCima = mw.getPercepcoesSala(posicaoLinha + 1, posicaoColuna);
						// cima
						if(mundo[posicaoLinha+1][posicaoColuna]==0){
							mw.setPercepSala(posicaoLinha + 1, posicaoColuna, 4,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha+1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha+1][posicaoColuna]==-1 && salaCima[3] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha+1][posicaoColuna] = 1;
						}
						boolean[] salaEsquerda = mw.getPercepcoesSala(posicaoLinha, posicaoColuna-1);
						//esquerda
						if(mundo[posicaoLinha][posicaoColuna-1]==0) {
							mw.setPercepSala(posicaoLinha, posicaoColuna-1, 4, true);// posicao adjacente = um valor
							mundo[posicaoLinha][posicaoColuna-1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna-1]==-1 && salaEsquerda[3] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna-1] = 1;
						}

					}
				}
				if(posicaoLinha == maximo && posicaoColuna == maximo ){//3

					if(brisa  && !tesouro){
						boolean[] salaEsquerda = mw.getPercepcoesSala(posicaoLinha, posicaoColuna-1);
						// esquerda
						if(mundo[posicaoLinha][posicaoColuna-1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna-1, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha][posicaoColuna-1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna-1]==-1 && salaEsquerda[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna-1] = 1;
						}


						boolean[] salaBaixo = mw.getPercepcoesSala(posicaoLinha - 1, posicaoColuna);
						//baixo
						if(mundo[posicaoLinha-1][posicaoColuna]==0) {
							mw.setPercepSala(posicaoLinha-1, posicaoColuna, 3, true);// posicao adjacente = um valor
							mundo[posicaoLinha-1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha-1][posicaoColuna]==-1 && salaBaixo[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha-1][posicaoColuna] = 1;
						}
					}

					if (fedor && !tesouro){
						boolean[] salaEsquerda = mw.getPercepcoesSala(posicaoLinha, posicaoColuna-1);

						// esquerda
						if(mundo[posicaoLinha][posicaoColuna-1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna-1, 4,true) ;// posicao adjacente possivel monstro
							mundo[posicaoLinha][posicaoColuna-1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna-1]==-1 && salaEsquerda[3] == true){// falso perigoso pois se estou no fedor n tem poco
							mundo[posicaoLinha][posicaoColuna-1] = 1;
						}

						boolean[] salaBaixo = mw.getPercepcoesSala(posicaoLinha - 1, posicaoColuna);
						//baixo
						if(mundo[posicaoLinha-1][posicaoColuna]==0) {
							mw.setPercepSala(posicaoLinha-1, posicaoColuna, 4, true);// posicao adjacente possivel monstro
							mundo[posicaoLinha-1][posicaoColuna] = -1;// sala como perigosa

						}else if(mundo[posicaoLinha][posicaoColuna-1]==-1 && salaBaixo[3] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna-1] = 1;
						}
					}
				}
				if(posicaoLinha == 0 && posicaoColuna == maximo){//4

					if(brisa && !tesouro){
						boolean[] salaDireita = mw.getPercepcoesSala(posicaoLinha, posicaoColuna+1);
						// direita
						if(mundo[posicaoLinha][posicaoColuna+1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna+1, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha][posicaoColuna+1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna+1]==-1 && salaDireita[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna+1] = 1;
						}


						boolean[] salaBaixo = mw.getPercepcoesSala(posicaoLinha - 1, posicaoColuna);
						//baixo
						if(mundo[posicaoLinha-1][posicaoColuna]==0) {
							mw.setPercepSala(posicaoLinha-1, posicaoColuna, 3, true);// posicao adjacente = um valor
							mundo[posicaoLinha-1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha-1][posicaoColuna]==-1 && salaBaixo[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha-1][posicaoColuna] = 1;
						}
					}

					if (fedor && !tesouro){
						// direita
						boolean[] salaDireita = mw.getPercepcoesSala(posicaoLinha , posicaoColuna+1);

						if(mundo[posicaoLinha][posicaoColuna+1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna+1, 4,true) ;// posicao adjacente possivel monstro
							mundo[posicaoLinha][posicaoColuna+1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna+1]==-1 && salaDireita[3] == true){// falso perigoso pois se estou no fedor n tem poco
							mundo[posicaoLinha][posicaoColuna+1] = 1;
						}

						boolean[] salaBaixo = mw.getPercepcoesSala(posicaoLinha - 1, posicaoColuna);
						//baixo
						if(mundo[posicaoLinha-1][posicaoColuna]==0) {
							mw.setPercepSala(posicaoLinha-1, posicaoColuna, 4, true);// posicao adjacente possivel monstro
							mundo[posicaoLinha-1][posicaoColuna] = -1;// sala como perigosa

						}else if(mundo[posicaoLinha-1][posicaoColuna]==-1 && salaBaixo[3] == true){// falso perigoso pois se estou na fedor n tem poco
							mundo[posicaoLinha-1][posicaoColuna] = 1;
						}
					}
				}
				if((posicaoLinha == 0) && (posicaoColuna > 0 && posicaoColuna < maximo)){//5

					if(brisa && !tesouro){

						boolean[] salaCima = mw.getPercepcoesSala(posicaoLinha + 1, posicaoColuna);
						// cima
						if(mundo[posicaoLinha+1][posicaoColuna]==0){

							mw.setPercepSala(posicaoLinha + 1, posicaoColuna, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha+1][posicaoColuna] = -1;



						}else if(mundo[posicaoLinha+1][posicaoColuna]==-1 && salaCima[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha+1][posicaoColuna] = 1;
						}

						boolean[] salaDireita = mw.getPercepcoesSala(posicaoLinha, posicaoColuna+1);
						// direita
						if(mundo[posicaoLinha][posicaoColuna+1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna+1, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha][posicaoColuna+1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna+1]==-1 && salaDireita[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna+1] = 1;
						}

						// esquerda
						if(mundo[posicaoLinha][posicaoColuna-1]!=0){
							boolean[] salaEsquerda = mw.getPercepcoesSala(posicaoLinha, posicaoColuna-1);

							if(mundo[posicaoLinha][posicaoColuna-1]==0){
								mw.setPercepSala(posicaoLinha, posicaoColuna-1, 3,true) ;// posicao adjacente possivel poço
								mundo[posicaoLinha][posicaoColuna-1] = -1;

							}else if(mundo[posicaoLinha][posicaoColuna-1]==-1 && salaEsquerda[4] == true){// falso perigoso pois se estou na brisa n tem monstro
								mundo[posicaoLinha][posicaoColuna-1] = 1;
							}
						}

						System.out.println(mundo[posicaoLinha+1][posicaoColuna]);// cima
						System.out.println(mundo[posicaoLinha][posicaoColuna+1]);// direita
						System.out.println(mundo[posicaoLinha][posicaoColuna-1]); // esquerda

					}

					if (fedor  && !tesouro){

						boolean[] salaCima = mw.getPercepcoesSala(posicaoLinha + 1, posicaoColuna);
						// cima
						if(mundo[posicaoLinha+1][posicaoColuna]==0){
							mw.setPercepSala(posicaoLinha + 1, posicaoColuna, 4,true) ;// posicao adjacente possivel monstro
							mundo[posicaoLinha+1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha+1][posicaoColuna]==-1 && salaCima[3] == true){// falso perigoso pois se estou no fedor n tem poço
							mundo[posicaoLinha+1][posicaoColuna] = 1;
						}

						// direita
						boolean[] salaDireita = mw.getPercepcoesSala(posicaoLinha , posicaoColuna+1);

						if(mundo[posicaoLinha][posicaoColuna+1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna+1, 4,true) ;// posicao adjacente possivel monstro
							mundo[posicaoLinha][posicaoColuna+1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna+1]==-1 && salaDireita[3] == true){// falso perigoso pois se estou no fedor n tem poco
							mundo[posicaoLinha][posicaoColuna+1] = 1;
						}

						boolean[] salaEsquerda = mw.getPercepcoesSala(posicaoLinha, posicaoColuna-1);

						// esquerda
						if(mundo[posicaoLinha][posicaoColuna-1]!=0) {
							if (mundo[posicaoLinha][posicaoColuna - 1] == 0) {
								mw.setPercepSala(posicaoLinha, posicaoColuna - 1, 4, true);// posicao adjacente possivel monstro
								mundo[posicaoLinha][posicaoColuna - 1] = -1;

							} else if (mundo[posicaoLinha][posicaoColuna - 1] == -1 && salaEsquerda[3] == true) {// falso perigoso pois se estou no fedor n tem poco
								mundo[posicaoLinha][posicaoColuna - 1] = 1;
							}
						}
					}
					if(tesouro){
						System.out.println("rico");
						mw.setPercepSala(posicaoLinha,posicaoColuna,5,false);// tesouro


						if(caminho[posicaoLinha][posicaoColuna+1]){// direita
							posicao[0] = posicaoLinha;
							posicao[1] = posicaoColuna+1;
							mw.setPercepSala(posicaoLinha,posicaoColuna+1,5,true);// tesouro
							System.out.println("direita");
							return posicao;
						}

						if(caminho[posicaoLinha][posicaoColuna-1]){// esquerda
							posicao[0] = posicaoLinha;
							posicao[1] = posicaoColuna-1;
							mw.setPercepSala(posicaoLinha,posicaoColuna-1,5,true);// tesouro
							System.out.println("esquerda");
							return  posicao;
						}





						if(caminho[posicaoLinha + 1][posicaoColuna]){// cima
							posicao[0] = posicaoLinha+1;
							posicao[1] = posicaoColuna;
							mw.setPercepSala(posicaoLinha+1,posicaoColuna,5,true);// tesouro
							System.out.println("cima");
							return  posicao;
						}

					}
					// ORDEM DE PRECEDENCIA  - direta esquerda cima
					if(!caminho[posicaoLinha][posicaoColuna+1] && mundo[posicaoLinha][posicaoColuna+1]==1){
						posicao[0] = posicaoLinha;
						posicao[1] = posicaoColuna  + 1;
					}else if (!caminho[posicaoLinha+1][posicaoColuna] && mundo[posicaoLinha+1][posicaoColuna]==1) {//CIMA
						posicao[0] = posicaoLinha + 1;
						posicao[1] = posicaoColuna;
					}else{
						posicao[0] = posicaoLinha;
						posicao[1] = posicaoColuna-1;
					}
				}
				if(posicaoLinha == maximo && posicaoColuna > 0 && posicaoColuna < maximo ){//6


					if(mundo[posicaoLinha - 1][posicaoColuna] == -1){
						mundo[posicaoLinha - 1][posicaoColuna] = 1;
					}else{
						mundo[posicaoLinha - 1][posicaoColuna] = -1;
					}

					if(mundo[posicaoLinha][posicaoColuna - 1] == - 1){
						mundo[posicaoLinha][posicaoColuna - 1] = 1;
					}else{
						mundo[posicaoLinha][posicaoColuna - 1] = -1;
					}





					if(brisa){

						System.out.println("perigo6 brisa");
					}

					if (fedor){
						System.out.println("perigo6 fedor");
					}
				}
				if((posicaoLinha > 0 && posicaoLinha < maximo) && (posicaoColuna >0  && posicaoLinha < maximo)){//7



					if(brisa && !tesouro){
						boolean[] salaCima = mw.getPercepcoesSala(posicaoLinha + 1, posicaoColuna);
						// cima
						if(mundo[posicaoLinha+1][posicaoColuna]==0){

							mw.setPercepSala(posicaoLinha + 1, posicaoColuna, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha+1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha+1][posicaoColuna]==-1 && salaCima[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha+1][posicaoColuna] = 1;
						}

						boolean[] salaDireita = mw.getPercepcoesSala(posicaoLinha, posicaoColuna+1);
						// direita
						if(mundo[posicaoLinha][posicaoColuna+1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna+1, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha][posicaoColuna+1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna+1]==-1 && salaDireita[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna+1] = 1;
						}

						boolean[] salaBaixo = mw.getPercepcoesSala(posicaoLinha - 1, posicaoColuna);
						//baixo

						if (mundo[posicaoLinha - 1][posicaoColuna] == 0) {
							mw.setPercepSala(posicaoLinha - 1, posicaoColuna, 3, true);// posicao adjacente = um valor
							mundo[posicaoLinha - 1][posicaoColuna] = -1;

						} else if (mundo[posicaoLinha - 1][posicaoColuna] == -1 && salaBaixo[4] == true) {// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha - 1][posicaoColuna] = 1;
						}

						boolean[] salaEsquerda = mw.getPercepcoesSala(posicaoLinha, posicaoColuna-1);

						if(mundo[posicaoLinha][posicaoColuna-1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna-1, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha][posicaoColuna-1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna-1]==-1 && salaEsquerda[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna-1] = 1;
						}

					}

					if (fedor && !tesouro){
						boolean[] salaCima = mw.getPercepcoesSala(posicaoLinha + 1, posicaoColuna);
						// cima
						if(mundo[posicaoLinha+1][posicaoColuna]==0){
							mw.setPercepSala(posicaoLinha + 1, posicaoColuna, 4,true) ;// posicao adjacente possivel monstro
							mundo[posicaoLinha+1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha+1][posicaoColuna]==-1 && salaCima[3] == true){// falso perigoso pois se estou no fedor n tem poço
							mundo[posicaoLinha+1][posicaoColuna] = 1;
						}

						// direita
						boolean[] salaDireita = mw.getPercepcoesSala(posicaoLinha , posicaoColuna+1);

						if(mundo[posicaoLinha][posicaoColuna+1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna+1, 4,true) ;// posicao adjacente possivel monstro
							mundo[posicaoLinha][posicaoColuna+1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna+1]==-1 && salaDireita[3] == true){// falso perigoso pois se estou no fedor n tem poco
							salaDireita[3] = false;
							mundo[posicaoLinha][posicaoColuna+1] = 1;
						}

						// esquerda
						boolean[] salaEsquerda = mw.getPercepcoesSala(posicaoLinha, posicaoColuna-1);

						if (mundo[posicaoLinha][posicaoColuna - 1] == 0) {
							mw.setPercepSala(posicaoLinha, posicaoColuna - 1, 4, true);// posicao adjacente possivel monstro
							mundo[posicaoLinha][posicaoColuna - 1] = -1;

						} else if (mundo[posicaoLinha][posicaoColuna - 1] == -1 && salaEsquerda[3] == true) {// falso perigoso pois se estou no fedor n tem poco
							mundo[posicaoLinha][posicaoColuna - 1] = 1;
						}


						// baixo

						boolean[] salaBaixo = mw.getPercepcoesSala(posicaoLinha - 1, posicaoColuna);

						if (mundo[posicaoLinha - 1][posicaoColuna] == 0) {
							mw.setPercepSala(posicaoLinha - 1, posicaoColuna, 4, true);// posicao adjacente possivel monstro
							mundo[posicaoLinha - 1][posicaoColuna] = -1;// sala como perigosa

						} else if (mundo[posicaoLinha - 1][posicaoColuna] == -1 && salaBaixo[3] == true) {// falso perigoso pois se estou na fedor n tem poco
							mundo[posicaoLinha - 1][posicaoColuna] = 1;
						}


					}


					if(tesouro){
						System.out.println("rico");
						mw.setPercepSala(posicaoLinha,posicaoColuna,5,false);// tesouro

						if(caminho[posicaoLinha + 1][posicaoColuna]){// cima
							posicao[0] = posicaoLinha+1;
							posicao[1] = posicaoColuna;
							mw.setPercepSala(posicaoLinha+1,posicaoColuna,5,true);// tesouro
							System.out.println("cima");
							return  posicao;
						}
						if(caminho[posicaoLinha][posicaoColuna+1]){// direita
							posicao[0] = posicaoLinha;
							posicao[1] = posicaoColuna+1;
							mw.setPercepSala(posicaoLinha,posicaoColuna+1,5,true);// tesouro
							System.out.println("direita");
							return posicao;
						}
						if(caminho[posicaoLinha-1][posicaoColuna] ){// baixo
							posicao[0] = posicaoLinha-1;
							posicao[1] = posicaoColuna;
							mw.setPercepSala(posicaoLinha-1,posicaoColuna,5,true);// tesouro
							System.out.println("baixo");
							return posicao;
						}
						if(caminho[posicaoLinha][posicaoColuna-1]){// esquerda
							posicao[0] = posicaoLinha;
							posicao[1] = posicaoColuna-1;
							mw.setPercepSala(posicaoLinha,posicaoColuna-1,5,true);// tesouro
							System.out.println("esquerda");
							return  posicao;
						}

					}
					// ORDEM DE PRECEDENCIA  - direta esquerda cima
					if(!caminho[posicaoLinha][posicaoColuna+1] && mundo[posicaoLinha][posicaoColuna+1]==1){
						posicao[0] = posicaoLinha;
						posicao[1] = posicaoColuna  + 1;
						System.out.println("DIREITA");
					}else if (!caminho[posicaoLinha+1][posicaoColuna] && mundo[posicaoLinha+1][posicaoColuna]==1) {//CIMA
						posicao[0] = posicaoLinha + 1;
						posicao[1] = posicaoColuna;
						System.out.println("CIMA");
					}else{
						posicao[0] = posicaoLinha;
						posicao[1] = posicaoColuna-1;
						System.out.println("ESQUERDA");
					}
					System.out.println(mundo[posicaoLinha+1][posicaoColuna]);// cima
					System.out.println(mundo[posicaoLinha][posicaoColuna+1]);// direita
					System.out.println(mundo[posicaoLinha][posicaoColuna-1]);// esquerda
					System.out.println(mundo[posicaoLinha-1][posicaoColuna]);// baixo



				}
				if((posicaoLinha > 0 && posicaoLinha < maximo) && (posicaoColuna == 0)){//8

					if(brisa){

						boolean[] salaCima = mw.getPercepcoesSala(posicaoLinha + 1, posicaoColuna);
						// cima
						if(mundo[posicaoLinha+1][posicaoColuna]==0){

							mw.setPercepSala(posicaoLinha + 1, posicaoColuna, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha+1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha+1][posicaoColuna]==-1 && salaCima[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha+1][posicaoColuna] = 1;
						}

						boolean[] salaDireita = mw.getPercepcoesSala(posicaoLinha, posicaoColuna+1);
						// direita
						if(mundo[posicaoLinha][posicaoColuna+1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna+1, 3,true) ;// posicao adjacente possivel poço
							mundo[posicaoLinha][posicaoColuna+1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna+1]==-1 && salaDireita[4] == true){// falso perigoso pois se estou na brisa n tem monstro
							mundo[posicaoLinha][posicaoColuna+1] = 1;
						}

						boolean[] salaBaixo = mw.getPercepcoesSala(posicaoLinha - 1, posicaoColuna);
						//baixo
						if(mundo[posicaoLinha-1][posicaoColuna]!=0) {
							if (mundo[posicaoLinha - 1][posicaoColuna] == 0) {
								mw.setPercepSala(posicaoLinha - 1, posicaoColuna, 3, true);// posicao adjacente = um valor
								mundo[posicaoLinha - 1][posicaoColuna] = -1;

							} else if (mundo[posicaoLinha - 1][posicaoColuna] == -1 && salaBaixo[4] == true) {// falso perigoso pois se estou na brisa n tem monstro
								mundo[posicaoLinha - 1][posicaoColuna] = 1;
							}
						}


					}


					if (fedor){
						boolean[] salaCima = mw.getPercepcoesSala(posicaoLinha + 1, posicaoColuna);
						// cima
						if(mundo[posicaoLinha+1][posicaoColuna]==0){
							mw.setPercepSala(posicaoLinha + 1, posicaoColuna, 4,true) ;// posicao adjacente possivel monstro
							mundo[posicaoLinha+1][posicaoColuna] = -1;

						}else if(mundo[posicaoLinha+1][posicaoColuna]==-1 && salaCima[3] == true){// falso perigoso pois se estou no fedor n tem poço
							mundo[posicaoLinha+1][posicaoColuna] = 1;
						}

						// direita
						boolean[] salaDireita = mw.getPercepcoesSala(posicaoLinha , posicaoColuna+1);

						if(mundo[posicaoLinha][posicaoColuna+1]==0){
							mw.setPercepSala(posicaoLinha, posicaoColuna+1, 4,true) ;// posicao adjacente possivel monstro
							mundo[posicaoLinha][posicaoColuna+1] = -1;

						}else if(mundo[posicaoLinha][posicaoColuna+1]==-1 && salaDireita[3] == true){// falso perigoso pois se estou no fedor n tem poco
							salaDireita[3] = false;
							mundo[posicaoLinha][posicaoColuna+1] = 1;
						}


						// baixo
						if(mundo[posicaoLinha-1][posicaoColuna]!=0) {
							boolean[] salaBaixo = mw.getPercepcoesSala(posicaoLinha - 1, posicaoColuna);

							if (mundo[posicaoLinha - 1][posicaoColuna] == 0) {
								mw.setPercepSala(posicaoLinha - 1, posicaoColuna, 4, true);// posicao adjacente possivel monstro
								mundo[posicaoLinha - 1][posicaoColuna] = -1;// sala como perigosa

							} else if (mundo[posicaoLinha - 1][posicaoColuna] == -1 && salaBaixo[3] == true) {// falso perigoso pois se estou na fedor n tem poco
								mundo[posicaoLinha - 1][posicaoColuna] = 1;
							}
						}
						System.out.println(mundo[posicaoLinha+1][posicaoColuna]);// cima
						System.out.println(mundo[posicaoLinha][posicaoColuna+1]);// direita
						System.out.println(mundo[posicaoLinha-1][posicaoColuna]);


					}

					// ORDEM DE PRECEDENCIA  - direta esquerda cima
					if(!caminho[posicaoLinha][posicaoColuna+1] && mundo[posicaoLinha][posicaoColuna+1]==1 ){//direita
						posicao[0] = posicaoLinha;
						posicao[1] = posicaoColuna  + 1;
					}else if (!caminho[posicaoLinha+1][posicaoColuna] && mundo[posicaoLinha+1][posicaoColuna]==1) {//CIMA
						posicao[0] = posicaoLinha + 1;
						posicao[1] = posicaoColuna;
					}else{
						posicao[0] = posicaoLinha-1;
						posicao[1] = posicaoColuna;
						System.out.println("oi");
					}

				}
				if((posicaoLinha > 0 && posicaoLinha < maximo) && (posicaoColuna == maximo)){//9
					mundo[posicaoLinha + 1][posicaoColuna] = -1;
					mundo[posicaoLinha-1][posicaoColuna] = -1;
					mundo[posicaoLinha][posicaoColuna - 1] = -1;
					if(brisa){
						percep[3] = true;
						System.out.println("perigo9 brisa");
					}

					if (fedor){
						percep[1] = true;
						System.out.println("perigo9 fedor");
					}
				}
			}
		}
		return posicao;
	}
	public void tell ( int i, int j){
		caminho[i][j] = true;  //marca a sala como caminho percorrido
	}
}