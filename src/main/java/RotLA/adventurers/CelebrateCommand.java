package RotLA.adventurers;

import RotLA.Combat;

public class CelebrateCommand implements Command {
    private Adventurer playingAdv;

    public CelebrateCommand(Adventurer adv) {
        this.playingAdv = adv;
    }

    public void execute() {
        Combat myFight = playingAdv.setCelebrations();
        String fightOutcome = myFight.fight(1, 0);
        String[] celebration = fightOutcome.split("!");
        if (celebration.length > 1)
            playingAdv.notifySubscribers(playingAdv.type + " Celebrates: " + celebration[1]);

    }
}
