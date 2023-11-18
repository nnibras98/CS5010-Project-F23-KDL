package model;

public class ModelFacade {
  
  private final WorldInterface world;
  
  public ModelFacade(WorldInterface world) {
    this.world = world;
  }
  
  
  
  public WorldInterface getWorld() {
    return world;
  }

}
