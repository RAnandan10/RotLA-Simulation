package RotLA;

import RotLA.adventurers.Adventurer;

class AdventurerCreator {
    public Adventurer createAdventurer() {
        return new Adventurer();
    }
}

class BrawlerCreator extends AdventurerCreator{
    @Override
    public Adventurer createAdventurer() {
        return new Adventurer(new Expert(), new Careless(), "Brawler",12);
    }
}

class SneakerCreator extends AdventurerCreator{
    @Override
    public Adventurer createAdventurer() {
        return new Adventurer(new Stealth(), new Quick(), "Sneaker",8);
    }
}

class RunnerCreator extends AdventurerCreator{
    @Override
    public Adventurer createAdventurer() {
        return new Adventurer(new Untrained(), new Quick(), "Runner",10);
    }
}

class ThiefCreator extends AdventurerCreator{
    @Override
    public Adventurer createAdventurer() {
        return new Adventurer(new Trained(), new Careful(), "Thief",10);
    }
}