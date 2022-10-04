public class Potion extends Treasure {

    Potion() {
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public void treasureEffect(Adventurer adv) {
        adv.maxDamage = 4;
    }
}
