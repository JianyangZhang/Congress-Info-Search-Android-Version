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
import android.util.Log;
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

public class FragmentTen extends Fragment {
    private final String ACTIVE_BILLS_PATH = "http://sample-env-1.5ah5ppemzf.us-east-1.elasticbeanstalk.com/?dataBase=bills&flag_5=true";
    private final String NEW_BILLS_PATH = "http://sample-env-1.5ah5ppemzf.us-east-1.elasticbeanstalk.com/?dataBase=bills&flag_6=true";
    private ListView listView10 = null;
    private BillsMain billsMain1 = null;
    private BillsMain billsMain2 = null;
    private static String[] result;

    public FragmentTen() {
        // empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ten, container, false);
        listView10 = (ListView) view.findViewById(R.id.listView10);
        new FragmentTen.JSONTask_doubleBills().execute(ACTIVE_BILLS_PATH, NEW_BILLS_PATH);
        return view;
    }

    public class JSONTask_doubleBills extends AsyncTask<String, Integer, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            result = new String[2];
            result[0] = MyTools.stringFromURL(params[0]);
            result[1] = MyTools.stringFromURL(params[1]);
            return "";
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            billsMain1 = gson.fromJson(FragmentTen.result[0], BillsMain.class);
            billsMain2 = gson.fromJson(FragmentTen.result[1], BillsMain.class);

            final List<Map<String, Object>> bothBillsInfo = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < billsMain1.getPage().getCount(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("line1", billsMain1.getResults()[i].getBill_id().toUpperCase());
                map.put("line2", billsMain1.getResults()[i].getOfficial_title());
                if (billsMain1.getResults()[i].getShort_title() != null) {
                    map.put("line2", billsMain1.getResults()[i].getShort_title());
                }
                map.put("line3", billsMain1.getResults()[i].getIntroduced_on());
                map.put("sorter", billsMain1.getResults()[i].getIntroduced_on());
                map.put("id", billsMain1.getResults()[i].getBill_id());
                if (!MyTools.getKV(FragmentTen.this.getActivity(), (String) map.get("id")).equals("*EMPTY-KV")) {
                    bothBillsInfo.add(map);
                }
            }

