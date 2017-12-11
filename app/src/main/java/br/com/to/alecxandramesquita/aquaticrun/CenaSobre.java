package br.com.to.alecxandramesquita.aquaticrun;

import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGGameManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGInputManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScene;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScreenManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSprite;

/**
 * Created by alecxandra on 30/11/17.
 */

public class CenaSobre extends AGScene {
    //Atributos da Classe
    AGSprite imagemSobre = null;
    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public CenaSobre(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        //chamada da imagem(Atributo da Classe)
        imagemSobre=this.createSprite(R.mipmap.sobre,1,1);

        //inatancia o tamanho das imagens
        imagemSobre.setScreenPercent(100,100);

        //pega imagem e a coloca no centro da tela
        imagemSobre.vrPosition.setXY(AGScreenManager.iScreenWidth/2,AGScreenManager.iScreenHeight/2);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        voltar();
    }
    //Cria a funcão que volta para  o menu
    public void voltar(){
        if (AGInputManager.vrTouchEvents.backButtonClicked()){
            this.vrGameManager.setCurrentScene(1);
            return;

        }
    }
}
