package com.company;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.GroundItem;

import java.util.ArrayList;
@ScriptManifest(author = "Blade", category = Category.UTILITY, description = "Collect Feathers", name = "Feather collector", servers = { "2006Scape" }, version = 1)

public class FeatherCollector extends Script {
    public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    @Override
    public void onFinish() {
    }
        @Override
        public boolean onExecute() {
            strategies.add(new Collecting());
            provide(strategies);
            return true;
        }

    private class Collecting implements Strategy {
        @Override
        public boolean activate() {
            return true;
        }

        @Override
        public void execute() {
            for(GroundItem Feather : GroundItems.getNearest(314,315)){
                if(Feather != null){
                    Feather.take();
                    Time.sleep(2500);
                }
            }
        }
    }
}




