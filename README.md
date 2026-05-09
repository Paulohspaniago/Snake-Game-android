# 🐍 Snake Game Android

Jogo da cobrinha clássico desenvolvido para Android como projeto final da disciplina de Programação para Dispositivos Móveis (PDM).

## 📱 Sobre o Projeto

O jogo é construído do zero com uma `View` customizada (`GameView`) que desenha diretamente no canvas do Android. A cobra se move automaticamente e o jogador a controla por gestos de swipe na tela.

## ✨ Funcionalidades

- **Controle por swipe** — deslize para cima, baixo, esquerda ou direita para mudar a direção
- **Pontuação em tempo real** — exibida na tela durante a partida
- **Pausar/Retomar** — botão dedicado na tela de jogo
- **Trilha sonora** — música arcade em loop durante a partida
- **Game Over** — tela com a pontuação final ao colidir com parede ou com a própria cobra
- **Ranking persistente** — top pontuações salvas localmente com Room (SQLite)
- **Limpar ranking** — opção para zerar o histórico de pontuações

## 🏗️ Arquitetura

```
MainActivity        → Tela inicial com ranking e botão jogar
GameActivity        → Tela do jogo com GameView, placar e pause
GameOverActivity    → Tela de fim de jogo com pontuação
RankingActivity     → Tela de ranking detalhado
GameView            → View customizada com o loop do jogo desenhado no canvas
AppDatabase         → Banco de dados Room
PontuacaoDao        → Operações de insert/query no banco
```

## 🛠️ Tecnologias

- **Java** — linguagem principal
- **Android SDK** — compileSdk 35, minSdk 24
- **Room (SQLite)** — persistência do ranking
- **Canvas API** — renderização customizada do jogo
- **GestureDetector** — detecção de swipes
- **MediaPlayer** — música de fundo

## 🚀 Como Rodar

1. Clone o repositório:
   ```bash
   git clone https://github.com/Paulohspaniago/Snake-Game-android.git
   ```
2. Abra no **Android Studio**
3. Sincronize o Gradle
4. Rode em um emulador ou dispositivo físico (Android 7.0+)

## 👤 Autor

**Paulo Henrique Soares Paniago**  
[GitHub](https://github.com/Paulohspaniago)
