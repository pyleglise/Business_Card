package com.axialdata.businesscard

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.axialdata.businesscard.ui.theme.BusinessCardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            BusinessCardTheme{
                BusinessCardScreen(userData = UserData)
            }
        }
    }
}

@Composable
fun BusinessCardScreen(userData: UserData, modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    Surface(
        color = colors.surface,
        modifier = modifier.fillMaxSize()
    ) {
        BusinessCard(
            userData = userData
        )
    }
}

@Composable
fun BusinessCard(userData: UserData, modifier: Modifier = Modifier){
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {   // Landscape
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = modifier
                    .padding(top = 40.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) { UserInfoCard(modifier = modifier, userData = userData) }

                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom
                ) { UserCoordCard(modifier = modifier, userData = userData) }
            }
        }
        else -> {   // Portrait
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) { UserInfoCard(modifier = modifier, userData = userData) }

            Column(
                modifier = modifier.padding(bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) { UserCoordCard(modifier = modifier, userData = userData) }
        }
    }
}

@Composable
private fun UserCoordCard(
    modifier: Modifier,
    userData: UserData
) {
    Column(modifier = modifier) {
        ContactRow(icon = Icons.Rounded.Phone, text = userData.phone)
        ContactRow(icon = Icons.Rounded.Email, text = userData.email)
        ContactRow(icon = Icons.Rounded.Notifications, text = userData.social)
    }
}

@Composable
private fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically // Align icon and text vertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = MaterialTheme.colorScheme.primary // Use primary color for icons
        )
        Text(
            text = " $text",
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 8.dp) // Add spacing between icon and text
        )
    }
}

@Composable
private fun UserInfoCard(
    modifier: Modifier,
    userData : UserData
) {
    val fullName = "${userData.firstName} ${userData.lastName}"
    Image(
        painter = painterResource(userData.avatarImage),
        contentDescription = fullName,
        modifier = modifier
            .size(200.dp)
            .clip(RoundedCornerShape(40.dp))
    )
    Text(
        text = fullName,
        fontSize = 40.sp,
        modifier = modifier
    )
    Text(
        text = userData.title,
        fontSize = 25.sp,
        modifier = modifier
    )
    Image(
        painter = painterResource(userData.logoImage),
        contentDescription = "Company",
        modifier = modifier
            .size(450.dp, 80.dp)
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    name = "DefaultPreviewLight", wallpaper = Wallpapers.NONE,
    device = "id:pixel_8_pro", showSystemUi = true, apiLevel = 33
)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
            BusinessCardScreen(userData = UserData)
    }
}
