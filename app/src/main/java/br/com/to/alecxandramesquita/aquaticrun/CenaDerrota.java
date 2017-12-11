package br.com.to.alecxandramesquita.aquaticrun;

import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGGameManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGInputManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScene;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScreenManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSoundManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSprite;

/**
 * Created by alecxandra on 10/12/17.
 */

public class CenaDerrota extends AGScene {

    AGSprite imagemGameOver=null;
    AGSprite imagemAgua = null;

    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public CenaDerrota(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        imagemAgua = this.createSprite(R.mipmap.agua,1,1);
        imagemGameOver = this.createSprite(R.mipmap.fim_jogo,1,1);
        AGSoundManager.vrMusic.loadMusic("perdeu.mp3", false);
        AGSoundManager.vrMusic.play();
        //passao tamanho dela natela
        imagemAgua.setScreenPercent(100,100);
        imagemGameOver.setScreenPercent(50,20);
        //pega imagem e a coloca no centro da tela
        imagemAgua.vrPosition.setXY(AGScreenManager.iScreenWidth/2,AGScreenManager.iScreenHeight/2);
        imagemGameOver.vrPosition.setXY(imagemGameOver.vrPosition.getX()+500,imagemGameOver.vrPosition.getY()+1000);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        //chamada da função

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
