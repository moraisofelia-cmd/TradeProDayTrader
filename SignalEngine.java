package com.tradepro.daytrader.engine;

import java.util.Random;

/**
 * Prototype signal engine.
 *
 * The current implementation is deliberately a DEMO engine. It does not claim
 * 100% accuracy and must not be treated as a trading guarantee.
 *
 * Production version should receive real OHLCV candles and calculate:
 * EMA/SMA, RSI, MACD, ATR, volume, support/resistance and candle confirmation,
 * followed by backtesting and out-of-sample validation.
 */
public class SignalEngine {
    private final Random random = new Random();

    public SignalResult analyze() {
        int trend = random.nextInt(3) - 1;       // -1, 0, +1
        int momentum = random.nextInt(3) - 1;
        int volume = random.nextInt(3) - 1;
        int score = trend + momentum + volume;

        String signal;
        String direction;
        if (score >= 2) {
            signal = "COMPRA";
            direction = "ALTA";
        } else if (score <= -2) {
            signal = "VENDA";
            direction = "BAIXA";
        } else {
            signal = "AGUARDAR";
            direction = "LATERAL";
        }

        int confidence = 55 + Math.abs(score) * 12 + random.nextInt(8);
        return new SignalResult(signal, Math.min(confidence, 89), direction,
                "EMA + momentum + volume (demo)");
    }
}
