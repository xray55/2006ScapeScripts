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

@ScriptManifest(author = "Blade", category = Category.WOODCUTTING, description = "Woodcuts Willows and drops them", name = "Powerchops Willows logs", servers = { "2006Scape" }, version = 1.1)
public class Main extends Script {
    private static boolean Drop;
    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private static final int[] LOG_ID = {1519, 1520};
    private static final int[] TREE_IDS = {1308, 1307};

    @Override
    public boolean onExecute() {
        strategies.add(new Chopping());
        strategies.add(new Logs());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {
        System.out.println("Script Stopped!");
    }

    private class Chopping implements Strategy {

        SceneObject tree; // local variable to store the tree.

        @Override
        public boolean activate() {
             tree = tree();// set the local Variable
            //Check if we need to chop the tree
            return !Inventory.isFull()&&Players.getMyPlayer().getAnimation()!=879;
        }

        @Override
        public void execute() {
            //Chop the tree
            for (SceneObject tree : SceneObjects.getNearest(TREE_IDS)) {
                //Check if the Object is around.
                if (tree != null && !Inventory.isFull() && Players.getMyPlayer().getAnimation()!= 879) {
                    SceneObjects.getNearest(TREE_IDS);
                    Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
             }
            }
        }
        private SceneObject tree() {
            for (SceneObject tree : SceneObjects.getNearest(TREE_IDS)) {
                //Check if the Object is around.
                if (tree != null && !Inventory.isFull() && Players.getMyPlayer().getAnimation() != 879) {
                    System.out.println("Cutting Tree!");
                    tree.interact(SceneObjects.Option.CHOP_DOWN);//Return the Tree Object.
                    Time.sleep(1500,5000);
                    return tree;
                }
            }
            return null;
        }
    }
    private static class Logs implements Strategy {
        @Override
        public boolean activate() {
            return Inventory.contains(LOG_ID)&&Players.getMyPlayer().getAnimation()!=879 && Inventory.isFull();
        }
        @Override
        public void execute() {
                for (Item log : Inventory.getItems(LOG_ID)) {
                    //Check if Log Exists
                    if (log != null) {
                       log.drop();
                        System.out.println("Dropping the logs!");
                        Time.sleep(500,750);
                    }
            }
        }
    }
}