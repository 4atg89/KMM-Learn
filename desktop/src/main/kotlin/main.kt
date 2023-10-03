import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.kmmproject.MainJvm
import androidx.compose.ui.window.Window
import androidx.compose.material.Text
import com.example.kmmproject.appModule
import moe.tlaster.precompose.PreComposeWindow
import org.koin.core.context.startKoin
import org.w3c.dom.Text

fun main() = application {
    startKoin { modules(appModule()) }
//    Window(onCloseRequest = ::exitApplication) {
//        Text(text = "Test")
//    }
    PreComposeWindow(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(width = 300.dp, height = 300.dp)
    ) {
        MainJvm()
//        Text(text = "Test")
    }
}