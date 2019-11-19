package com.nobre.jsoup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       new Thread(new BuscaCotacoesRunnable()).start(); // deve ser em outra thread
        
    }

    public class BuscaCotacoesRunnable implements Runnable{

        @Override
        public void run() {
            Document doc;

            //https://www.bloomberg.com/quote/PETRT35:BZ
            try {
                doc = Jsoup.connect("https://economia.uol.com.br/cotacoes/bolsas/bvsp-bovespa/?indice=.BVSP").get();
                Log.d("titulo", "titulo = " + doc.title());
                Elements trs = doc.select("td.up");
                Log.d("lista", "tamanho da lista = " + trs.text());



                for (Element tr : trs) {

                    Log.d("teste", "option = " + tr.text());
                    /*Elements tds = tr.select("td.text-left");
                    for(Element td : tds){
                        Log.d("td", "Item td = " + td.text());
                    }*/
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
