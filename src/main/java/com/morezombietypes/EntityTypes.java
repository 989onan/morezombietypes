package com.morezombietypes;



import com.morezombietypes.firezombie.FireZombie;
import com.morezombietypes.snowzombie.SnowZombie;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntityTypes {
	
	@SuppressWarnings("unchecked")
	public static final EntityType<FireZombie> FIREZOMBIE = (EntityType<FireZombie>) EntityType.Builder.of(FireZombie::new,MobCategory.MONSTER).build(MoreZombieTypesMain.MODID+":firezombie").setRegistryName(new ResourceLocation(MoreZombieTypesMain.MODID, "firezombie"));
	@SuppressWarnings("unchecked")
	public static final EntityType<SnowZombie> SNOWZOMBIE = (EntityType<SnowZombie>) EntityType.Builder.of(SnowZombie::new,MobCategory.MONSTER).build(MoreZombieTypesMain.MODID+":snowzombie").setRegistryName(new ResourceLocation(MoreZombieTypesMain.MODID, "snowzombie"));
	@SubscribeEvent
	public static void RegisterEntitySpawnEggs(final RegistryEvent.Register<Item> registry) {
		registry.getRegistry().registerAll(
							new SpawnEggItem(FIREZOMBIE,16754176,16711680,new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)).setRegistryName(new ResourceLocation(MoreZombieTypesMain.MODID,"firezombie_spawnegg")),
							new SpawnEggItem(SNOWZOMBIE,12434877,12105912,new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)).setRegistryName(new ResourceLocation(MoreZombieTypesMain.MODID,"snowzombie_spawnegg"))
						);
	}
}
