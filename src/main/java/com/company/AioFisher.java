package com.company;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Npc;

import java.util.ArrayList;
import java.util.logging.Logger;

@ScriptManifest(author = "Blade", category = Category.FISHING, description = "Fishes stuff", name = "AioFisher", servers = { "2006Scape" }, version = 1)

public class AioFisher extends Script {

    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private static final int[] FISH_ID = {318,322};
    public static final int[] FISHS_ID = {317,316,315};
    @Override
    public boolean onExecute() {
        strategies.add(new Fishing());
        strategies.add(new Drop());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {

    }
private class Fishing implements Strategy {

    boolean FISHS_ID; // local variable to store the tree.

    @Override
    public boolean activate() {
         // set the local Variable
        if(Players.getMyPlayer().getAnimation() != 621 && !Inventory.isFull()){
            FISHS_ID = FISHS_ID();
        } else {
            if(!Inventory.isFull()&&Players.getMyPlayer().getAnimation()==621){
                Time.sleep(75000);
            }
        }
        return !Inventory.isFull() && Players.getMyPlayer().getAnimation() != 621; //&& FISHS_ID != null;
    }
    @Override
    public void execute() {
        //Chop the tree
        if(Players.getMyPlayer().getAnimation() != 621 &&!Inventory.isFull()){
            Time.sleep(2500);
            Npcs.getNearest(316,317);
            Time.sleep(2000);
            System.out.println("Scripting starting");
            Time.sleep(2000);
        } else {
            if(Players.getMyPlayer().getAnimation() == 621)
                System.out.println("On the money");
            Time.sleep(130000);
        }



        //tree.interact(Menu.);
        //Wait for the Player to chop the Tree
        Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
    }
}
    private boolean FISHS_ID() {
        if (Players.getMyPlayer().getAnimation() != 621 && !Players.getMyPlayer().isInCombat() && !Inventory.isFull()) {
            for (Npc FISHS_ID : Npcs.getNearest(316, 317)) {
                FISHS_ID.interact(0);
                Time.sleep(60000);
            }
        }
        return true;
    }

            //Check if the Object is around.
          //  if (FISHS_ID != null) {
                //Return the Tree Object.
            //    return FISHS_ID();
            //}



private static class Drop implements Strategy {


    @Override
    public boolean activate() {

        if(Inventory.isFull() && Inventory.containts(FISH_ID) && Players.getMyPlayer().getAnimation() != 621);{
            return true;
        }


    }

    @Override
    public void execute() {
        // Loop through all Inventory Items and Drop the once with Log ID.
        for (Item fish : Inventory.getItems(FISH_ID)) {
            //Check if Log Exists
            if (fish != null && Inventory.containts(FISH_ID)) {
                Inventory.getItems(FISH_ID);//Drop the Log.

                fish.drop();

                //Using a Static Sleep here for Tutorial sake, You can use a Dynamic one.
                Time.sleep(1000);
            }
        }
    }
}
    private static class Banking implements Strategy{

        @Override
        public boolean activate() {
           if(Inventory.isFull()&&Players.getMyPlayer().getAnimation()!=621);
            return false;
        }

        @Override
        public void execute() {
            Bank.getBanker().interact(0);
                if(Bank.open()){
                    Bank.depositAllExcept(304);
                    Bank.close();
                }

            }
        }


}


