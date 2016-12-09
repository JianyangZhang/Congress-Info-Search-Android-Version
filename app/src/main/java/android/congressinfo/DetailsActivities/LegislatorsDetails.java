package android.congressinfo.DetailsActivities;


import android.congressinfo.MyTools;
import android.congressinfo.R;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;


public class LegislatorsDetails extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Legislator Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.myBlack));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_legislators_details);
        Intent getDetails = getIntent();

        final String id = getDetails.getStringExtra("id");
        final ImageButton legislatorDetails_star = (ImageButton) findViewById(R.id.legislatorDetails_star);
        if (MyTools.getKV(this, id).equals("*EMPTY-KV")) {
            legislatorDetails_star.setImageResource(android.R.drawable.btn_star_big_off);
            legislatorDetails_star.setTag("off");
        } else {
            legislatorDetails_star.setImageResource(android.R.drawable.btn_star_big_on);
            legislatorDetails_star.setTag("on");
        }

        legislatorDetails_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (legislatorDetails_star.getTag().equals("off")) {
                    legislatorDetails_star.setImageResource(android.R.drawable.btn_star_big_on);
                    legislatorDetails_star.setTag("on");
                    MyTools.saveKV(LegislatorsDetails.this, id, "=)");
                } else {
                    legislatorDetails_star.setImageResource(android.R.drawable.btn_star_big_off);
                    legislatorDetails_star.setTag("off");
                    MyTools.deleteKV(LegislatorsDetails.this, id);
                }
            }
        });

        ImageView legislatorDetails_photo = (ImageView) findViewById(R.id.legislatorDetails_photo);
        Picasso.with(this).load(getDetails.getStringExtra("photo")).fit().into(legislatorDetails_photo);

        ImageView legislatorDetails_partyImg = (ImageView) findViewById(R.id.legislatorDetails_partyImg);
        TextView legislatorDetails_party = (TextView) findViewById(R.id.legislatorDetails_party);
        if (getDetails.getStringExtra("party").equals("R")) {
            legislatorDetails_partyImg.setImageResource(R.drawable.r);
            legislatorDetails_party.setText("Republican");
        } else if (getDetails.getStringExtra("party").equals("D")) {
            legislatorDetails_partyImg.setImageResource(R.drawable.d);
            legislatorDetails_party.setText("Democrat");
        } else {
            legislatorDetails_partyImg.setImageResource(R.drawable.i);
            legislatorDetails_party.setText("Independent");
        }

        TextView legislatorDetails_name = (TextView) findViewById(R.id.legislatorDetails_name);
        legislatorDetails_name.setText(getDetails.getStringExtra("name"));

        TextView legislatorDetails_email = (TextView) findViewById(R.id.legislatorDetails_email);
        legislatorDetails_email.setText(getDetails.getStringExtra("email"));

        TextView legislatorDetails_chamber = (TextView) findViewById(R.id.legislatorDetails_chamber);
        legislatorDetails_chamber.setText(getDetails.getStringExtra("chamber"));

        TextView legislatorDetails_contact = (TextView) findViewById(R.id.legislatorDetails_contact);
        legislatorDetails_contact.setText(getDetails.getStringExtra("contact"));

        TextView legislatorDetails_start_term = (TextView) findViewById(R.id.legislatorDetails_start_term);
        String start_term_iso8601 = getDetails.getStringExtra("start_term");
        String start_term_format = MyTools.formatDate(start_term_iso8601);
        legislatorDetails_start_term.setText(start_term_format);

        TextView legislatorDetails_end_term = (TextView) findViewById(R.id.legislatorDetails_end_term);
        String end_term_iso8601 = getDetails.getStringExtra("end_term");
        String end_term_format = MyTools.formatDate(end_term_iso8601);
        legislatorDetails_end_term.setText(end_term_format);

        ProgressBar legislatorDetails_bar = (ProgressBar) findViewById(R.id.legislatorDetails_bar);
        legislatorDetails_bar.setMax(100);

        try {
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(start_term_iso8601);
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(end_term_iso8601);
            int percentagePassed = 100 - MyTools.getPercentageLeft(start, end);
            legislatorDetails_bar.setProgress(percentagePassed);
            String percentagePassed_String =  Integer.toString(percentagePassed) + "%";
            TextView legislatorDetails_percent = (TextView) findViewById(R.id.legislatorDetails_percent);
            legislatorDetails_percent.setText(percentagePassed_String);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView legislatorDetails_office = (TextView) findViewById(R.id.legislatorDetails_office);
        legislatorDetails_office.setText(getDetails.getStringExtra("office"));

        TextView legislatorDetails_state = (TextView) findViewById(R.id.legislatorDetails_state);
        legislatorDetails_state.setText(getDetails.getStringExtra("state"));

        TextView legislatorDetails_fax = (TextView) findViewById(R.id.legislatorDetails_fax);
        legislatorDetails_fax.setText(getDetails.getStringExtra("fax"));

        TextView legislatorDetails_birthday = (TextView) findViewById(R.id.legislatorDetails_birthday);
        String birthday_iso8601 = getDetails.getStringExtra("birthday");
        String birthday_format = MyTools.formatDate(birthday_iso8601);
        legislatorDetails_birthday.setText(birthday_format);

        ImageButton legislatorDetails_f = (ImageButton) findViewById(R.id.legislatorDetails_f);
        final String facebook_id = getDetails.getStringExtra("facebook_id");
        if (!facebook_id.equals("N.A")) {
            legislatorDetails_f.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://facebook.com/" + facebook_id));
                    startActivity(intent);
                }
            });
        } else {
            legislatorDetails_f.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(LegislatorsDetails.this, "This legislator has no Facebook", Toast.LENGTH_LONG).show();
                }
            });
        }

        ImageButton legislatorDetails_t = (ImageButton) findViewById(R.id.legislatorDetails_t);
        final String twitter_id = getDetails.getStringExtra("twitter_id");
        if (!twitter_id.equals("N.A")) {
            legislatorDetails_t.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://twitter.com/" + twitter_id));
                    startActivity(intent);
                }
            });
        } else {
            legislatorDetails_t.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(LegislatorsDetails.this, "This legislator has no Twitter", Toast.LENGTH_LONG).show();
                }
            });
        }

        ImageButton legislatorDetails_w = (ImageButton) findViewById(R.id.legislatorDetails_w);
        final String website = getDetails.getStringExtra("website");
        if (!website.equals("N.A")) {
            legislatorDetails_w.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(website));
                    startActivity(intent);
                }
            });
        } else {
            legislatorDetails_w.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(LegislatorsDetails.this, "This legislator has no website", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}














