package com.company;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.*;
import java.util.ArrayList;

@ScriptManifest(author = "Blade", category = Category.FISHING, description = "123", name = "Herefishyfishy", servers = { "2006Scape" }, version = 1.1)
    public class Sharkfisher extends Script {
    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private static final int[] Shark_ID = {384};
    private static final int[] SharkSpot = {313, 314};
   public  final  Tile[] BankSpot ={new Tile(2596,3420) ,new Tile(2586, 3418)};
   TilePath path2 = new TilePath((BankSpot));
   public final Tile[] Sharkspot1 = { new Tile(2596,3420) , new Tile(2602, 3420)};
    TilePath path = new TilePath(Sharkspot1);
    @Override
    public boolean onExecute() {
        strategies.add(new Fishing());
        provide(strategies);
        return true;
    }
    @Override
    public void onFinish() {
        System.out.println("Script Stopped");
    }
    private class Fishing implements Strategy {
        boolean FISHS_ID;
        boolean bank;
        @Override
        public boolean activate() {
                if(Players.getMyPlayer().getAnimation() != 618 && !Inventory.isFull()){
                    FISHS_ID = FISHS_ID();
                } else {
                    if(Players.getMyPlayer().getAnimation() == 618 && !Inventory.isFull()){
                        System.out.println("Waiting on Inventory to Fill up!");
                        Time.sleep(25000);
                    } else {
                        if(Inventory.isFull()){
                            bank = bank();
                        }
                    }
                }
            return true;
        }
        private boolean bank() {
            if (Players.getMyPlayer().getAnimation() != 618 && !Players.getMyPlayer().isInCombat() && Inventory.isFull()) {
                path2.traverse();
                Time.sleep(1250);
                path2.getNextTile();
                Time.sleep(1250);
                path2.hasReached();
                Time.sleep(1250);
                for (SceneObject Bank_booth : SceneObjects.getNearest(2213)) {
                       Time.sleep(1250);
                  if(Bank_booth !=null && Players.getMyPlayer().distanceTo() < 4 )
                     // Bank_booth.interact(1);
                        Bank_booth.interact(SceneObjects.Option.SECOND);
                        Time.sleep(1250);
                        Bank.depositAllExcept(312);
                        Time.sleep(1250);
                    Bank.close();
                    if(Players.getMyPlayer().getAnimation() == -1 &&!Inventory.isFull()&&Inventory.contains(312) && !Inventory.contains(Shark_ID)){
                        return FISHS_ID;
                    } else {
                        if (Inventory.isFull()){
                            return bank;
                        }
                }

                }
            }
            return false;
        }
        @Override
        public void execute() {
            if(Players.getMyPlayer().getAnimation() != 618 &&!Inventory.isFull()){
                Time.sleep(2500);
                path.traverse();
               path.getNextTile();
               path.hasReached();
                Time.sleep(2500);
                Npcs.getNearest(314);
                Time.sleep(2000);
                System.out.println("Scripting starting");
                Time.sleep(2000);
                } else {
                if(Players.getMyPlayer().getAnimation() == 618)
                    System.out.println("On the money");
                Time.sleep(130000);
            }
            if(Inventory.isFull()){
                bank = bank();
            }
        }
        private boolean FISHS_ID() {
            if (Players.getMyPlayer().getAnimation() != 618 && !Players.getMyPlayer().isInCombat() && !Inventory.isFull()) {
                for (Npc FISHS_ID : Npcs.getNearest(SharkSpot)) {
                    path.traverse();
                    path.getNextTile();
                    path.hasReached();
                    FISHS_ID.interact(Npcs.Option.HARPOON);
                    Time.sleep(10000);
                }
            }
            return true;
        }
    }
}