package com.morezombietypes;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.morezombietypes.firezombie.FireZombie;
import com.morezombietypes.snowzombie.SnowZombie;
import com.morezombietypes.firezombie.client.FireZombieRenderer;
import com.morezombietypes.snowzombie.client.SnowZombieRenderer;
// The value here should match an entry in the META-INF/mods.toml file
@Mod("morezombietypesonan")
public class MoreZombieTypesMain
{
	public static final String MODID = "morezombietypesonan";
    // Directly reference a log4j logger.
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();

    public MoreZombieTypesMain() {
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
	public void setup(final EntityAttributeCreationEvent event)
	{
		event.put((EntityType<? extends LivingEntity>)EntityTypes.FIREZOMBIE, FireZombie.createAttributes().build());
		event.put((EntityType<? extends LivingEntity>)EntityTypes.SNOWZOMBIE, SnowZombie.createAttributes().build());
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
    	@SubscribeEvent
        public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
        	event.getRegistry().registerAll(
        			EntityTypes.FIREZOMBIE,
        			EntityTypes.SNOWZOMBIE
        			);
        	
        }
    	
    	@SubscribeEvent(priority = EventPriority.HIGH)
        public void onBiomeLoadingEvent(BiomeLoadingEvent event) {
    		//event.getSpawns().
    		if(event.getCategory() == BiomeCategory.NETHER) {
    			event.getSpawns().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityTypes.FIREZOMBIE, 100, 4, 4));
    		}
    		else if(event.getCategory() == BiomeCategory.ICY || event.getCategory() == BiomeCategory.TAIGA) {
    			event.getSpawns().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityTypes.SNOWZOMBIE, 100, 4, 4));
    		}
    	}
    	
    	
    	
    	
    	
    	
    }
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD,value=Dist.CLIENT)
	public static class RegisterRenderersZombieTypesMod {
		@SubscribeEvent
		public static void RegisterModelRenderers(final RegisterRenderers event){
			
			event.registerEntityRenderer(EntityTypes.FIREZOMBIE, FireZombieRenderer::new);
			event.registerEntityRenderer(EntityTypes.SNOWZOMBIE, SnowZombieRenderer::new);
		}
	}
    
}