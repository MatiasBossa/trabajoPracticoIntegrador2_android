package com.tp2_matias_bossa;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

public class Reading extends Service implements  Runnable {

    //Contructor
    public Reading() {}

    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service
        return null;
    }

    /*
     * @params intent - un objeto de tipo Intent que se indico en la llamada
     * @params flags - indica como iniciar el servicio, este valor puede ser 0
     * @params startId - Un entero unico que representa la solicitud de inicio especifico
     */
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            while (true) {
                this.getAllMsg();
                Thread.sleep(9000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    /*
    * @return  Retorna todos los mensajes con nro de contacto, mensaje, id y fecha
    */
    public void getAllMsg(){
        Uri message = Uri.parse("content://sms/");
        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(message, null, null, null);
        
        int totalSMS = c.getCount();
        if(c.moveToFirst()){
            for(int i = 0; i < totalSMS; i++){
                Log.d("SMS",
                        "Contant number: " + c.getString(c.getColumnIndexOrThrow("address"))
                        + "\n"
                        + "msg: " + c.getString(c.getColumnIndexOrThrow("body"))
                        + "\n"
                        + "id: " + c.getString(c.getColumnIndexOrThrow("_id"))
                        + "\n"
                        + "date: " + c.getString(c.getColumnIndexOrThrow("date"))
                );
                c.moveToNext();
            }
        }
        c.close();
    }

    @Override
    public void run() {

    }


}
