package com.suhail.simpleecommerceapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.suhail.simpleecommerceapp.home.HomeScreen
import com.suhail.simpleecommerceapp.order.OrderSummary

const val ROUTE_HOME = "HomeRoute"
const val ROUTE_ORDER = "OrderRoute"

const val SCREEN_HOME = "Home"
const val SCREEN_ORDER_SUMMARY = "OrderSummary"
const val SCREEN_ORDER_SUCCESS = "OrderSuccess"

internal sealed class Screen(val route: String) {
    object Home: Screen(ROUTE_HOME)
    object Order: Screen(ROUTE_ORDER)
}

@Composable
fun AppNav(navController: NavHostController,
           startDestination: String = Screen.Home.route
) {
    NavHost(navController = navController, startDestination = startDestination ) {
        homeGraph(navController = navController)
    }
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(startDestination = SCREEN_HOME, route = Screen.Home.route) {
        composable(SCREEN_HOME) {
            HomeScreen()
        }
    }
}

fun NavGraphBuilder.orderGraph(navController: NavHostController) {
    navigation(startDestination = SCREEN_ORDER_SUMMARY, route = Screen.Order.route) {
        composable(SCREEN_ORDER_SUMMARY) {
            OrderSummary()
        }
    }
}