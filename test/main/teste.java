package main;

import Robo.AppRobo;


public class teste {

    public static void main(String[] args) {
        AppRobo robo = new AppRobo("Teste");
        robo.definirParametros();
        robo.executar(
                rock_importacao.Rock_Importacao.principal(12, 2019)
        );
    }
    
}
