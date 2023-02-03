package combat;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.*;

import java.util.ArrayList;

@ScriptManifest(author = "Blade", category = Category.COMBAT, description = "Kills Chicken", name = "ChickenKiller", servers = { "2006Scape" }, version = 1.1)
public class Chickenkiller extends Script {
    private final int[] featherIds = {314,315};
    private final int[] boneIds = {526,527};
    private final int chickenNpcId = 41;
    private final int[] attackAnimations = {451, 827, 404};
    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    @Override
    public boolean onExecute() {
        strategies.add(new AttackStrategy());
        provide(strategies);
        return true;
    }

    public class AttackStrategy implements Strategy {
        private Npc chicken;
        @Override
        public boolean activate() {
            return !Players.getMyPlayer().isInCombat() && Players.getMyPlayer().getAnimation() == -1 &&
                    (chicken = getChicken()) != null
                    && Players.getMyPlayer().getAnimation() != 451 &&
                    Players.getMyPlayer().getAnimation() !=827 &&
                    Players.getMyPlayer().getAnimation()!=404;


        }



        @Override
        public void execute() {
            if (Inventory.contains(boneIds)) {
                buryBones();
            }
            pickUpFeathers();
            Time.sleep(500, 2500);
            pickupBones();
            Time.sleep(500,1500);
            chicken.interact(Npcs.Option.ATTACK);
            Time.sleep(1500,3000);
        }
        private void pickupBones(){
            for(GroundItem Bones: GroundItems.getNearest(boneIds)){
                if(boneIds != null && !Inventory.isFull()){
                    Bones.take();
                    Time.sleep(1500,3000);
                }
            }
        }

        private boolean buryBones() {
            for (Item Bones1 : Inventory.getItems(boneIds)) {
                if (Bones1 != null) { //checks to see if bone is in inventory and makes sure the inventory is full before burying
                    Bones1.interact(Items.Option.SECOND); // burys bone
                    Time.sleep(500,1500);
                }
            }
            return Inventory.contains(boneIds);
        }
        private boolean pickUpFeathers() {
            for (GroundItem feather : GroundItems.getNearest(featherIds)) {
                if (feather == null) {
                    return false;
                }
               feather.take();
                Time.sleep(1500,2000);
            } return featherIds !=null;
        }
        private Npc getChicken() {
            for (Npc npc : Npcs.getNearest(chickenNpcId)) {
                if (npc.distanceTo() <= 4 && !npc.isInCombat() && npc.getAnimation() == -1) {
                    return npc;
                }
            }
            return null;
        }
    }
}
