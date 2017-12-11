package br.com.to.alecxandramesquita.aquaticrun;

import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGGameManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScene;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScreenManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSoundManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSprite;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGTimer;

/**
 * Created by alecxandra on 30/11/17.
 */

public class CenaAbertura extends AGScene {
    //Atributos
    AGTimer tempo = null;
    AGSprite imagemAbre = null;

    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public CenaAbertura(AGGameManager pManager) {
        super(pManager);
    }
    //metodo obrigatório da classe AGScene Chamado toda vez que a tela for chamada
    @Override
    public void init() {
        //instancia o tempo para mudança de tela
        tempo = new AGTimer(2000);
        //Chama a imagem
        imagemAbre = this.createSprite(R.mipmap.tela_aquatic_run,1,1);
        //instancia a musica
        AGSoundManager.vrMusic.loadMusic("music.mp3", true);
        AGSoundManager.vrMusic.play();
        //inatancia o tamanho
        imagemAbre.setScreenPercent(100,100);
        //pega imagem e a coloca no centro da tela
        imagemAbre.vrPosition.setXY(AGScreenManager.iScreenWidth/2,AGScreenManager.iScreenHeight/2);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }
    //etodo obrigatório da classe AGScene será executado a todo momento
    @Override
    public void loop() {
        //Atualiza o tempo sempre que usado
        tempo.update();
        if (tempo.isTimeEnded()){
            AGSoundManager.vrMusic.stop();
            //metodo que chama a proxima cena e destroi a atual
            this.vrGameManager.setCurrentScene(1);
            return;
        }
    }
}
