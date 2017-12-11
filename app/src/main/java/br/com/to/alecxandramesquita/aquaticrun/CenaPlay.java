package br.com.to.alecxandramesquita.aquaticrun;

import java.util.ArrayList;

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

public class CenaPlay extends AGScene {
    //Atributos da classe
    AGSprite imagemAgua = null;
    AGSprite imagemBarco = null;
    AGSprite imagemPrincesa = null;
    AGSprite imagemBarril = null;
    AGSprite imagemExplosao = null;
    Boolean estado = false;

    AGSprite up, left, right, upOn, leftOn, rightOn = null;
    ArrayList<AGSprite> listaBarril = null;
    AGTimer tempoGeraBarril = null;
    AGTimer tempobarril = null;

    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public CenaPlay(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        //Chama a imagem
        imagemAgua = this.createSprite(R.mipmap.agua, 1, 1);
        imagemBarco = this.createSprite(R.mipmap.barco, 1, 1);
        imagemPrincesa = this.createSprite(R.mipmap.sofia_pedindo_ajuda, 1, 1);
        //Instancia a musica do jogo
        AGSoundManager.vrMusic.loadMusic("jogando.wav", true);
        AGSoundManager.vrMusic.play();
        // imagemBarril = this.createSprite(R.mipmap.barril,1,1);
        tempoGeraBarril = new AGTimer(1500);
        tempobarril = new AGTimer(5000);
        //instancia uma liata de barris
        listaBarril = new ArrayList<AGSprite>();

        //inatancia o tamanho das imagens
        imagemAgua.setScreenPercent(100, 100);
        imagemBarco.setScreenPercent(10, 10);
        imagemPrincesa.setScreenPercent(10, 12);
        // imagemBarril.setScreenPercent(20,10);

        //pega imagem e a coloca no centro da tela
        imagemAgua.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, AGScreenManager.iScreenHeight / 2);
        imagemBarco.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, imagemBarco.vrPosition.getY() + 180);
        imagemPrincesa.vrPosition.setXY(AGScreenManager.iScreenWidth / 2, imagemPrincesa.vrPosition.getY() + 1800);
        // imagemBarril.vrPosition.setXY(AGScreenManager.iScreenWidth/2,AGScreenManager.iScreenHeight/2);
    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        //chamada dos métodos
        geraBarril();
        atualizaBarril();
        explode();
        barcoAnda();
        //chama o voltar do próprio aparelho
        voltar();
        //verifica o estado para chamar a tela de vitoria ou derrota
       if (estado == true) {
           vitoria();
       }
        else if (estado = true) {
            perde();
        }


    }

    //Cria a funcão que volta para  o menu
    public void voltar() {
        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            AGSoundManager.vrMusic.stop();
            this.vrGameManager.setCurrentScene(1);
            return;

        }
    }

    //cria a função demovimentação do barco
    public void barcoAnda() {
        //movimeta a po
        imagemBarco.vrPosition.setX(imagemBarco.vrPosition.getX() + AGInputManager.vrAccelerometer.getAccelX());
        imagemBarco.vrPosition.setY(imagemBarco.vrPosition.getY() + AGInputManager.vrAccelerometer.getAccelY());

    }

    /*método que cria os meteóros de forma aleatórias na tela*/
    public void geraBarril() {
        tempoGeraBarril.update();
        for (AGSprite barrilRecycled : listaBarril) {
            //Se o meteóro for reciclado
            if (barrilRecycled.bRecycled) {
                //Posição dele em X e Y será setada
                barrilRecycled.vrPosition.setXY(50 + (int) (Math.random() * (AGScreenManager.iScreenWidth - 50)),
                        AGScreenManager.iScreenHeight);
                // E ele será setado como não reciclado
                barrilRecycled.bRecycled = false;
                return;
            }//Fim if que pergunta se o meteóro ja é reciclado
        }//Fim for array de meteóros

        if (tempoGeraBarril.isTimeEnded()) {
            //Cria o meteóro, posicion, dá tamanho e coloca ele no array de meteoros
            imagemBarril = createSprite(R.mipmap.barril, 1, 1);
            imagemBarril.setScreenPercent(13, 8);
            //Posição X é randomica com números entre 50 e tamanho da tela - 50
            imagemBarril.vrPosition.setXY(50 + (int) (Math.random() * (AGScreenManager.iScreenWidth - 50)),
                    AGScreenManager.iScreenHeight);
            imagemBarril.bRecycled = false;
            listaBarril.add(imagemBarril);
            tempoGeraBarril.restart();
        }
    } //Fim metódo geraBarril

    //metodo de atualizar o
    public void atualizaBarril() {
        for (AGSprite imagemBarril : listaBarril) {
            //Meteoro tera sua posição atual em y decrementada em 5 a cada frame
            imagemBarril.vrPosition.setY(imagemBarril.vrPosition.getY() - 5);
            //se a posição do meteóro em Y for menor do que a altura da tela
            if (imagemBarril.vrPosition.getY() < 0) {
                //Seta o meteóro para reciclado
                imagemBarril.bRecycled = true;
            }//Fim do if
        }//Fim do For
    }//Fim do método atualizaMeteoro();,

    //Função de impaquito
    public void explode() {

        for (AGSprite m : listaBarril) {
            if (m.collide(imagemBarco) && m.bVisible) {
                AGSoundManager.vrMusic.loadMusic("explosao1.mp3", false);
                AGSoundManager.vrMusic.play();
                //instacia a imagem
                imagemExplosao = this.createSprite(R.mipmap.explosao, 1, 1);
                //passao tamanho dela natela
                imagemExplosao.setScreenPercent(30, 30);
                //adiciona a animação
                imagemExplosao.addAnimation(20, false, 0, 15);

                //faz a preparação para colisão
                imagemExplosao.vrPosition.setXY(imagemBarco.vrPosition.getX(), imagemBarco.vrPosition.getY());
                imagemBarco.bVisible = false;
                m.bVisible = false;
                return;


            }
        }
    }
    //função vitória
    public void vitoria() {
        //verifiva a cgegada do barco na princesa
        if (imagemBarco.collide(imagemPrincesa)) {
            //seta estado da variavel para verificar a função
            estado = true;
            this.vrGameManager.setCurrentScene(5);
            return;
        }
        estado=false;
    }

    //função derrota
    public void perde() {
        tempobarril.update();
        for (AGSprite barril : listaBarril) {
            if (imagemBarco.collide(barril)|| tempobarril.isTimeEnded()){
               //seta estado da variavel para verificar a função
                estado = false;
                this.vrGameManager.setCurrentScene(6);
                return;
            }
        }
    }//Fim for array de meteóros


}
