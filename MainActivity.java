package com.tradepro.daytrader;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.tradepro.daytrader.engine.SignalEngine;
import com.tradepro.daytrader.engine.SignalResult;

public class MainActivity extends AppCompatActivity {
    private TextView price, signal, confidence, trend, status;
    private final Handler handler = new Handler();
    private final SignalEngine engine = new SignalEngine();
    private double lastPrice = 132.685;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildUi();
        refreshSignal();
        handler.postDelayed(ticker, 1500);
    }

    private final Runnable ticker = new Runnable() {
        @Override public void run() {
            // Demo feed. Replace MarketDataProvider with a broker/data API for live quotes.
            lastPrice += (Math.random() - 0.48) * 0.18;
            price.setText(String.format(java.util.Locale.US, "%.3f", lastPrice));
            refreshSignal();
            handler.postDelayed(this, 1500);
        }
    };

    private void refreshSignal() {
        SignalResult r = engine.analyze();
        signal.setText(r.signal);
        signal.setTextColor(Color.parseColor(r.signal.equals("COMPRA") ? "#19E681" : r.signal.equals("VENDA") ? "#FF3B4E" : "#F5F7F6"));
        confidence.setText(r.confidence + "% de confirmação");
        trend.setText("Tendência: " + r.trend);
        status.setText("Motor: " + r.details);
    }

    private TextView tv(String text, int size) {
        TextView v = new TextView(this);
        v.setText(text);
        v.setTextColor(Color.parseColor("#F5F7F6"));
        v.setTextSize(size);
        v.setPadding(12, 8, 12, 8);
        return v;
    }

    private void buildUi() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(18, 22, 18, 18);
        root.setBackgroundColor(Color.parseColor("#07100D"));

        TextView title = tv("TradePro", 28);
        title.setTextColor(Color.parseColor("#19E681"));
        TextView sub = tv("DAY TRADER • SINAIS DE MERCADO", 12);
        root.addView(title);
        root.addView(sub);

        LinearLayout selector = new LinearLayout(this);
        selector.setPadding(0, 18, 0, 8);
        Spinner asset = new Spinner(this);
        asset.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"WINFUT", "WDOFUT", "PETR4", "VALE3", "BTC/USDT"}));
        Spinner tf = new Spinner(this);
        tf.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"1m", "5m", "15m", "30m"}));
        selector.addView(asset, new LinearLayout.LayoutParams(0, 55, 1));
        selector.addView(tf, new LinearLayout.LayoutParams(0, 55, 1));
        root.addView(selector);

        LinearLayout chart = new LinearLayout(this);
        chart.setOrientation(LinearLayout.VERTICAL);
        chart.setGravity(Gravity.CENTER);
        chart.setBackgroundResource(com.tradepro.daytrader.R.drawable.bg_panel);
        price = tv("132.685", 34);
        price.setGravity(Gravity.CENTER);
        chart.addView(tv("PREÇO / MONITORAMENTO", 11));
        chart.addView(price);
        root.addView(chart, new LinearLayout.LayoutParams(-1, 150));

        LinearLayout signalCard = new LinearLayout(this);
        signalCard.setOrientation(LinearLayout.VERTICAL);
        signalCard.setGravity(Gravity.CENTER);
        signalCard.setPadding(20, 20, 20, 20);
        signalCard.setBackgroundResource(com.tradepro.daytrader.R.drawable.bg_panel);

        signal = tv("ANALISANDO", 30);
        signal.setGravity(Gravity.CENTER);
        confidence = tv("0% de confirmação", 15);
        confidence.setGravity(Gravity.CENTER);
        trend = tv("Tendência: —", 14);
        trend.setGravity(Gravity.CENTER);
        signalCard.addView(signal);
        signalCard.addView(confidence);
        signalCard.addView(trend);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, 0, 1);
        sp.setMargins(0, 14, 0, 14);
        root.addView(signalCard, sp);

        LinearLayout buttons = new LinearLayout(this);
        Button buy = new Button(this);
        buy.setText("▲  COMPRAR");
        buy.setTextColor(Color.BLACK);
        buy.setBackgroundResource(R.drawable.bg_button_green);
        Button sell = new Button(this);
        sell.setText("▼  VENDER");
        sell.setTextColor(Color.WHITE);
        sell.setBackgroundResource(R.drawable.bg_button_red);
        buttons.addView(buy, new LinearLayout.LayoutParams(0, 62, 1));
        buttons.addView(sell, new LinearLayout.LayoutParams(0, 62, 1));
        root.addView(buttons);

        buy.setOnClickListener(v -> Toast.makeText(this, "Compra registrada em modo de demonstração.", Toast.LENGTH_SHORT).show());
        sell.setOnClickListener(v -> Toast.makeText(this, "Venda registrada em modo de demonstração.", Toast.LENGTH_SHORT).show());

        status = tv("Motor: inicializando", 12);
        status.setTextColor(Color.parseColor("#8EA39A"));
        root.addView(status);

        TextView warning = tv("Modo DEMO: conecte uma fonte de dados antes de operar com dinheiro real.", 11);
        warning.setTextColor(Color.parseColor("#8EA39A"));
        warning.setPadding(8, 16, 8, 0);
        root.addView(warning);

        setContentView(root);
    }

    @Override protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
