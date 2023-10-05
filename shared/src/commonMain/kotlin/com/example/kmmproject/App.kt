import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.games.UiTest
import com.example.kmmproject.getPlatform
import com.example.network.Game
import com.example.network.GameService
import com.example.network.ShortGame
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun App(click: (Int) -> Unit) {
    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello, World!") }
        Column(
            Modifier.fillMaxWidth().background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val props = remember { mutableStateListOf<ShortGame>() }


            LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {
                items(props, key = { it.id }) {
                    Column(modifier = Modifier.clickable { click.invoke(it.id) }) {
                        Text(text = it.title, modifier = Modifier)
                        KamelImage(
                            modifier = Modifier.fillMaxSize(100f),
                            resource = asyncPainterResource(data = it.thumbnail),
                            contentDescription = it.title,
                        )

                    }

                }
            }
            Button(onClick = {
                greetingText = "Hello, ${getPlatform().name}"
            }) {
                Text(greetingText)
            }
            UiTest()
            val koin = getKoin()
            LaunchedEffect(key1 = Unit) {
                greetingText = "Hello props, ${getPlatform().name}"
                props.addAll(koin.get<GameService>().games())
            }
        }
    }
}

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
            App() { navigator.navigate("/details/$it") }
        }
        scene("/details/{id}") { entry ->
            val game = remember { mutableStateOf<Game?>(null) }
            Column {
                val id: Int = entry.path<Int>("id") ?: 0
                Button(
                    modifier = Modifier.size(24.dp),
                    onClick = navigator::goBack
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = ""
                    )
                }
                Column {
                    Text(text = game.value?.title ?: "", modifier = Modifier)
                    KamelImage(
                        modifier = Modifier.fillMaxSize(100f),
                        resource = asyncPainterResource(data = game.value?.thumbnail ?: ""),
                        contentDescription = game.value?.title,
                    )

                }
                val koin = getKoin()
                LaunchedEffect(key1 = Unit) {
                    game.value = koin.get<GameService>().game(id)
                }
            }

        }
    }
}