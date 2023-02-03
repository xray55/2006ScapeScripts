package com.company;

import jdk.nashorn.internal.runtime.regexp.joni.constants.Traverse;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.*;

import java.util.ArrayList;
import java.util.Random;

@ScriptManifest(author = "Blade", category = Category.COMBAT,
            description = "Kills Cows & Burys Bones", name = "Cow Killer",
            servers = { "2006Scape" }, version = 1.1)

    public class CowKiller extends Script {
    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private static final int[] Bone = {526, 527};
    //private static final int[] Cows1 = {1767, 1766, 81, 397};
    private static final int[] goblins ={1773,1774,1775};

    @Override
    public boolean onExecute() {
        strategies.add(new Attack());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {
        System.out.println("Scripted Has Stopped");
    }

    private static class Attack implements Strategy {
    boolean Cows;
    boolean Bones;
        @Override
        public boolean activate() {
            if(Players.getMyPlayer().getAnimation() != 412 && (Players.getMyPlayer().getAnimation() != 404) && !Inventory.contains(Bone)){
                Bones = Bones();
                Time.sleep(1000);
            } else {
                if(Players.getMyPlayer().isInCombat()) {
                    Time.sleep(500);
                } else {
                    if(!Players.getMyPlayer().isInCombat() && Players.getMyPlayer().getAnimation()== -1){
                        Cows = Cows();
                    }
                }
            } return Bones() && Cows();
        }


        @Override
        public void execute() {
        }

        private boolean Cows() {
            for (Npc Cows : Npcs.getNpcs()) {
                Time.sleep(1500);
                if (Cows == null && Cows.isInCombat() && Players.getMyPlayer().getAnimation() == 412 && Players.getMyPlayer().getAnimation() == 404 && Players.getMyPlayer().isInCombat()) {
                    Time.sleep(1500);
                } else {
                    if (Cows != null && Cows.distanceTo() <= 6 && Players.getMyPlayer().getAnimation() != 412 && Players.getMyPlayer().getAnimation() != 404 && !Players.getMyPlayer().isInCombat() && !Cows.isInCombat() && !Inventory.contains(Bone)) {
                        Npcs.getClosest(goblins);
                        Cows.interact(Npcs.Option.ATTACK);
                        Time.sleep(1500, 3000);
                    }
                }
            }
            return !Players.getMyPlayer().isInCombat() && Players.getMyPlayer().getAnimation() == -1;
        }

        private boolean Bones() {
            //int Feather =314;
            for (GroundItem Bones : GroundItems.getNearest(Bone)) {
                if (Bones != null && !Inventory.isFull() && Bones.distanceTo() <=2) {
                    Bones.getLocation();
                    Bones.take();
                    Time.sleep(1500);
                }
                for (Item Bones1 : Inventory.getItems(Bone)) {
                    if (Bones1 != null) { //checks to see if bone is in inventory and makes sure the inventory is full before burying
                        Inventory.getItems(Bone);
                        Bones1.interact(Items.Option.SECOND); // burys bone
                        Time.sleep(1500);
                    }
                }
                return true;
            }
            return true;
        }
    }
}