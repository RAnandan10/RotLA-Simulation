public class Portal extends Treasure {

    Portal() {
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public void treasureEffect(Adventurer adv) {
        //extra move for adventurer
    }
}
