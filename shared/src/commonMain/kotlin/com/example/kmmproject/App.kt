import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gamedetails.GameDetails
import com.example.games.GamesList
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun Apps() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Enter()
    }
}

@Composable
fun Enter() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/home"
    ) {
        scene("/home", navTransition = NavTransition()) {
            GamesList { navigator.navigate("/details/$it") }
        }
        scene("/details/{id}") { entry ->
            GameDetails(entry.path<Int>("id")!!, navigator)
        }
    }
}