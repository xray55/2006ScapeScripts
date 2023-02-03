package com.company;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.*;
import java.util.ArrayList;

@ScriptManifest(author = "Blade", category = Category.FISHING, description = "123", name = "NetFisher", servers = { "2006Scape" }, version = 1.1)
public class netfisher extends Script {
    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private static final int[] FISH_ID = {322,317};
    private static final int[] NetSpot = {316, 317};
    public  final  Tile[] BankSpot ={new Tile(3273,3151),new Tile(3275,3156) ,new Tile(3269, 3164)};
    TilePath path2 = new TilePath((BankSpot));
    public final Tile[] Netspot1 = { new Tile(3273,3151),new Tile(3275,3156) , new Tile(3275, 3141)};
    TilePath path = new TilePath(Netspot1);
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
            if(Players.getMyPlayer().getAnimation() != 621 && !Inventory.isFull()&& NetSpot != null){
                FISHS_ID = FISHS_ID();
            } else {
                if(Players.getMyPlayer().getAnimation() == 621 && !Inventory.isFull()){
                    System.out.println("Waiting on Inventory to Fill up!");
                    Time.sleep(25000);
                } else {
                    if(Players.getMyPlayer().getAnimation()==-1 && Inventory.isFull()){
                        bank = bank();
                    }
                }
            }
            return true;
        }
        private boolean bank() {
            if(Players.getMyPlayer().getAnimation() == -1 &&!Inventory.isFull()&&Inventory.containts(304)&&!Inventory.containts(FISH_ID)){
                return FISHS_ID;
            } else {
                if (Players.getMyPlayer().getAnimation() != 621 && !Players.getMyPlayer().isInCombat() && Inventory.isFull()) {
                    path2.traverse();
                    Time.sleep(2500);
                    path2.getNextTile();
                    Time.sleep(2500);
                    path2.hasReached();
                    Time.sleep(2500);
                    for (SceneObject Bank_booth : SceneObjects.getNearest(2213)) {
                        Time.sleep(2500);
                        if(Bank_booth !=null && Players.getMyPlayer().distanceTo() <10 )
                            Bank_booth.interact(1);
                        Time.sleep(5500);
                        Bank.depositAllExcept(304);
                        Time.sleep(2500);
                        Bank.close();
                        Time.sleep(1500);
                        if (!Inventory.isFull() && Players.getMyPlayer().getAnimation() ==-1 && !Inventory.containts(FISH_ID)){
                            return FISHS_ID;
                        }
                    }
                }
            }
            return true;
        }
        @Override
        public void execute() {
            if (Players.getMyPlayer().getAnimation() == -1 && Inventory.isFull()) {
                bank = bank();
            } else {
                if (Players.getMyPlayer().getAnimation() != 621 && !Inventory.isFull() &&!Inventory.containts(FISH_ID)) {
                    path.traverse();
                    Time.sleep(2500);
                    path.getNextTile();
                    Time.sleep(2500);
                    path.getNextTile();
                    Time.sleep(2500);
                    path.hasReached();
                    Time.sleep(2000);
                    System.out.println("Scripting starting");
                    Time.sleep(2000);
                } else {
                    if (Players.getMyPlayer().getAnimation() == 621)
                        System.out.println("On the money");
                    Time.sleep(30000);
                }

            }
        }
        private boolean FISHS_ID() {
            if (Players.getMyPlayer().getAnimation() == -1 &&Players.getMyPlayer().getAnimation() != 621 && !Players.getMyPlayer().isInCombat() && !Inventory.isFull() && NetSpot != null &&Players.getMyPlayer().distanceTo()<3) {
                for (Npc FISHS_ID : Npcs.getNearest(NetSpot)) {
                    path.traverse();
                    Time.sleep(2500);
                    path.getNextTile();
                    Time.sleep(2500);
                    path.hasReached();
                    FISHS_ID.interact(0);
                    Time.sleep(120000);
                }
            } else {
                if(Players.getMyPlayer().getAnimation()==621){
                    Time.sleep(120000);
                } if (Inventory.isFull()&&Players.getMyPlayer().getAnimation()==-1){
                    return bank();
                }
            }
            return FISHS_ID;
        }
    }
}