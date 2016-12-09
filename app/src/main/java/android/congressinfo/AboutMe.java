package android.congressinfo;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AboutMe extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("About Me");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.myBlack));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.about_me);
    }
}
