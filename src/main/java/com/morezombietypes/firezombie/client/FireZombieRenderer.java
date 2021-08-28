package com.morezombietypes.firezombie.client;

import com.morezombietypes.MoreZombieTypesMain;


import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
@OnlyIn(Dist.CLIENT)
public class FireZombieRenderer extends ZombieRenderer{



	public FireZombieRenderer(Context p_174456_) {
		super(p_174456_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResourceLocation getTextureLocation(Zombie entityIn) {
		// TODO Auto-generated method stub
		if(entityIn.isInPowderSnow || entityIn.isInWater()) {
			return new ResourceLocation(MoreZombieTypesMain.MODID,"textures/entities/firezombieunlit.png");
		}
		return new ResourceLocation(MoreZombieTypesMain.MODID,"textures/entities/firezombielit.png");
	}
}
