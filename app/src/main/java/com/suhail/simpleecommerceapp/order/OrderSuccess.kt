package com.suhail.simpleecommerceapp.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.suhail.simpleecommerceapp.R
import com.suhail.simpleecommerceapp.SCREEN_HOME
import com.suhail.simpleecommerceapp.Screen

@Composable
fun OrderSuccess(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_done),
            contentDescription = stringResource(R.string.order_success)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.congratulations),
            fontSize = 24.sp,
            modifier = Modifier.testTag("testTagCongrats")
        )
        Text(text = stringResource(R.string.order_success))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(SCREEN_HOME) {
                popUpTo(Screen.Home.route)
            }
        }) {
            Text(text = stringResource(R.string.okay))
        }
    }
}