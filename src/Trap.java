public class Trap extends Treasure {

    Trap() {
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public void treasureEffect(Adventurer adv) {
        adv.damage++;
    }
}
