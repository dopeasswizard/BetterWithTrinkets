package googy.betterwithtrinkets;

import googy.betterwithtrinkets.block.BlockFuser;
import googy.betterwithtrinkets.block.entity.TileEntityFuser;
import googy.betterwithtrinkets.item.ModItems;
import googy.betterwithtrinkets.utils.IDUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;


public class BetterWithTrinkets implements ModInitializer {
    public static final String MOD_ID = "betterwithtrinkets";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);


	public static final ConfigHandler config;
	static {
		Properties prop = new Properties();
		prop.setProperty("starting_block_id", "2137");
		prop.setProperty("starting_item_id", "21370");
		prop.setProperty("fuser_inventory_id", "22");

		config = new ConfigHandler(MOD_ID, prop);

		IDUtils.init(config.getInt("starting_block_id"), config.getInt("starting_item_id"));
		config.updateConfig();
	}

	public static final Block fuser = new BlockBuilder(MOD_ID)
		.setHardness(5)
		.setResistance(1200)
		.setTags(BlockTags.MINEABLE_BY_PICKAXE)
		.build(new BlockFuser("fuser", IDUtils.nextBlock()));

    @Override
    public void onInitialize() {
        LOG.info("Better with trinkets initializing...");

		EntityHelper.createTileEntity(TileEntityFuser.class, "Fuser");

		ModItems.register();
    }
}
