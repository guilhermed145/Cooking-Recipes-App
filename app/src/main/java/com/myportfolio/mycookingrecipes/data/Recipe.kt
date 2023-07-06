package com.myportfolio.mycookingrecipes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.myportfolio.mycookingrecipes.R

data class Recipe(
    @StringRes val name : Int,
    @StringRes val recipeText : Int,
    @DrawableRes val imageResourceId: Int
)

val recipes = listOf(
    Recipe(R.string.recipe_name_1,R.string.recipe_text_1, R.drawable.lasagna),
    Recipe(R.string.recipe_name_2,R.string.recipe_text_2, R.drawable.omelette),
    Recipe(R.string.recipe_name_3,R.string.recipe_text_3, R.drawable.spaghetti),
    Recipe(R.string.recipe_name_4,R.string.recipe_text_4, R.drawable.pancakes),
    Recipe(R.string.recipe_name_5,R.string.recipe_text_5, R.drawable.lasagna),
    Recipe(R.string.recipe_name_6,R.string.recipe_text_6, R.drawable.lasagna),
    Recipe(R.string.recipe_name_7,R.string.recipe_text_7, R.drawable.lasagna),
    Recipe(R.string.recipe_name_8,R.string.recipe_text_8, R.drawable.lasagna),
)