package com.stal111.forbidden_arcanus.data.server.tags;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.core.init.ModItems;
import com.stal111.forbidden_arcanus.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * Mod Item Tags Provider <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.data.server.tags.ModItemTagsProvider
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-02-11
 */
public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, ForbiddenArcanus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ModTags.Items.OBSIDIAN_SKULLS).add(ModItems.OBSIDIAN_SKULL.get(), ModItems.ETERNAL_OBSIDIAN_SKULL.get());
        this.tag(Tags.Items.HEADS).addTag(ModTags.Items.OBSIDIAN_SKULLS);
        this.tag(ModTags.Items.INDESTRUCTIBLE_BLACKLISTED);
        this.tag(ModTags.Items.BLACKSMITH_GAVEL).add(
                ModItems.WOODEN_BLACKSMITH_GAVEL.get(),
                ModItems.STONE_BLACKSMITH_GAVEL.get(),
                ModItems.GOLDEN_BLACKSMITH_GAVEL.get(),
                ModItems.IRON_BLACKSMITH_GAVEL.get(),
                ModItems.DIAMOND_BLACKSMITH_GAVEL.get(),
                ModItems.NETHERITE_BLACKSMITH_GAVEL.get(),
                ModItems.DEORUM_BLACKSMITH_GAVEL.get(),
                ModItems.REINFORCED_DEORUM_BLACKSMITH_GAVEL.get()
        );
        this.tag(ModTags.Items.DEORUM_INGOTS).add(ModItems.DEORUM_INGOT.get());
        this.tag(ModTags.Items.DEORUM_NUGGETS).add(ModItems.DEORUM_NUGGET.get());
        this.tag(ModTags.Items.OBSIDIAN_INGOTS).add(ModItems.OBSIDIAN_INGOT.get());
        this.tag(ModTags.Items.MAGICAL_FARMLAND_BLACKLISTED).add(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, ModItems.GOLDEN_ORCHID_SEEDS.get());
        this.tag(Tags.Items.INGOTS).addTags(ModTags.Items.DEORUM_INGOTS, ModTags.Items.OBSIDIAN_INGOTS);

        this.tag(ModTags.Items.ETERNAL_INCOMPATIBLE);
        this.tag(ModTags.Items.FIERY_INCOMPATIBLE);

        this.tag(ModTags.Items.CLIBANO_CREATES_SOUL_FIRE).add(ModItems.SOUL.get(), ModItems.CORRUPT_SOUL.get());
        this.tag(ModTags.Items.CLIBANO_CREATES_ENCHANTED_FIRE).add(ModItems.ENCHANTED_SOUL.get());

        this.copy(ModTags.Blocks.FUNGYSS_STEMS, ModTags.Items.FUNGYSS_STEMS);
        this.copy(ModTags.Blocks.CHERRYWOOD_LOGS, ModTags.Items.CHERRYWOOD_LOGS);
        this.copy(ModTags.Blocks.MYSTERYWOOD_LOGS, ModTags.Items.MYSTERYWOOD_LOGS);
        this.copy(ModTags.Blocks.EDELWOOD_LOGS, ModTags.Items.EDELWOOD_LOGS);
        this.copy(BlockTags.LOGS, ItemTags.LOGS);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.DOORS, ItemTags.DOORS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(ModTags.Blocks.RUNIC_STONES, ModTags.Items.RUNIC_STONES);
        this.copy(ModTags.Blocks.RUNE_BLOCKS, ModTags.Items.RUNE_BLOCKS);
        this.copy(ModTags.Blocks.ARCANE_CRYSTAL_ORES, ModTags.Items.ARCANE_CRYSTAL_ORES);
        this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        this.copy(ModTags.Blocks.DEORUM_STORAGE_BLOCKS, ModTags.Items.DEORUM_STORAGE_BLOCKS);
        this.copy(ModTags.Blocks.ARCANE_CRYSTAL_STORAGE_BLOCKS, ModTags.Items.ARCANE_CRYSTAL_STORAGE_BLOCKS);
        this.copy(ModTags.Blocks.DARK_NETHER_STAR_STORAGE_BLOCKS, ModTags.Items.DARK_NETHER_STAR_STORAGE_BLOCKS);
        this.copy(ModTags.Blocks.STELLARITE_STORAGE_BLOCKS, ModTags.Items.STELLARITE_STORAGE_BLOCKS);
        this.copy(ModTags.Blocks.OBSIDIAN_STORAGE_BLOCKS, ModTags.Items.OBSIDIAN_STORAGE_BLOCKS);
        this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
    }
}
