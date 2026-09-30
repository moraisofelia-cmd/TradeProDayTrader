package com.tradepro.daytrader.engine;

public class SignalResult {
    public final String signal;
    public final int confidence;
    public final String trend;
    public final String details;

    public SignalResult(String signal, int confidence, String trend, String details) {
        this.signal = signal;
        this.confidence = confidence;
        this.trend = trend;
        this.details = details;
    }
}
