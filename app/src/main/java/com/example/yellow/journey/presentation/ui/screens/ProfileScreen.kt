package com.example.yellow.journey.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.yellow.R
import com.example.yellow.journey.presentation.ui.components.ProfileItem

@Composable
fun ProfileScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Distributor 1",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = null,
                        tint = Color(0xFF333333),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "27640100000",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_print), // If you have an ID icon
                        contentDescription = null,
                        tint = Color(0xFF333333),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "DIST1",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ProfileItem(
            iconId = R.drawable.ic_sales,
            title = "Sales Report",
            onClick = {
                Toast.makeText(context, "Not Implemented Yet!", Toast.LENGTH_SHORT).show()
            }
        )
        ProfileItem(
            iconId = R.drawable.ic_history,
            title = "Transaction History",
            onClick = {
                Toast.makeText(context, "Not Implemented Yet!", Toast.LENGTH_SHORT).show()
            }
        )
        ProfileItem(
            iconId = R.drawable.ic_deposit,
            title = "Deposit History",
            onClick = {
                Toast.makeText(context, "Not Implemented Yet!", Toast.LENGTH_SHORT).show()
            }
        )
        ProfileItem(
            iconId = R.drawable.ic_incentive,
            title = "Incentive History",
            onClick = {
                Toast.makeText(context, "Not Implemented Yet!", Toast.LENGTH_SHORT).show()
            }
        )
        ProfileItem(
            iconId = R.drawable.ic_hierarchy,
            title = "My Hierarchy",
            onClick = {
                Toast.makeText(context, "Not Implemented Yet!", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
