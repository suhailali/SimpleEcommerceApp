package com.suhail.simpleecommerceapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.suhail.simpleecommerceapp.home.HomeScreen
import com.suhail.simpleecommerceapp.order.OrderSummary
import androidx.hilt.navigation.compose.hiltViewModel
import com.suhail.simpleecommerceapp.home.HomeViewModel
import com.suhail.simpleecommerceapp.order.OrderSuccess
import com.suhail.simpleecommerceapp.order.OrderSummaryViewModel

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
fun AppNav(navController: NavHostController, mainViewModel: MainViewModel,
           startDestination: String = Screen.Home.route
) {
    NavHost(navController = navController, startDestination = startDestination ) {
        homeGraph(navController = navController, mainViewModel)
        orderGraph(navController = navController, mainViewModel)
    }
}

fun NavGraphBuilder.homeGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    navigation(startDestination = SCREEN_HOME, route = Screen.Home.route) {
        composable(SCREEN_HOME) {
            val parentEntry = remember {
                navController.getBackStackEntry(Screen.Home.route)
            }
            val parentViewModel = hiltViewModel<HomeViewModel>(parentEntry)
            parentViewModel.fetchData()
            HomeScreen(viewModel = parentViewModel, mainViewModel = mainViewModel,
                navController = navController)
        }
    }
}

fun NavGraphBuilder.orderGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    navigation(startDestination = SCREEN_ORDER_SUMMARY, route = Screen.Order.route) {
        composable(SCREEN_ORDER_SUMMARY) {
            val parentEntry = remember {
                navController.getBackStackEntry(Screen.Order.route)
            }
            val parentViewModel = hiltViewModel<OrderSummaryViewModel>(parentEntry)
            OrderSummary(parentViewModel, mainViewModel, navController)
        }
        composable(SCREEN_ORDER_SUCCESS) {
            OrderSuccess(navController)
        }
    }
}