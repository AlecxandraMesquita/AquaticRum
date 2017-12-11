package br.com.to.alecxandramesquita.aquaticrun;

import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGGameManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGInputManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScene;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGScreenManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSoundManager;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGSprite;
import br.com.to.alecxandramesquita.aquaticrun.AndGraph.AGTimer;

/**
 * Created by alecxandra on 30/11/17.
 */

public class CenaMenu extends AGScene {
    //Atributos da classe
    AGSprite imagemAgua = null;
    AGSprite btnJogar = null;
    AGSprite btnSobre = null;
    AGSprite btnAjuda = null;

    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public CenaMenu(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        //Chama a imagem
        imagemAgua= this.createSprite(R.mipmap.agua,1,1);
        btnJogar = this.createSprite(R.mipmap.botao_jogar,1,1);
        btnAjuda = this.createSprite(R.mipmap.botao_ajuda,1,1);
        btnSobre = this.createSprite(R.mipmap.botao_sobre,1,1);
        //inatancia o tamanho das imagens
        imagemAgua.setScreenPercent(100,100);
        btnJogar.setScreenPercent(45,15);
        btnSobre.setScreenPercent(45,15);
        btnAjuda.setScreenPercent(45,15);
        //instancia a musica
        AGSoundManager.vrMusic.loadMusic("music.mp3", true);
        AGSoundManager.vrMusic.play();

        //pega imagem e a coloca no centro da tela
        imagemAgua.vrPosition.setXY(AGScreenManager.iScreenWidth/2,AGScreenManager.iScreenHeight/2);
        //Comandos que posiciona a imagem na tela
        btnJogar.vrPosition.setXY(AGScreenManager.iScreenWidth/2,btnJogar.vrPosition.getY()+1500);
        btnAjuda.vrPosition.setXY(AGScreenManager.iScreenWidth/2,btnAjuda.vrPosition.getY()+1000);
        btnSobre.vrPosition.setXY(AGScreenManager.iScreenWidth/2,btnSobre.vrPosition.getY()+500);



    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {

        botoes();
        sair();
    }
    //Cria função que chama os botoes do menu
    public  void botoes(){
        //Atualiza o tempo sempre que usado
        if(AGInputManager.vrTouchEvents.screenClicked()){
            if(btnJogar.collide(AGInputManager.vrTouchEvents.getLastPosition())){
                AGSoundManager.vrMusic.stop();
                this.vrGameManager.setCurrentScene(4);
                return;
            }
            if(btnSobre.collide(AGInputManager.vrTouchEvents.getLastPosition())){
                AGSoundManager.vrMusic.stop();
                this.vrGameManager.setCurrentScene(2);
                return;
            }
            if(btnAjuda.collide(AGInputManager.vrTouchEvents.getLastPosition())){
                AGSoundManager.vrMusic.stop();
                this.vrGameManager.setCurrentScene(3);
                return;
            }
        }
    }

    //cria a função sair do siatema de jogo
    public  void sair(){
        if (AGInputManager.vrTouchEvents.backButtonClicked()){
            AGSoundManager.vrMusic.stop();
            System.exit(0);
            return;
        }
    }
}
