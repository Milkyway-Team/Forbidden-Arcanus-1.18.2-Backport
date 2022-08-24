package com.stal111.forbidden_arcanus.data.recipes;

import com.google.gson.JsonObject;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.recipe.CombineResiduesRecipe;
import com.stal111.forbidden_arcanus.core.init.ModRecipes;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author stal111
 * @since 2022-07-05
 */
public record CombineResiduesRecipeBuilder(String residueName, int residueAmount, Item result) implements RecipeBuilder {

    public static CombineResiduesRecipeBuilder of(String residueName, int residueAmount, ItemLike result) {
        return new CombineResiduesRecipeBuilder(residueName, residueAmount, result.asItem());
    }

    @Nonnull
    @Override
    public RecipeBuilder unlockedBy(@Nonnull String criterionName, @Nonnull CriterionTriggerInstance criterionTrigger) {
        return this;
    }

    @Nonnull
    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Nonnull
    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(@Nonnull Consumer<FinishedRecipe> finishedRecipeConsumer) {
        ResourceLocation recipeId = RecipeBuilder.getDefaultRecipeId(this.getResult());
        this.save(finishedRecipeConsumer, new ResourceLocation(ForbiddenArcanus.MOD_ID, "combine_residues/" + this.residueName + "_residues_to_" + recipeId.getPath()));
    }

    @Override
    public void save(@Nonnull Consumer<FinishedRecipe> finishedRecipeConsumer, @Nonnull ResourceLocation recipeId) {
        finishedRecipeConsumer.accept(new CombineResiduesRecipeBuilder.Result(recipeId, this.residueName, this.residueAmount, this.result, ModRecipes.COMBINE_RESIDUES_SERIALIZER.get()));
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation recipeId;
        private final String residueName;
        private final int residueAmount;
        private final Item result;
        private final RecipeSerializer<CombineResiduesRecipe> serializer;

        public Result(ResourceLocation recipeId, String residueName, int residueAmount, Item result, RecipeSerializer<CombineResiduesRecipe> serializer) {
            this.recipeId = recipeId;
            this.residueName = residueName;
            this.residueAmount = residueAmount;
            this.result = result;
            this.serializer = serializer;
        }

        @Override
        public void serializeRecipeData(@Nonnull JsonObject json) {
            json.addProperty("residue_name", this.residueName);
            json.addProperty("residue_amount", this.residueAmount);

            JsonObject result = new JsonObject();
            result.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.result)).toString());

            json.add("result", result);
        }

        @Nonnull
        @Override
        public ResourceLocation getId() {
            return this.recipeId;
        }

        @Nonnull
        @Override
        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}