package au.com.codycodes.tpk.tamizhpallikoodam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WriteOnScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TouchEventView(this, null));
    }
}
