package es.bgaleralop.sentinelle.presentation.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.DetailedCommentLog
import es.bgaleralop.sentinelle.domain.model.ModerationLog
import es.bgaleralop.sentinelle.domain.model.SentinelleAccount
import es.bgaleralop.sentinelle.domain.model.SparklinePoint
import es.bgaleralop.sentinelle.domain.model.enums.Platform
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.presentation.screens.commons.BottomNavigationBar
import es.bgaleralop.sentinelle.presentation.screens.commons.SmallTopAppBar
import es.bgaleralop.sentinelle.presentation.screens.dashboard.components.CommentsDashboardCard
import es.bgaleralop.sentinelle.presentation.screens.dashboard.components.ModeratedCommentCard
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 25-06-2026
 *
 * Composable que renderiza la pantalla Dashboard.
 */

@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val uiState by viewModel.uiSate.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SmallTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Sentinelle",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(
                            modifier = Modifier
                                .width(16.dp)
                                .weight(0.5f)
                        )
                        Surface(
                            modifier = Modifier.padding(bottom = 4.dp, end = 16.dp),
                            color = Color(0xFF00C9A7).copy(alpha = 0.15f),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "Sistema Activo",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = Color(0xFF00C9A7)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = { BottomNavigationBar() },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val state = uiState) {
                is DashboardUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

                is DashboardUiState.Error -> Text(
                    text = state.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )

                is DashboardUiState.Success -> DashboardContent(state = state)
            }
        }
    }
}

@Composable
fun DashboardContent(state: DashboardUiState.Success) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 1. Tarjeta de Conteos y Sparkline.
        CommentsDashboardCard(state = state, sparks = state.sparklineData)

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Título de Moderacion reciente.
        Text(text = "Moderacion Reciente", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // 3. Listado de Comentarios Moderados
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(state.recentModerationLogs) { log ->
                ModeratedCommentCard(detailedLog = log)
            }
        }
    }
}


@Preview(showBackground = true, name = "Test A - Dark UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun DashboardContentDarkModePreview() {
    SentinelleTheme(darkTheme = true) {
        // Envolvemos en una Surface para que tome el color de fondo del tema oscuro automáticamente
        Surface(color = MaterialTheme.colorScheme.background) {
            DashboardContent(state = fakeDashboardSuccessState)
        }
    }
}

@Preview(showBackground = true, name = "Test B - Light UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun DashboardContentLightModePreview() {
    SentinelleTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            DashboardContent(state = fakeDashboardSuccessState)
        }
    }
}

// --- BANCO DE DATOS MOCKEADOS DE ALTA FIDELIDAD ---
private val fakeSparklineData = listOf(
    SparklinePoint(hour = 8, count = 2),
    SparklinePoint(hour = 10, count = 5),
    SparklinePoint(hour = 12, count = 9),
    SparklinePoint(hour = 14, count = 14) // Pico actual coincidente con el contador
)

private val fakeInstagramAccount = SentinelleAccount(
    id = 1,
    platform = Platform.INSTAGRAM,
    accountHandle = "@ig_spammer_x",
    accessToken = "mock_token_1",
    refreshToken = "mock_refresh_token_1",
    accessTokenExpiration = 234234,
    refreshTokenExpiration = 234234,
    isActive = true
)

private val fakeTikTokAccount = SentinelleAccount(
    id = 2,
    platform = Platform.TIKTOK,
    accountHandle = "@troll_abc",
    accessToken = "mock_token_2",
    refreshToken = "mock_refresh_token_2",
    accessTokenExpiration = 2423434,
    refreshTokenExpiration = 44443,
    isActive = true
)

private val fakeLogs = listOf(
    DetailedCommentLog(
        log = ModerationLog(
            accountId = 1,
            commentId = "msg_001",
            authorUsername = "bot_seller",
            commentText = "¡Increíble oferta! Te vendo seguidores muy baratos. Escríbeme!",
            timestamp = System.currentTimeMillis(),
            isSpam = true,
            matchedWord = "seguidores",
            actionTaken = "FLAGGED"
        ),
        account = fakeInstagramAccount,
        matchedWord = "seguidores"
    ),
    DetailedCommentLog(
        log = ModerationLog(
            accountId = 2,
            commentId = "msg_002",
            authorUsername = "hater_99",
            commentText = "Eres lo peor, ojalá te pase lo peor. Solo buscas atención.",
            timestamp = System.currentTimeMillis() - 1000 * 60 * 27,
            isSpam = false,
            matchedWord = "peor",
            actionTaken = "BLOCKED"
        ),
        account = fakeTikTokAccount,
        matchedWord = "peor"
    )
)

private val fakeDashboardSuccessState = DashboardUiState.Success(
    userTier = UserTier.PRO,
    checksToday = 14, // Número exacto de tu diseño
    sparklineData = fakeSparklineData,
    recentModerationLogs = fakeLogs,
    accountCount = 2
)