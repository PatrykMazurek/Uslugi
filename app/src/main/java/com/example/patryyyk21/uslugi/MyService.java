package com.example.patryyyk21.uslugi;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;
import java.util.Random;

public class MyService extends IntentService {

    public MyService()
    {
        super("Test Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId){
        return super.onStartCommand(intent, flag, startId);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Tworzenie usługi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Zakończenie usługi", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try{
                Thread.sleep(3000);
                Random rand = new Random();
                int r = rand.nextInt();
                Message("Zwrócono numer " + String.valueOf(r));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void Message(String message){
        Toast.makeText(getApplicationContext(),""+ message, Toast.LENGTH_SHORT ).show();
    }
}
