package com.example.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static java.lang.Thread.*;

public class MainActivity extends AppCompatActivity {

    private Button botaoIniciar;
    private int numero;
    private Handler handler = new Handler(); // possivel enviar para uma thread mensagens, apartir disso poderá executar trechos de codigos executaveis.
    private boolean pararExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoIniciar = findViewById(R.id.buttonIniciar);




        //Não é recomendável executar uma Thread na UI Principal

    }

    public void iniciarThread(View view){

        //MyThread thread = new MyThread();
        //thread.start();
        pararExecucao = false;
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();

    }

    public void pararThread(View view){
        pararExecucao = true;
    }

    //2-Alternativa - com Runnable
    class MyRunnable implements Runnable{

        @Override
        public void run() {
            for (int i=0; i<=15; i++){

                if(pararExecucao)
                    return;

                numero = i;
                Log.d("Thread","contador: " + i);
                //botaoIniciar.setText("contador: " + i); // nao é possivel alterar objeto na thread principal UI

                //deve usar o metodo abaixo para alterar objeto na thread principal
                /*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText("contador: " + numero);
                    }
                });*/
// ou...
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText("contador: " + numero);
                    }
                });

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //1-Alternativa criar classe auxiliar que estende de thread
    class MyThread extends Thread {

        @Override
        public void run(){
            for (int i=0; i<=15; i++){
                Log.d("Thread","contador: " + i);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
