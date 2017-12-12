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
        //cria a imagem cetando qtd linha/coluna
        imagemAgua = this.createSprite(R.mipmap.agua, 1, 1);
        imagemBarco = this.createSprite(R.mipmap.barco, 1, 1);
        imagemPrincesa = this.createSprite(R.mipmap.sofia_pedindo_ajuda, 1, 1);
        //Instancia a musica do jogo
        AGSoundManager.vrMusic.loadMusic("jogando.wav", true);
        AGSoundManager.vrMusic.play();
        // imagemBarril = this.createSprite(R.mipmap.barril,1,1);
       //instancia o tempo que irá gera os berris
        tempoGeraBarril = new AGTimer(1500);
        tempobarril = new AGTimer(5000);
        //instancia uma lista de barris para o AGSprite
        listaBarril = new ArrayList<AGSprite>();

        //inatancia o tamanho das imagens
        imagemAgua.setScreenPercent(100, 100);
        imagemBarco.setScreenPercent(10, 10);
        imagemPrincesa.setScreenPercent(10, 12);
        // imagemBarril.setScreenPercent(20,10);

        //pega imagem e a posiciona na tela
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

    //metodo Cria a funcão que volta para  o menu
    public void voltar() {
        //verifica se houve um toc no botão de voltar do aparelho
        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            AGSoundManager.vrMusic.stop();
            //chama a próxima cena nunca esquecer do returne se não dará erro a execução
            this.vrGameManager.setCurrentScene(1);
            return;

        }
    }

    //metodo cria a função demovimentação do barco
    public void barcoAnda() {
        //movimeta a barco de acordo como sua posição em relacção a x e y
        imagemBarco.vrPosition.setX(imagemBarco.vrPosition.getX() + AGInputManager.vrAccelerometer.getAccelX());
        imagemBarco.vrPosition.setY(imagemBarco.vrPosition.getY() + AGInputManager.vrAccelerometer.getAccelY());

    }

    /*método que cria os barril de forma aleatórias na tela*/
    public void geraBarril() {
        tempoGeraBarril.update();
        for (AGSprite barrilRecicla : listaBarril) {
            //Se o barril for reciclado
            if (barrilRecicla.bRecycled) {
                //Posição dele em X e Y será setada
                barrilRecicla.vrPosition.setXY(50 + (int) (Math.random() * (AGScreenManager.iScreenWidth - 50)),
                        AGScreenManager.iScreenHeight);
                // E ele será setado como não reciclado
                barrilRecicla.bRecycled = false;
                return;
            }//Fim if que pergunta se o barril ja é reciclado
        }//Fim for array de barris

        if (tempoGeraBarril.isTimeEnded()) {
            //Cria o barrio, posição, dá tamanho e coloca ele no array de barris
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

    //metodo de atualizar o barril
    public void atualizaBarril() {
        for (AGSprite imagemBarril : listaBarril) {
            //barril tera sua posição atual em y decrementada em 5 a cada frame
            imagemBarril.vrPosition.setY(imagemBarril.vrPosition.getY() - 5);
            //se a posição do barril em Y for menor do que a altura da tela
            if (imagemBarril.vrPosition.getY() < 0) {
                //Seta o barril para reciclado
                imagemBarril.bRecycled = true;
            }//Fim do if
        }//Fim do For
    }//Fim do metodo atualizabarril();,

    //Função de impaquito
    public void explode() {

        for (AGSprite barril : listaBarril) {
            //se barril colidir no barco e estiver visivel
            if (barril.collide(imagemBarco) && barril.bVisible) {
                //chama a musica de explosão
                AGSoundManager.vrMusic.loadMusic("explosao1.mp3", false);
                AGSoundManager.vrMusic.play();
                //instacia a imagem de explosão
                imagemExplosao = this.createSprite(R.mipmap.explosao, 1, 1);
                //passao tamanho da imagem para a tela
                imagemExplosao.setScreenPercent(30, 30);
                //adiciona a animação da imagem
                imagemExplosao.addAnimation(20, false, 0, 15);

                //ceta a posição da colisão de adordo com o xy dela em relação ao xy do barco
                imagemExplosao.vrPosition.setXY(imagemBarco.vrPosition.getX(), imagemBarco.vrPosition.getY());
                //ceta as dua imagns como falsa
                imagemBarco.bVisible = false;
                barril.bVisible = false;
                return;

            }
        }
    }
    //metodo com função vitória
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

    //metodo com função derrota
    public void perde() {
        tempobarril.update();
        for (AGSprite barril : listaBarril) {
            //verifica a colisao do varco com o barril ou se o tempo acabou
            if (imagemBarco.collide(barril)|| tempobarril.isTimeEnded()){
               //seta estado da variavel para verificar a função
                estado = false;
                this.vrGameManager.setCurrentScene(6);
                return;
            }
        }//Fim for array de barris
    }
}
