package com.stal111.forbidden_arcanus.common.block.entity.forge.essence;

import java.util.EnumMap;
import java.util.List;
import java.util.function.UnaryOperator;

public class EssencesStorage extends EnumMap<EssenceType, Integer> {
    
    public EssencesStorage() {
        super(EssenceType.class);
    }
    
    public EssencesStorage(int aureal, int souls, int blood, int experience) {
        super(EssenceType.class);
        
        this.put(EssenceType.AUREAL, aureal);
        this.put(EssenceType.SOULS, souls);
        this.put(EssenceType.BLOOD, blood);
        this.put(EssenceType.EXPERIENCE, experience);
    }
    
    public void reduce(EssencesDefinition definition) {
        definition.forEach((type, integer) -> {
            this.put(type, this.getOrDefault(type, 0) - integer);
        });
    }
    
    @Override
    public Integer put(EssenceType key, Integer value) {
        return super.put(key, Math.max(value, 0));
    }
    
    public void modify(EssenceType type, UnaryOperator<Integer> modifier) {
        this.put(type, modifier.apply(this.get(type)));
    }
    
    public void applyModifiers(List<EssenceModifier> modifiers) {
        for (EssenceModifier modifier : modifiers) {
            this.modify(modifier.getEssenceType(), modifier::getModifiedValue);
        }
    }
    
    public EssencesDefinition immutable() {
        return new EssencesDefinition(this.getOrDefault(EssenceType.AUREAL, 0), this.getOrDefault(EssenceType.SOULS, 0), this.getOrDefault(EssenceType.BLOOD, 0), this.getOrDefault(EssenceType.EXPERIENCE, 0));
    }
}
