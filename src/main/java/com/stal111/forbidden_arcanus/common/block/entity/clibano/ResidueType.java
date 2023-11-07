package com.stal111.forbidden_arcanus.common.block.entity.clibano;

import com.stal111.forbidden_arcanus.util.backports.Components;
import net.minecraft.network.chat.MutableComponent;

public record ResidueType(String name) {

public MutableComponent getComponent() {
        return Components.translatable("gui.forbidden_arcanus.clibano.residue." + this.name);
        }
}
