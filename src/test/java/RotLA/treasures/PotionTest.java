package RotLA.treasures;

import RotLA.Careless;
import RotLA.Expert;
import RotLA.adventurers.Adventurer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PotionTest {

    @Test
    void treasureEffect() {
        System.out.println("Test to check if Potion treasure Increases a adventurers maximum damage limit");
        Treasure potion = new Potion();
        Adventurer adv = new Adventurer(new Expert(), new Careless(), "Brawler",12);
        int maxDamage = adv.maxDamage;
        potion.treasureEffect(adv);
        assertEquals(maxDamage+1, adv.maxDamage);

    }
}