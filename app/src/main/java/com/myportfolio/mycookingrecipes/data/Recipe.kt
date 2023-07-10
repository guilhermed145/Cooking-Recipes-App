package com.myportfolio.mycookingrecipes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.myportfolio.mycookingrecipes.R

/**
 * A data class that represents the information presented in a recipe card.
 */
data class Recipe(
    @StringRes val name : Int,
    @StringRes val recipeText : Int,
    @DrawableRes val imageResourceId: Int
)

// A list of different cooking recipes.
val recipes = listOf(
    Recipe(R.string.recipe_name_1,R.string.recipe_text_1, R.drawable.lasagna),
    Recipe(R.string.recipe_name_2,R.string.recipe_text_2, R.drawable.omelette),
    Recipe(R.string.recipe_name_3,R.string.recipe_text_3, R.drawable.spaghetti),
    Recipe(R.string.recipe_name_4,R.string.recipe_text_4, R.drawable.pancakes),
    Recipe(R.string.recipe_name_5,R.string.recipe_text_5, R.drawable.cupcakes),
    Recipe(R.string.recipe_name_6,R.string.recipe_text_6, R.drawable.pizza)
)