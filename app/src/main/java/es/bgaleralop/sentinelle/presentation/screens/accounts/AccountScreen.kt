package es.bgaleralop.sentinelle.presentation.screens.accounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.SentinelleAccount
import es.bgaleralop.sentinelle.domain.model.enums.Platform
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.presentation.screens.accounts.components.AccountCard
import es.bgaleralop.sentinelle.presentation.screens.accounts.components.AccountsHeader
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 25-06-2026
 *
 * Composable that render AccountScreen.
 */
@Composable
fun AccountScreen(viewModel: AccountViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { paddingValues ->
        Box(modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            when (val state = uiState) {
                is AccountUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

                is AccountUiState.Error -> Text(
                    text = state.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )

                is AccountUiState.Success -> AccountContent(
                    state = state,
                    tier = UserTier.PRO,
                    onAddAccount = {},
                    onDeleteClick = { id -> viewModel.removeAccount(id) }
                )
            }
        }
    }
}

@Composable
fun AccountContent(
    state: AccountUiState.Success,
    tier: UserTier,
    onAddAccount: () -> Unit,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        AccountsHeader(title = "Sentinelle", tier = tier)

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            "TUS CUENTAS VINCULADAS",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.padding(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(state.accounts) { account ->
                AccountCard(account = account, onDeleteClick = onDeleteClick)
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = { onAddAccount() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isMaxAccountsReached
        ) {
            Text(
                text = "+ Vincular Nueva Cuenta",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(0.4f))

        HorizontalDivider()
        Text(
            text = "Estado del Plan de Creador",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = getTierText(tier),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun getTierText(tier: UserTier): String {
    return when (tier) {
        UserTier.FREE -> """
            Acceso a 1 cuenta por plataforma.
            Filtrado de 200 comentarios por día.
        """.trimIndent()

        UserTier.PRO -> """
            Acceso a 5 cuentas.
            Filtrado de comentarios sin límite.
        """.trimIndent()

        UserTier.ENTERPRISE -> """
            Acceso a 50 cuentas.
            Filtrado de comentarios sin límite.
        """.trimIndent()
    }
}

@Preview(showBackground = true, name = "Test A - Dark UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun AccountScreenDarkModePreview() {
    SentinelleTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AccountContent(
                state = fakeSuccessState,
                tier = UserTier.PRO,
                onAddAccount = {},
                onDeleteClick = {})
        }
    }
}

@Preview(showBackground = true, name = "Test B - Light UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun AccountScreenLightModePreview() {
    SentinelleTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AccountContent(
                state = fakeSuccessState,
                tier = UserTier.FREE,
                onAddAccount = {},
                onDeleteClick = {})
        }
    }
}

private val fakeAccounts = listOf(
    SentinelleAccount(
        platform = Platform.TIKTOK,
        accountHandle = "@streamer_gamer",
        accessToken = "1",
        refreshToken = "4",
        accessTokenExpiration = 132349,
        refreshTokenExpiration = 123123123,
        id = 1,
        isActive = true
    ),
    SentinelleAccount(
        platform = Platform.INSTAGRAM,
        accountHandle = "@agencia_marketing",
        accessToken = "2",
        refreshToken = "5",
        accessTokenExpiration = 123443,
        refreshTokenExpiration = 24234234,
        id = 2,
        isActive = true
    )
)

private val fakeSuccessState = AccountUiState.Success(
    accounts = fakeAccounts,
    accountCount = 2,
    isMaxAccountsReached = false
)