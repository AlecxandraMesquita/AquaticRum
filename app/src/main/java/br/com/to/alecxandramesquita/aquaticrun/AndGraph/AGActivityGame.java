/********************************************
 Class: AGAtivivityGame
 Description: Android activity class
 Author: Silvano Maneck Malfatti
 Date: 05/11/2013
 ********************************************/

//Engine package
package br.com.to.alecxandramesquita.aquaticrun.AndGraph;

//Used packages
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import br.com.to.alecxandramesquita.aquaticrun.CenaAbertura;
import br.com.to.alecxandramesquita.aquaticrun.CenaAjuda;
import br.com.to.alecxandramesquita.aquaticrun.CenaDerrota;
import br.com.to.alecxandramesquita.aquaticrun.CenaMenu;
import br.com.to.alecxandramesquita.aquaticrun.CenaPlay;
import br.com.to.alecxandramesquita.aquaticrun.CenaSobre;
import br.com.to.alecxandramesquita.aquaticrun.CenaVitoria;


public class AGActivityGame extends Activity
{
	//Attributes
	protected AGGameManager vrManager = null;

	/********************************************
	 * Name: AGActivity
	 * Description: constructor
	 * Parameters: Activity, boolean
	 * Returns: none
	 ******************************************/
	public void init(Activity context, boolean accel)
	{
		vrManager = new AGGameManager(context, accel);
	}
	
	/********************************************
	* Name: onCreate()
	* Description: method called to create the application
	* Parameters: Bundle
	* Returns: none
	******************************************/
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//Cria o agente da aplicação
		this.vrManager = new AGGameManager(this, true);
		//Instancia a classe Cena com o vrManager
		CenaAbertura abertura = new CenaAbertura(vrManager);
        CenaMenu Menu = new CenaMenu(vrManager);
		CenaSobre sobre= new CenaSobre(vrManager);
		CenaAjuda ajuda = new CenaAjuda(vrManager);
		CenaPlay jogar = new CenaPlay(vrManager);
		CenaVitoria vitoria = new CenaVitoria(vrManager);
		CenaDerrota derrota = new CenaDerrota(vrManager);
		vrManager.addScene(abertura);//numero representado pela primeira cena é 0
        vrManager.addScene(Menu);//numero representado pela segunda cena é 1
		vrManager.addScene(sobre);//numero representado pela terceira cena é 2
		vrManager.addScene(ajuda);//numero representado pela quarta cena é 3
		vrManager.addScene(jogar);//numero representado pela quinta cena é 4
		vrManager.addScene(vitoria);//numero representado pela sexta cena é 5
		vrManager.addScene(derrota);//numero representado pela setima cena é 6
	}
  
	/*******************************************
	* Name: onPause()
	* Description: method called to pause application
	* Parameters: none
	* Returns: none
	******************************************/
	protected void onPause()
	{
		super.onPause();
		AGSoundManager.vrMusic.pause();
		vrManager.onPause();
	}
	
	/*******************************************
	* Name: onResume()
	* Description: method called after resume application
	* Parameters: none
	* Returns: none
	******************************************/
	protected void onResume()
	{
		super.onResume();
		vrManager.onResume();
		AGSoundManager.vrMusic.play();
	}
  
	/*******************************************
	* Name: onBackPressed()
	* Description: method called when Android back button is pressed
	* Parameters: none
	* Returns: none
	*****************************************/
	public void onBackPressed()
	{
		AGInputManager.vrTouchEvents.bBackButtonClicked = true;
	}
  
	/*******************************************
	* Name: onDestroy()
	* Description: method called when applicatin is destroyed
	* Parameters: none
	* Returns: none
	*****************************************/
	protected void onDestroy()
	{
		super.onDestroy();
		vrManager.release();
		vrManager = null;
		System.gc();
	}
}
