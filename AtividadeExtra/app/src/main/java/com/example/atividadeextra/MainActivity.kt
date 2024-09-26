package com.example.atividadeextra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Munchkin()
        }
    }
}

@Composable
fun Munchkin() {
    var jogadores = remember { mutableStateListOf(Jogador("Jogador 1"), Jogador("Jogador 2"),
        Jogador("Jogador 3"), Jogador("Jogador 4"), Jogador("Jogador 5"), Jogador("Jogador 6")) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Para cada jogador na lista, cria um CartaoJogador
        items(jogadores) { jogador ->
            CartaoJogador(jogador)
            Spacer(modifier = Modifier.height(16.dp)) // Espaçamento entre os cartões
        }
    }
}

@Composable
fun CartaoJogador(jogador: Jogador) {
    var nome by remember { mutableStateOf(jogador.nome) }
    var level by remember { mutableStateOf(jogador.level) }
    var bonus by remember { mutableStateOf(jogador.bonus) }
    var modificadores by remember { mutableStateOf(jogador.modificadores) }

    val poderTotal = level + bonus + modificadores

    // Layout da interface
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de texto para o nome do jogador
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Jogador") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Controle do nível do jogador
        Row(verticalAlignment = Alignment.CenterVertically) {

            Text("Level: ")

            Button(onClick = { if (level > 1) level-- }) {
                Text("-")
            }

            Text("$level")

            Button(onClick = { if (level < 10) level++ }) {
                Text("+")
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        // Controle do bônus de equipamento
        Row(verticalAlignment = Alignment.CenterVertically) {

            Text("Bônus de Equipamento: ")

            Button(onClick = { bonus-- }) {
                Text("-")
            }

            Text("$bonus")

            Button(onClick = { bonus++ }) {
                Text("+")
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        // Controle dos modificadores
        Row(verticalAlignment = Alignment.CenterVertically) {

            Text("Modificadores: ")

            Button(onClick = { modificadores-- }) {
                Text("-")
            }

            Text("$modificadores")

            Button(onClick = { modificadores++ }) {
                Text("+")
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        // Exibir o poder total do jogador
        Text("Poder Total: $poderTotal")

    }

}