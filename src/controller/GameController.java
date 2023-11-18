package controller;

import java.io.File;
import java.util.Scanner;

import model.ModelFacade;
import model.WorldImpl;

public class GameController {
  private final ControllerFacade gameFacade;
  private final ModelFacade modelFacade;
  private final Scanner scanner;
  private final WorldImpl world;
  private final GameSetupHandler setupHandler;


  public GameController(String file) {
    
    this.world = new WorldImpl(new File(file));
    this.gameFacade = new ControllerFacade(world);
    this.scanner = new Scanner(System.in);
    this.modelFacade = new ModelFacade(world);
    this.setupHandler = new GameSetupHandler(gameFacade, modelFacade, scanner);
  }

  public void startGame() {

    setupHandler.showIntro();
    setupHandler.playerCreation();
    setupHandler.takeTurn();

  }

}