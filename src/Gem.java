import java.lang.reflect.Array;

public class Gem extends Treasure {

    Gem() {
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public int[] treasureEffectOnCombatDiceRolls(int advRoll, int creRoll) {
        int[] diceRolls = new int[2];
        diceRolls[0] = advRoll;
        diceRolls[1] = creRoll + 1;
        return diceRolls;
    }
}
