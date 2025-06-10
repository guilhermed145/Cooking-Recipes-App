package com.myportfolio.mycookingrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.myportfolio.mycookingrecipes.data.Recipe
import com.myportfolio.mycookingrecipes.data.recipes
import com.myportfolio.mycookingrecipes.ui.theme.MyCookingRecipesTheme

/**
 * (PT-BR)
 * Esta é a Activity principal do aplicativo.
 * Quando criada, verifica qual tema está sendo usado pelo dispositivo e o aplica na UI.
 * Ela contém o Composable Surface, responsável por exibir o Composable AppScreen e mudar a cor
 * de fundo se baseando no tema sendo usado pelo dispositivo.
 *
 * (EN)
 * This is the app's main Activity.
 * When created, it checks which theme is being used by the device and applies it to the UI.
 * It contains the Surface Composable, responsible for displaying the AppScreen composable and
 * changing the background color based on the theme being used by the device.
 */
class MainActivity : ComponentActivity() {
    // Runs when the Activity is created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCookingRecipesTheme(
                useDarkTheme = isSystemInDarkTheme()
            ) {
                // A surface container using the 'background' color from the theme.
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppScreen()
                }
            }
        }
    }
}

/**
 * (PT-BR)
 * Este Composable exibe uma AppBar e uma lista de receitas de culinária.
 * A AppBar contém o título do aplicativo e um ícone.
 * A lista é uma LazyColumn que contém um cartão de receita para cada receita no aplicativo.
 *
 * (EN)
 * This composable displays an app bar and a list of cooking recipes.
 * The app bar has the app title and an icon.
 * The list is a LazyColumn that contains a recipe card for each recipe in the app.
 *
 * @param modifier the modifier to be applied to this composable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            MainAppBar(
                scrollBehavior = scrollBehavior,
                modifier = modifier
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { contentPadding ->
        LazyColumn(
            modifier = modifier.padding(contentPadding)
        ) {
            items(recipes.size) { index ->
                RecipeCard(
                    recipe = recipes.elementAt(index),
                    modifier = modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

/**
 * (PT-BR)
 * Este Composable exibe a AppBar.
 * Ela contém o título do aplicativo, seguido de um ícone.
 *
 * (EN)
 * This Composable displays the app bar.
 * It contains the app title followed by an icon.
 *
 * @param scrollBehavior defines the TopAppBar's behavior when scrolling through the app.
 * @param modifier the modifier to be applied to this composable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Row {
                Text(
                    text = stringResource(R.string.app_name) + " ",
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(R.drawable.appbar_title_icon),
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

/**
 * (PT-BR)
 * Este é um Composable que exibe um cartão de uma receita culinária.
 * O cartão contém as informações da receita, uma imagem e um botão de ícone que expande o cartão.
 *
 * (EN)
 * This is a Composable that displays a cooking recipe card.
 * The card contains all the recipe information, an image and an icon button to expand the card.
 *
 * @param recipe contains all the data about the card's cooking recipe.
 * @param modifier the modifier to be applied to this composable.
 */
@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(modifier = modifier) {
        Column(
            modifier = modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row {
                Text(
                    text = stringResource(recipe.name),
                    fontSize = 24.sp
                )
                Spacer(modifier = modifier.weight(1f))
                ExpandRecipeButton(
                    isExpanded = isExpanded,
                    onClick = {isExpanded = !isExpanded}
                )
            }
            Image(
                painter = painterResource(recipe.imageResourceId),
                contentDescription = stringResource(R.string.recipe_illustration),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(RoundedCornerShape(4))
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.recipe_image_height))
            )
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = stringResource(recipe.recipeText)
                )
            }
        }
    }
}

/**
 * (PT-BR)
 * Este é um composable que exibe um IconButton com um ícone de flecha.
 * O ícone de flecha muda de direção de acordo com o valor de isExpanded.
 *
 * (EN)
 * This is a Composable that displays an icon button with an arrow icon.
 * The arrow icon changes direction according to the isExpanded value.
 *
 * @param isExpanded represents whether the arrow icon's direction should be up or down.
 * @param onClick is the action that happens when the icon button is clicked.
 * @param modifier the modifier to be applied to this composable.
 */
@Composable
fun ExpandRecipeButton (
    isExpanded : Boolean,
    onClick : () -> Unit,
    modifier : Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        if (isExpanded) {
            Icon(
                Icons.Rounded.KeyboardArrowUp,
                contentDescription = stringResource(R.string.close_recipe)
            )
        } else {
            Icon(
                Icons.Rounded.KeyboardArrowDown,
                contentDescription = stringResource(R.string.open_recipe)
            )
        }
    }
}

/**
 * (PT-BR)
 * Este Composable mostra uma pré-visualização da UI do aplicativo.
 *
 * (EN)
 * This Composable shows a preview of the app's UI.
 */
@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    MyCookingRecipesTheme {
        AppScreen()
    }
}

/**
 * (PT-BR)
 * Este Composable mostra uma pré-visualização da UI do aplicativo com o tema escuro aplicado.
 *
 * (EN)
 * This Composable shows a preview of the app's UI with the dark theme applied.
 */
@Preview(showBackground = true)
@Composable
fun AppScreenDarkThemePreview() {
    MyCookingRecipesTheme(useDarkTheme = true) {
        AppScreen()
    }
}
