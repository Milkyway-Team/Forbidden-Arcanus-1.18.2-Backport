package com.stal111.forbidden_arcanus.common.block.entity.forge.essence;

public interface EssenceModifier {
    EssenceType getEssenceType();
    
    default boolean matches(EssenceType essenceType) {
        return essenceType == this.getEssenceType();
    }
    
    int getModifiedValue(int originalValue);
}
