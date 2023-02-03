package com.company;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

import java.util.ArrayList;

@ScriptManifest(author = "Blade&Jmcm71", category = Category.MINING, description = "Powermines ores",
        name = "PowerMiner", servers = { "2006Scape" }, version = 0.1)

public class PowerMiner extends Script {
    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private static final int[] ORE_ID = {441};
    private static final int[] ROCK_IDS = {2092,2093};
    private static final int[] GEM_IDS = {1620,1622,1618,1624};
    @Override
    public boolean onExecute() {
        strategies.add(new Mining());
        strategies.add(new Drop());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {

    }
    private class Mining implements Strategy {

        SceneObject rock; // local variable to store the tree.

        @Override
        public boolean activate() {
            rock = rock(); // set the local Variable
            //Check if we need to chop the tree
            return !Inventory.isFull() && Players.getMyPlayer().getAnimation() == -1 && rock != null;
        }
        @Override
        public void execute() {
            //Chop the tree
            if(Players.getMyPlayer().getAnimation() != 625&& rock.distanceTo()<=2){
                SceneObjects.getNearest(ROCK_IDS);
                rock.interact(SceneObjects.Option.FIRST);
                Time.sleep(3500);

            }else{
                if(Players.getMyPlayer().getAnimation() == 625)
                    Time.sleep(5000);
            }

            //tree.interact(Menu.);
            //Wait for the Player to chop the Tree
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
        }
    }
    private SceneObject rock() {
        for (SceneObject rock : SceneObjects.getNearest(ROCK_IDS)) {
            //Check if the Object is around.
            if (rock != null) {
                //Return the Tree Object.
                return rock;
            }
        }
        return null;
    }
    private static class Drop implements Strategy {


        @Override
        public boolean activate() {

            return Inventory.isFull()&&Players.getMyPlayer().getAnimation()==-1;
        }

        @Override
        public void execute() {
            // Loop through all Inventory Items and Drop the once with Log ID.
            for (Item ore : Inventory.getItems(ORE_ID)) {
                if (ore != null ) {
                    //Drop the Log.
                    ore.drop();
                    //Using a Static Sleep here for Tutorial sake, You can use a Dynamic one.
                    Time.sleep(1000);
                //Check if Log Exists
                }
            }
                for(Item Gem : Inventory.getItems(GEM_IDS)){
                    if (Gem != null){
                        Gem.drop();
                        Time.sleep(1500);
                    }
                }



        }
    }

}