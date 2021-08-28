package com.morezombietypes.snowzombie.client;

import com.morezombietypes.MoreZombieTypesMain;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowZombieRenderer extends ZombieRenderer{


	public SnowZombieRenderer(Context p_174456_) {
		super(p_174456_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResourceLocation getTextureLocation(Zombie entityIn) {
		// TODO Auto-generated method stub
		return new ResourceLocation(MoreZombieTypesMain.MODID,"textures/entities/snowzombie.png");
	}
	
}
