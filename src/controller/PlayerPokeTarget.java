package controller;

import model.Player;

public class PlayerPokeTarget implements CommandInterface {
    private Player player;

    public PlayerPokeTarget(Player playerIn) {
        this.player = playerIn;
    }

    @Override
    public void execute() {
        // Implement the logic to move the target character
        player.pokeTarget();
    }
}