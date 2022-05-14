package dev.client.rose.module.impl.player;

import dev.client.rose.module.Category;
import dev.client.rose.module.Module;
import dev.event.EventListener;
import dev.event.impl.game.TickEvent;
import dev.event.impl.player.MotionEvent;
import dev.event.impl.player.MoveEvent;
import dev.settings.impl.BooleanSetting;
import dev.settings.impl.NumberSetting;
import dev.utils.misc.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import org.lwjgl.input.Keyboard;

public class FastPlace extends Module {

    private final BooleanSetting blocks = new BooleanSetting("Blocks Only", false);
    private final BooleanSetting rng = new BooleanSetting("Random", false);
    private final NumberSetting rate = new NumberSetting("Delay", 0, 6, 0, 1);

    public FastPlace() {
        super("FastPlace", "Lowers right click delay", 0, Category.PLAYER);
        addSettings(blocks, rng, rate);
    }

    private final EventListener < TickEvent > onTick = e - > {
        if (mc == null || mc.thePlayer == null)
            return;

        if (blocks.isEnabled() && mc.thePlayer.getHeldItem() != null && !(mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock))
            return;

        double speed = rate.getValue();

        if (rng.isEnabled() && speed > 0) speed = Random.dbRandom(rate.getValue() - 1, rate.getValue() + 1);

        mc.rightClickDelayTimer = (int) Math.min(mc.rightClickDelayTimer, speed);
    };
}
