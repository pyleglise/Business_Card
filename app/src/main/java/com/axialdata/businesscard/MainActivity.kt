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
import androidx.compose.ui.graphics.painter.Painter
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
                val colors = MaterialTheme.colorScheme
                Surface(
                    color = colors.surface,
                    modifier = Modifier
                        .fillMaxSize()
            )  {


                    BusinessCard(
                        firstName = UserData.firstName,
                        lastName = UserData.lastName,
                        title = UserData.title,
                        email = UserData.email,
                        phone = UserData.phone,
                        social = UserData.social
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(
    firstName: String,
    lastName: String,
    title: String,
    email: String,
    phone: String,
    social: String,
    modifier: Modifier = Modifier
){
    val avatarImage = painterResource(R.drawable.image_peewai)
    val logoImage = painterResource(R.drawable.logo_axialdata_v2_noir_banniere_trans)
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = modifier.padding(0.dp,40.dp,0.dp,0.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    UserInfoCard(avatarImage, firstName, lastName, modifier, title, logoImage)
                }
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom
                )
                {
                    UserCoordCard(modifier, phone, email, social)
//                    Spacer(modifier = Modifier.height(40.dp))
                }

            }
        }
        else -> {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                UserInfoCard(avatarImage, firstName, lastName, modifier, title, logoImage)
            }
            Column(
                modifier = modifier.padding(0.dp, 0.dp, 0.dp, 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            )
            {
                UserCoordCard(modifier, phone, email, social)
//                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }


}

@Composable
private fun UserCoordCard(
    modifier: Modifier,
    phone: String,
    email: String,
    social: String
) {
    Column(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Absolute.Left,
            //            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Rounded.Phone, contentDescription = "phone")

            Text(
                text = " $phone",
                fontSize = 24.sp,
                modifier = modifier
            )

        }
        Row(
            modifier = modifier,
            //            horizontalArrangement = Arrangement.Center,
            //            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Rounded.Email, contentDescription = "email")

            Text(
                text = " $email",
                fontSize = 24.sp,
                modifier = modifier
            )

        }

        Row(
            modifier = modifier,
            //            horizontalArrangement = Arrangement.Center,
            //            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Rounded.Notifications, contentDescription = "social")

            Text(
                text = " $social",
                fontSize = 24.sp,
                modifier = modifier
            )

        }
    }
}

@Composable
private fun UserInfoCard(
    avatarImage: Painter,
    firstName: String,
    lastName: String,
    modifier: Modifier,
    title: String,
    logoImage: Painter
) {
    Image(
        painter = avatarImage,
        contentDescription = "$firstName $lastName",
        modifier = modifier
            .size(200.dp)
            .clip(RoundedCornerShape(40.dp))
    )
    Text(
        text = "$firstName $lastName",
        fontSize = 40.sp,
        modifier = modifier
    )
    Text(
        text = title,
        fontSize = 25.sp,
        modifier = modifier
    )
    Image(
        painter = logoImage,
        contentDescription = "axialdata",
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
        val colors = MaterialTheme.colorScheme
        Surface(
            color = colors.surface,
            modifier = Modifier
                .fillMaxSize()
        )  {
            BusinessCard(
                firstName = UserData.firstName,
                lastName = UserData.lastName,
                title = UserData.title,
                email = UserData.email,
                phone = UserData.phone,
                social = UserData.social
            )
        }

    }
}
