package com.morezombietypes.firezombie;

import net.minecraft.core.particles.ParticleTypes;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class FireZombie extends Zombie{

	
	public FireZombie(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
		super(p_34271_, p_34272_);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public boolean fireImmune() {
		return true;
	}
	
	
	@Override
	protected boolean convertsInWater() {
		return true;
	}
	
	@Override
	protected boolean isSunSensitive() {
		return false;
	}
	
	@Override
	public boolean isOnFire() {
		return false;
	}
	
	@Override
	protected void doUnderWaterConversion() {
		this.convertToZombieType(EntityType.ZOMBIE);
		if (!this.isSilent()) {
			this.level.levelEvent((Player)null, 1040, this.blockPosition(), 0);
		}
	}
	
	@Override
	public boolean doHurtTarget(Entity p_34276_) {
      boolean flag = super.doHurtTarget(p_34276_);
      if (flag) {
         float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
         if (this.getMainHandItem().isEmpty() && !this.isInWater() && this.random.nextFloat() < f * 0.3F) {
            p_34276_.setSecondsOnFire(2 * (int)f);
         }
      }

      return flag;
   }
	
	@Override 
	public void tick() {
		super.tick();
		if(!this.isInWater()) {
			for(int i = 0; i < 2; ++i) {
	            this.level.addParticle(ParticleTypes.FLAME, this.getRandomX(0.5D), (this.getRandomY())+(.1), this.getRandomZ(0.5D), 0, 0, 0);
			}
		}
	}
	
	@Override
	public float getBrightness() {
		if(!this.isInWater()) {
	      return 1.0F;
		}
		return super.getBrightness();
	}
	
	
}
