import org.parabot.api.*;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.events.listeners.*;
import org.rev317.min.api.wrappers.*;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.ScriptManifest;
import org.rev317.min.api.methods;

@ScriptManifest(author = "Bladeanator", name = "Basic Combat Script", version = 2, description = "Kill's Shit", category = Category.COMBAT)

public class main extends Script {
	@Override
	public void onStart() {
		log("Script has now started!");
		
		}
		
		
		
	}
	
  	@Override
	public int onLoop() {
	
	return 600;
	}

  	@Override
	public void onExit() {
  		log("Script Has been Terminated!");
	}
  	@Override
    public int onLoop() throws InterruptedException {
        //NPC fleshCrawler = npcs.closest("Flesh Crawler");
        //try building a filter 
        NPC Cow = npcs.closest(new Filter<NPC>() {
            @Override
            public boolean match(NPC npc) {
                return npc.exists() && npc.getName().equals("Cow") && npc.isAttackable() && npc.getHealthPercent() > 0;
            }
        });
        
        if (!getCombat().isFighting() || myPlayer().getInteracting() == null){
            if (Cow != null) {
                if (Cow.isVisible()) {
                    Cow.interact("Attack");
                }
            }
        }

        return random(600, 900); 
    }
    // The amount of time in milliseconds before the loop starts over
}
