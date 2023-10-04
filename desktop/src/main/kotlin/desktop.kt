import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.kmmproject.MainJvm
import com.example.kmmproject.appModule
import moe.tlaster.precompose.PreComposeWindow
import org.koin.core.context.startKoin

fun main() = application {
    startKoin { modules(appModule()) }
    PreComposeWindow(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(/*width = 300.dp, height = 300.dp*/)
    ) {
        MainJvm()
    }
}