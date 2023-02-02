package com.company;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

import java.util.ArrayList;

@ScriptManifest(author = "Blade", category = Category.FIREMAKING, description = "Woodcutting reglogs and Makes FIres", name = "FireMaking", servers = { "2006Scape" }, version = 1)
public class Firemaking extends Script {
    private static boolean Drop;
    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private static final int[] LOG_ID = {1511, 1512};
    private static final int[] TREE_IDS = {1278, 1277};
    private static final int[] tinder_box = {590, 591};

    @Override
    public boolean onExecute() {
        strategies.add(new Chopping());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {

    }

    private class Chopping implements Strategy {

        SceneObject tree; // local variable to store the tree.

        @Override
        public boolean activate() {
            tree = tree(); // set the local Variable
            //Check if we need to chop the tree
            return Players.getMyPlayer().getAnimation() != 879 && tree != null && Players.getMyPlayer().getAnimation() != 733 && !Inventory.contains(LOG_ID);

        }

        @Override
        public void execute() {
            //Chop the tree
            if (!Inventory.contains(LOG_ID)) {
                //SceneObjects.getNearest(1278);
                System.out.println("Cutting Tree!");
                tree.interact(SceneObjects.Option.CHOP_DOWN);
                Time.sleep(1000, 3000);

            } else
                for (Item log : Inventory.getItems(LOG_ID)) {
                    for (Item tinderbox : Inventory.getItems(tinder_box)) {//Check if Log Exists
                        if (log != null) {
                            //Drop the Log.
                            log.interact(Items.Option.USE_WITH);
                            Time.sleep(500);
                            tinderbox.interact(Items.Option.USE_WITH);
                            Time.sleep(500, 1000);
                            System.out.println("Burning the logs!");
                        }
                    }
                }
            if (Players.getMyPlayer().getAnimation() == 879 && Players.getMyPlayer().getAnimation() == 733)
                Time.sleep(5000);
        }

        private SceneObject tree() {
            for (SceneObject tree : SceneObjects.getNearest(TREE_IDS)) {
                //Check if the Object is around.
                if (tree != null && !Inventory.isFull() && !Inventory.contains(LOG_ID)) {
                    //Return the Tree Object.
                    return tree;
                }
            }
            return null;
        }
    }
}


