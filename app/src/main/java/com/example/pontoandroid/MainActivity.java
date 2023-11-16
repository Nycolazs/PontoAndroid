package com.example.pontoandroid;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); // Defina o modo de cache conforme necessário
        webView.clearCache(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");


        initWebView();
    }

    private void initWebView() {
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Habilita a execução de JavaScript

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Executa um script JavaScript após a página ser carregada completamente
                webView.evaluateJavascript(
                        "javascript:(function() { " +
                                codigoJs() +
                                "})();", null);
            }
        });

        webView.loadUrl("https://login.lg.com.br/login/mvsistemas"); // Substitua com o URL do site externo
    }

    // Adicione este método para garantir que o botão "Voltar" funcione corretamente dentro do WebView
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public String codigoJs(){
        return "document.getElementById('Login').value = '';"+
                "document.getElementsByClassName('login__botao-continuar btn btn-primary btn-sm float-end')[0].click();"+
                "setTimeout(function(){"+
                "    document.getElementById('Senha').value = '';"+
                "    document.getElementsByClassName('login__botao-continuar btn btn-primary btn-sm float-end')[0].click();"+
                "}, 1500);";
    }
}