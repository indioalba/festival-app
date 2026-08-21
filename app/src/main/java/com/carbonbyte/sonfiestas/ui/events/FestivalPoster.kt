package com.carbonbyte.sonfiestas.ui.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carbonbyte.sonfiestas.R
import com.carbonbyte.sonfiestas.ui.theme.FestivalTheme

@Composable
fun FestivalPoster(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_fiestas_agosto2026),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.poster_title),
                color = Color(0xFFC62828),
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.poster_dates),
                color = Color(0xFFA67C00),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(y = (-4).dp),
            )
            Text(
                text = stringResource(id = R.string.poster_subtitle),
                color = Color(0xFF5D4037),
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp),
            )
        }

        Text(
            text = "TRANS\nVERBERA\nCION",
            color = Color(0xFFC62828),
            fontSize = 80.sp,
            lineHeight = 70.sp,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .offset(y = 40.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FestivalPosterPreview() {
    FestivalTheme {
        FestivalPoster()
    }
}
