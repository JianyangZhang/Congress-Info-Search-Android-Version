package android.congressinfo.TabFragments;

import android.congressinfo.DetailsActivities.BillsDetails;
import android.congressinfo.BillsJSON.BillsMain;
import android.congressinfo.MainActivity;
import android.congressinfo.MyTools;
import android.congressinfo.R;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentFive extends Fragment {
    private final String NEW_BILLS_PATH = "http://sample-env-1.5ah5ppemzf.us-east-1.elasticbeanstalk.com/?dataBase=bills&flag_6=true";
    private ListView listView5 = null;
    private BillsMain billsMain = null;

    public FragmentFive() {
        // empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, container, false);
        listView5 = (ListView) view.findViewById(R.id.listView5);
        new FragmentFive.JSONTask_activeBills().execute(NEW_BILLS_PATH);
        return view;
    }

    public class JSONTask_activeBills extends AsyncTask<String, Integer, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            String result = MyTools.stringFromURL(params[0]);
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            billsMain = gson.fromJson(result, BillsMain.class);
            final List<Map<String, Object>> newBillsInfo = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < billsMain.getPage().getCount(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("line1", billsMain.getResults()[i].getBill_id().toUpperCase());
                map.put("line2", billsMain.getResults()[i].getOfficial_title());
                if (billsMain.getResults()[i].getShort_title() != null) {
                    map.put("line2", billsMain.getResults()[i].getShort_title());
                }
                map.put("line3", billsMain.getResults()[i].getIntroduced_on());
                map.put("id", billsMain.getResults()[i].getBill_id());
                newBillsInfo.add(map);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(FragmentFive.this.getActivity(), newBillsInfo, R.layout.bills_item,
                    new String[] {"line1", "line2", "line3"},
                    new int[] {R.id.textView_bills_1, R.id.textView_bills_2, R.id.textView_bills_3});
            listView5.setAdapter(simpleAdapter);

            listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MainActivity.currentPage_b = 1;
                    Intent setDetails = new Intent(view.getContext(), BillsDetails.class);
                    String id = (String) newBillsInfo.get(i).get("id");
                    setDetails.putExtra("id", id);
                    int j;
                    for (j = 0; j < billsMain.getPage().getCount(); j++) {
                        if (billsMain.getResults()[j].getBill_id().equals(id)) {
                            break;
                        }
                    }

                    String bill_id = billsMain.getResults()[j].getBill_id();
                    setDetails.putExtra("bill_id", bill_id);

                    String title = billsMain.getResults()[j].getOfficial_title();
                    if (billsMain.getResults()[j].getShort_title() != null) {
                        title = billsMain.getResults()[j].getShort_title();
                    }
                    setDetails.putExtra("title", title);

                    String bill_type = billsMain.getResults()[j].getBill_type();
                    setDetails.putExtra("bill_type", bill_type);

                    String sponsor = billsMain.getResults()[j].getSponsor().getTitle() + ". " + billsMain.getResults()[j].getSponsor().getLast_name() +
                            ", " + billsMain.getResults()[j].getSponsor().getFirst_name();
                    setDetails.putExtra("sponsor", sponsor);

                    String chamber = billsMain.getResults()[j].getChamber();
                    setDetails.putExtra("chamber", chamber);

                    Boolean status = billsMain.getResults()[j].getHistory().getActive();
                    if (status) {
                        setDetails.putExtra("status", "Active");
                    } else {
                        setDetails.putExtra("status", "New");
                    }

                    String introduced_on = billsMain.getResults()[j].getIntroduced_on();
                    setDetails.putExtra("introduced_on", introduced_on);

                    String congress_url = "N.A";
                    if (billsMain.getResults()[j].getUrls().getCongress() != null) {
                        congress_url = billsMain.getResults()[j].getUrls().getCongress();
                    }
                    setDetails.putExtra("congress_url", congress_url);

                    String version_status = "N.A";
                    if (billsMain.getResults()[j].getLast_version().getVersion_name() != null) {
                        version_status = billsMain.getResults()[j].getLast_version().getVersion_name();
                    }
                    setDetails.putExtra("version_status", version_status);

                    String bill_url = "N.A";
                    if (billsMain.getResults()[j].getLast_version().getUrls().getPDF() != null) {
                        bill_url = billsMain.getResults()[j].getLast_version().getUrls().getPDF();
                    }
                    setDetails.putExtra("bill_url", bill_url);

                    startActivity(setDetails);
                }
            });
        }
    }
}
