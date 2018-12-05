package com.example.patryyyk21.uslugi;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
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
                UpdateUI("Zwrócono numer " + String.valueOf(r));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void UpdateUI(String message){
        Intent intent = new Intent("com.example.patryyyk21.uslugi.MyService");
        intent.putExtra("value", message);
        intent.putExtra("code", Activity.RESULT_OK);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void Message(String message){
        Intent intent = new Intent(this, MainActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Usługa")
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        Random rand = new Random();
        int r = rand.nextInt(10);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(r, notification);

    }
}
