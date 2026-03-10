package com.example.harmonyhub.navigation.bottom_bar_nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {

    val backStack by navController.currentBackStackEntryAsState()

    val currentDestination = backStack?.destination;

    val onClick:(BottomNavRoutes)->Unit={ navItem->
        navController.navigate(navItem) {
            popUpTo(navController.graph.startDestinationId){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    NavigationBar() {
        BottomNavRoutes.items.forEach { navItem ->
            val isSelected =
                currentDestination?.hierarchy?.any {
                    it.hasRoute(navItem::class)
                } == true

            NavigationBarItem(
                selected = isSelected,
                icon = {
                    Icon(
                        imageVector = BottomNavRoutes.icons[navItem.icon]!!,
                        contentDescription = navItem.label
                    )
                },
                label = { Text(navItem.label) },
                onClick = { onClick(navItem) }
            )

        }
    }

}