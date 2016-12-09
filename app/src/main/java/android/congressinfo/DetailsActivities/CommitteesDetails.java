package android.congressinfo.DetailsActivities;

import android.congressinfo.MyTools;
import android.congressinfo.R;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class CommitteesDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Committee Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.myBlack));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_committees_details);
        Intent getDetails = getIntent();

        final String id = getDetails.getStringExtra("id");
        final ImageButton committeeDetails_star = (ImageButton) findViewById(R.id.committeeDetails_star);
        if (MyTools.getKV(this, id).equals("*EMPTY-KV")) {
            committeeDetails_star.setImageResource(android.R.drawable.btn_star_big_off);
            committeeDetails_star.setTag("off");
        } else {
            committeeDetails_star.setImageResource(android.R.drawable.btn_star_big_on);
            committeeDetails_star.setTag("on");
        }

        committeeDetails_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (committeeDetails_star.getTag().equals("off")) {
                    committeeDetails_star.setImageResource(android.R.drawable.btn_star_big_on);
                    committeeDetails_star.setTag("on");
                    MyTools.saveKV(CommitteesDetails.this, id, "=)");
                } else {
                    committeeDetails_star.setImageResource(android.R.drawable.btn_star_big_off);
                    committeeDetails_star.setTag("off");
                    MyTools.deleteKV(CommitteesDetails.this, id);
                }
            }
        });

        TextView committeeDetails_committee_id = (TextView) findViewById(R.id.committeeDetails_committee_id);
        committeeDetails_committee_id.setText(getDetails.getStringExtra("committee_id"));

        TextView committeeDetails_title = (TextView) findViewById(R.id.committeeDetails_title);
        committeeDetails_title.setText(getDetails.getStringExtra("title"));

        ImageView committeeDetails_chamberImg = (ImageView) findViewById(R.id.committeeDetails_chamberImg);
        TextView committeeDetails_chamber = (TextView) findViewById(R.id.committeeDetails_chamber);
        if (getDetails.getStringExtra("chamber").equals("house")) {
            committeeDetails_chamberImg.setImageResource(R.mipmap.h);
            committeeDetails_chamber.setText("House");
        } else if (getDetails.getStringExtra("chamber").equals("senate")) {
            committeeDetails_chamberImg.setImageResource(R.drawable.s);
            committeeDetails_chamber.setText("Senate");
        } else {
            committeeDetails_chamberImg.setImageResource(R.drawable.s);
            committeeDetails_chamber.setText("Joint");
        }

        TextView committeeDetails_parent_committee = (TextView) findViewById(R.id.committeeDetails_parent_committee);
        committeeDetails_parent_committee.setText(getDetails.getStringExtra("parent_committee"));

        TextView committeeDetails_contact = (TextView) findViewById(R.id.committeeDetails_contact);
        committeeDetails_contact.setText(getDetails.getStringExtra("contact"));

        TextView committeeDetails_office = (TextView) findViewById(R.id.committeeDetails_office);
        committeeDetails_office.setText(getDetails.getStringExtra("office"));


    }
}