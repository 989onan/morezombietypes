package com.morezombietypes.snowzombie;

import net.minecraft.nbt.ListTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class SnowZombie extends Zombie{

	public SnowZombie(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
		super(p_34271_, p_34272_);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public boolean fireImmune() {
		return false;
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(!super.hurt(source, amount)){return false;}
		else if (this.level.isClientSide) {return false;}
		else {
			
			boolean fireaspect = false; //set to false so that if it goes through all the enchantments and doesn't find fire aspect or flame this will stay false
			
			//grab the list of enchantments on the player's item code block...
			
			if(source.getEntity() != null) {//if damage was an entity
				if(source.getEntity().getHandSlots() != null) { //if entity has hands
					ListTag p_41711_ = source.getEntity().getHandSlots().iterator().next().getEnchantmentTags(); //get item hand enchantments
					
					if(p_41711_ != null) { //if entity is holding an enchanted item
						//iterate through all enchantments
						for(int i = 0; i < p_41711_.size(); ++i) {
							if(EnchantmentHelper.getEnchantmentId(Enchantments.FIRE_ASPECT) == EnchantmentHelper.getEnchantmentId(p_41711_.getCompound(i))||EnchantmentHelper.getEnchantmentId(Enchantments.FLAMING_ARROWS) == EnchantmentHelper.getEnchantmentId(p_41711_.getCompound(i))) { //if any enchantment equals fire aspect or flame
								//set found fire aspect to true
								fireaspect = true;
								//break since we don't need to keep checking..
								break;
							}
						}
					}
				}
			}
			
			
			//now if any heat source including fire aspect/flame enchantment has hit this zombie, deal extra damage.
			if(source.isFire() || fireaspect ||source == DamageSource.HOT_FLOOR || source == DamageSource.LAVA ||  source == DamageSource.IN_FIRE || source == DamageSource.ON_FIRE) {
				super.hurt(source, amount*2);
			}
			else {//else deal normal damage
				super.hurt(source, amount);
			}
		}
		
		
		return true;
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
      if( p_34276_ instanceof LivingEntity) {
	      if (flag) {
	         float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
	         if (this.getMainHandItem().isEmpty() && this.random.nextFloat() < f * 0.3F) {
	            ((LivingEntity) p_34276_).addEffect(new MobEffectInstance( MobEffects.MOVEMENT_SLOWDOWN,30));
	         }
	      }
      }
      return flag;
   }
	
	
	public void populateDefaultEquipmentSlots(DifficultyInstance p_34298_) {
		super.populateDefaultEquipmentSlots(p_34298_);
		boolean flag = true;
		for(EquipmentSlot equipmentslot : EquipmentSlot.values()) {
            if (equipmentslot.getType() == EquipmentSlot.Type.ARMOR) {
               ItemStack itemstack = this.getItemBySlot(equipmentslot);
               if (!flag && this.random.nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? .1F : 0.5F)) {
                  break;
               }

               flag = false;
               if (itemstack.isEmpty()) {
                  Item item = getEquipmentForSlot(equipmentslot, 0);
                  if (item != null) {
                     this.setItemSlot(equipmentslot, new ItemStack(item));
                  }
               }
            }
         }
	}
	
	
	
	
	
}