            for (int i = 0; i < billsMain2.getPage().getCount(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("line1", billsMain2.getResults()[i].getBill_id().toUpperCase());
                map.put("line2", billsMain2.getResults()[i].getOfficial_title());
                if (billsMain2.getResults()[i].getShort_title() != null) {
                    map.put("line2", billsMain2.getResults()[i].getShort_title());
                }
                map.put("line3", billsMain2.getResults()[i].getIntroduced_on());
                map.put("sorter", billsMain1.getResults()[i].getIntroduced_on());
                map.put("id", billsMain2.getResults()[i].getBill_id());
                if (!MyTools.getKV(FragmentTen.this.getActivity(), (String) map.get("id")).equals("*EMPTY-KV")) {
                    bothBillsInfo.add(map);
                }
            }

            MyTools.sortListMapByString_reverse(bothBillsInfo);

            SimpleAdapter simpleAdapter = new SimpleAdapter(FragmentTen.this.getActivity(), bothBillsInfo, R.layout.bills_item,
                    new String[] {"line1", "line2", "line3"},
                    new int[] {R.id.textView_bills_1, R.id.textView_bills_2, R.id.textView_bills_3});
            listView10.setAdapter(simpleAdapter);

            listView10.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MainActivity.currentPage_f = 1;
                    Intent setDetails = new Intent(view.getContext(), BillsDetails.class);
                    String id = (String) bothBillsInfo.get(i).get("id");
                    setDetails.putExtra("id", id);
                    int j = 0;
                    int k = 0;
                    boolean isActive = false;
                    for (j = 0; j < billsMain1.getPage().getCount(); j++) {
                        if (billsMain1.getResults()[j].getBill_id().equals(id)) {
                            isActive = true;
                            break;
                        }
                    }
                    if (!isActive) {
                        for (k = 0; k < billsMain2.getPage().getCount(); k++) {
                            if (billsMain2.getResults()[k].getBill_id().equals(id)) {
                                break;
                            }
                        }
                    }

                    if (isActive) {
                        String bill_id = billsMain1.getResults()[j].getBill_id();
                        setDetails.putExtra("bill_id", bill_id);

                        String title = billsMain1.getResults()[j].getOfficial_title();
                        if (billsMain1.getResults()[j].getShort_title() != null) {
                            title = billsMain1.getResults()[j].getShort_title();
                        }
                        setDetails.putExtra("title", title);

                        String bill_type = billsMain1.getResults()[j].getBill_type();
                        setDetails.putExtra("bill_type", bill_type);

                        String sponsor = billsMain1.getResults()[j].getSponsor().getTitle() + ". " + billsMain1.getResults()[j].getSponsor().getLast_name() +
                                ", " + billsMain1.getResults()[j].getSponsor().getFirst_name();
                        setDetails.putExtra("sponsor", sponsor);

                        String chamber = billsMain1.getResults()[j].getChamber();
                        setDetails.putExtra("chamber", chamber);

                        Boolean status = billsMain1.getResults()[j].getHistory().getActive();
                        if (status) {
                            setDetails.putExtra("status", "Active");
                        } else {
                            setDetails.putExtra("status", "New");
                        }

                        String introduced_on = billsMain1.getResults()[j].getIntroduced_on();
                        setDetails.putExtra("introduced_on", introduced_on);

                        String congress_url = "N.A";
                        if (billsMain1.getResults()[j].getUrls().getCongress() != null) {
                            congress_url = billsMain1.getResults()[j].getUrls().getCongress();
                        }
                        setDetails.putExtra("congress_url", congress_url);

                        String version_status = "N.A";
                        if (billsMain1.getResults()[j].getLast_version().getVersion_name() != null) {
                            version_status = billsMain1.getResults()[j].getLast_version().getVersion_name();
                        }
                        setDetails.putExtra("version_status", version_status);

                        String bill_url = "N.A";
                        if (billsMain1.getResults()[j].getLast_version().getUrls().getPDF() != null) {
                            bill_url = billsMain1.getResults()[j].getLast_version().getUrls().getPDF();
                        }
                        setDetails.putExtra("bill_url", bill_url);

                    } else {
                        String bill_id = billsMain2.getResults()[k].getBill_id();
                        setDetails.putExtra("bill_id", bill_id);

                        String title = billsMain2.getResults()[k].getOfficial_title();
                        if (billsMain2.getResults()[k].getShort_title() != null) {
                            title = billsMain2.getResults()[k].getShort_title();
                        }
                        setDetails.putExtra("title", title);

                        String bill_type = billsMain2.getResults()[k].getBill_type();
                        setDetails.putExtra("bill_type", bill_type);

                        String sponsor = billsMain2.getResults()[k].getSponsor().getTitle() + ". " + billsMain2.getResults()[k].getSponsor().getLast_name() +
                                ", " + billsMain2.getResults()[k].getSponsor().getFirst_name();
                        setDetails.putExtra("sponsor", sponsor);

                        String chamber = billsMain2.getResults()[k].getChamber();
                        setDetails.putExtra("chamber", chamber);

                        Boolean status = billsMain2.getResults()[k].getHistory().getActive();
                        if (status) {
                            setDetails.putExtra("status", "Active");
                        } else {
                            setDetails.putExtra("status", "New");
                        }

                        String introduced_on = billsMain2.getResults()[k].getIntroduced_on();
                        setDetails.putExtra("introduced_on", introduced_on);

                        String congress_url = "N.A";
                        if (billsMain2.getResults()[k].getUrls().getCongress() != null) {
                            congress_url = billsMain2.getResults()[k].getUrls().getCongress();
                        }
                        setDetails.putExtra("congress_url", congress_url);

                        String version_status = "N.A";
                        if (billsMain2.getResults()[k].getLast_version().getVersion_name() != null) {
                            version_status = billsMain2.getResults()[k].getLast_version().getVersion_name();
                        }
                        setDetails.putExtra("version_status", version_status);

                        String bill_url = "N.A";
                        if (billsMain2.getResults()[k].getLast_version().getUrls().getPDF() != null) {
                            bill_url = billsMain2.getResults()[k].getLast_version().getUrls().getPDF();
                        }
                        setDetails.putExtra("bill_url", bill_url);
                    }
                    startActivity(setDetails);
                }
            });
        }
    }
}