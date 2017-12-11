package br.com.to.alecxandramesquita.aquaticrun;

import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGGameManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGInputManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScene;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScreenManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSoundManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSprite;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGTimer;

/**
 * Created by alecxandra on 09/12/17.
 */

public class CenaVitoria extends AGScene {

    //Atributos da classe
    AGSprite imagemVitoria = null;
//    AGSprite imagemPrincesa = null;
    AGTimer tempo =null;


    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public CenaVitoria(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        //Chama a imagem
        imagemVitoria= this.createSprite(R.mipmap.vitoria,1,1);
//        imagemPrincesa = this.createSprite(R.mipmap.princesasofia_agradecida,1,1);
        AGSoundManager.vrMusic.loadMusic("vitoria.wav", false);
        AGSoundManager.vrMusic.play();
        tempo = new AGTimer(10000);
        //inatancia o tamanho das imagens
        imagemVitoria.setScreenPercent(100,100);
//        imagemPrincesa.setScreenPercent(10,15);

        //pega imagem e a coloca no centro da tela
        imagemVitoria.vrPosition.setXY(AGScreenManager.iScreenWidth/2,AGScreenManager.iScreenHeight/2);
//        imagemPrincesa.vrPosition.setXY(AGScreenManager.iScreenWidth/2,imagemPrincesa.vrPosition.getY()+900);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        //Chamada das funções
        voltar();
    }
    //Cria a funcão que volta para  o menu
    public void voltar(){
        if (AGInputManager.vrTouchEvents.backButtonClicked()){
            AGSoundManager.vrMusic.stop();
            this.vrGameManager.setCurrentScene(1);
            return;
        }
    }
}
