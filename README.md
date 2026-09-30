# TradePro Day Trader — Android

Projeto Android inicial para um aplicativo de day trade com interface de sinais de COMPRA/VENDA.

## O que já existe
- Interface mobile em modo retrato.
- Seleção de ativo e timeframe.
- Painel de preço.
- Sinal COMPRA / VENDA / AGUARDAR.
- Percentual de confirmação.
- Botões de compra e venda em modo demonstração.
- Motor de sinais separado em `SignalEngine.java`.
- Estrutura pronta para conectar uma API de cotações.
- GitHub Actions para gerar APK de debug.

## Importante
Este projeto **não promete 100% de assertividade**. O motor atual é propositalmente um simulador para demonstrar a interface e a arquitetura. Não use os sinais atuais para operar dinheiro real.

Para uma versão real, conecte uma fonte de dados de mercado e implemente:
1. candles OHLCV reais;
2. EMA/SMA;
3. RSI;
4. MACD;
5. ATR;
6. volume;
7. suporte e resistência;
8. confirmação de candle;
9. gerenciamento de risco;
10. backtest e validação fora da amostra.

## Abrir no Android Studio
1. Extraia o ZIP.
2. Abra a pasta `TradeProDayTrader` no Android Studio.
3. Aguarde o Gradle sincronizar.
4. Execute em um celular Android ou emulador.
5. Para gerar APK: `Build > Build APK(s)`.

## Gerar APK pelo GitHub
1. Crie um repositório no GitHub.
2. Envie todos os arquivos deste projeto.
3. Entre em `Actions`.
4. Execute o workflow `Build Android APK`.
5. O APK ficará disponível como artifact.

## Caminho do APK no build local
`app/build/outputs/apk/debug/app-debug.apk`

## Próxima etapa recomendada
Criar um `MarketDataProvider` real para receber cotações da corretora/API escolhida. Depois disso, substituir o `SignalEngine` de demonstração por um motor de análise com backtest e métricas reais.
