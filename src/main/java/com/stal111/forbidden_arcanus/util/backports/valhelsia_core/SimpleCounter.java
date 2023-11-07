package com.stal111.forbidden_arcanus.util.backports.valhelsia_core;

import net.minecraft.nbt.CompoundTag;

public class SimpleCounter implements Counter<Integer> {
    
    protected int value;
    protected boolean active = true;
    
    public SimpleCounter() {
        this(0);
    }
    
    public SimpleCounter(int value) {
        this(value, true);
    }
    
    public SimpleCounter(int value, boolean active) {
        this.value = value;
        this.active = active;
    }
    
    @Override
    public Integer getValue() {
        return this.value;
    }
    
    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
    
    public void tick() {
        this.tick(new CompoundTag());
    }
    
    public void tick(CompoundTag tag) {
        if (this.isActive()) {
            this.increase();
        }
    }
    
    public void increase() {
        this.value++;
    }
    
    public void decrease() {
        this.value--;
    }
    
    public void reset() {
        this.value = 0;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
