package me.haroldmartin.golwallpaper

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.haroldmartin.golwallpaper.ui.ColorPicker
import me.haroldmartin.golwallpaper.ui.PatternPicker
import me.haroldmartin.golwallpaper.ui.theme.COLOR_SCHEME
import me.haroldmartin.golwallpaper.ui.theme.XXLARGE

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(XXLARGE),
        verticalArrangement = Arrangement.spacedBy(XXLARGE),
    ) {
        Text(stringResource(R.string.freeze_alert))
        ColorPicker(
            label = stringResource(id = R.string.fg_color),
            selectedColor = uiState.fgColor,
        ) { color ->
            viewModel.setFgColor(context, color)
        }
        ColorPicker(
            label = stringResource(id = R.string.bg_color),
            selectedColor = uiState.bgColor,
        ) { color ->
            viewModel.setBgColor(context, color)
        }
        PatternPicker { pattern ->
            viewModel.reset(context, pattern)
        }
        Button(
            modifier = Modifier.border(1.dp, COLOR_SCHEME.secondary),
            onClick = { viewModel.saveNextStep(context) },
        ) {
            Text(stringResource(R.string.next_step))
        }
        Button(
            modifier = Modifier.border(1.dp, COLOR_SCHEME.secondary),
            onClick = { viewModel.openIssues(context) },
        ) {
            Text(stringResource(R.string.report_issue))
        }
    }
}
