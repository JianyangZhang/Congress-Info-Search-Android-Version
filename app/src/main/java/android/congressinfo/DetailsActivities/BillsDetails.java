package android.congressinfo.DetailsActivities;

import android.congressinfo.MyTools;
import android.congressinfo.R;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BillsDetails extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Bill Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.myBlack));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_bills_details);
        Intent getDetails = getIntent();


        final String id = getDetails.getStringExtra("id");
        final ImageButton billDetails_star = (ImageButton) findViewById(R.id.billDetails_star);
        if (MyTools.getKV(this, id).equals("*EMPTY-KV")) {
            billDetails_star.setImageResource(android.R.drawable.btn_star_big_off);
            billDetails_star.setTag("off");
        } else {
            billDetails_star.setImageResource(android.R.drawable.btn_star_big_on);
            billDetails_star.setTag("on");
        }

        billDetails_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (billDetails_star.getTag().equals("off")) {
                    billDetails_star.setImageResource(android.R.drawable.btn_star_big_on);
                    billDetails_star.setTag("on");
                    MyTools.saveKV(BillsDetails.this, id, "=)");
                } else {
                    billDetails_star.setImageResource(android.R.drawable.btn_star_big_off);
                    billDetails_star.setTag("off");
                    MyTools.deleteKV(BillsDetails.this, id);
                }
            }
        });

        TextView billDetails_bill_id = (TextView) findViewById(R.id.billDetails_bill_id);
        billDetails_bill_id.setText(getDetails.getStringExtra("bill_id").toUpperCase());

        TextView billDetails_title = (TextView) findViewById(R.id.billDetails_title);
        billDetails_title.setText(getDetails.getStringExtra("title"));

        TextView billDetails_bill_type = (TextView) findViewById(R.id.billDetails_bill_type);
        billDetails_bill_type.setText(getDetails.getStringExtra("bill_type").toUpperCase());

        TextView billDetails_sponsor = (TextView) findViewById(R.id.billDetails_sponsor);
        billDetails_sponsor.setText(getDetails.getStringExtra("sponsor"));

        TextView billDetails_chamber = (TextView) findViewById(R.id.billDetails_chamber);
        String chamber = getDetails.getStringExtra("chamber");
        if (chamber.equals("house")) {
            billDetails_chamber.setText("House");
        } else {
            billDetails_chamber.setText("Senate");
        }

        TextView billDetails_status = (TextView) findViewById(R.id.billDetails_status);
        billDetails_status.setText(getDetails.getStringExtra("status"));

        TextView billDetails_introduced_on = (TextView) findViewById(R.id.billDetails_introduced_on);
        billDetails_introduced_on.setText(MyTools.formatDate(getDetails.getStringExtra("introduced_on")));

        TextView billDetails_congress_url = (TextView) findViewById(R.id.billDetails_congress_url);
        billDetails_congress_url.setText(getDetails.getStringExtra("congress_url"));

        TextView billDetails_version_status = (TextView) findViewById(R.id.billDetails_version_status);
        billDetails_version_status.setText(getDetails.getStringExtra("version_status"));

        TextView billDetails_bill_url = (TextView) findViewById(R.id.billDetails_bill_url);
        billDetails_bill_url.setText(getDetails.getStringExtra("bill_url"));

    }
}
