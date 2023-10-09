package com.example.natsuyasai.ui;

import static com.example.natsuyasai.ui.ActivityConstants.EVENT_ID;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.natsuyasai.R;
import com.example.natsuyasai.ui.EventActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class EventCreatedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final Intent intent = getIntent();
        final String eventId = intent.getStringExtra(EVENT_ID);
        Log.i(EventCreatedActivity.class.toString(), eventId);

        setContentView(R.layout.activity_eventcreated);

        Button createYearButton = findViewById(R.id.button_to_event);
        // lambda式
        createYearButton.setOnClickListener(v -> {
            final Intent nextIntent = new Intent(getApplication(), EventActivity.class);
            nextIntent.putExtra(EVENT_ID, eventId);
            startActivity(nextIntent);
            overridePendingTransition(R.anim.enter_right, R.anim.exit_left);
        });

        String scheme = getString(R.string.qr_scheme);
        String host = getString(R.string.qr_host);
        String url = scheme + "://" + host + "/" + eventId; // "scheme://host/:event_id"
//QRコード画像の大きさを指定(pixel)
        int size = 500;

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

//            Map<EncodeHintType, Object> hints = new HashMap();

            //文字コードの指定
//            hints.put(EncodeHintType.CHARACTER_SET, "shiftjis");

            //誤り訂正レベルを指定
            //L 7%が復元可能
            //M 15%が復元可能
            //Q 25%が復元可能
            //H 30%が復元可能
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
//
//            //QRコードのバージョンを指定
//            hints.put(EncodeHintType.QR_VERSION, 20);

            Bitmap bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, size, size);

            ImageView imageViewQrCode = (ImageView) findViewById(R.id.QRImageView);
            imageViewQrCode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            throw new AndroidRuntimeException("Barcode Error.", e);
        }
    }


}
