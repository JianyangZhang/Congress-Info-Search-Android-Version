package android.congressinfo.TabFragments;

import android.congressinfo.DetailsActivities.LegislatorsDetails;
import android.congressinfo.LegislatorsJSON.LegislatorsMain;
import android.congressinfo.MainActivity;
import android.congressinfo.MyAdapter;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FragmentNine extends Fragment implements View.OnClickListener {
    private final String LEGISLATORS_PATH = "http://sample-env-1.5ah5ppemzf.us-east-1.elasticbeanstalk.com/?dataBase=legislators";
    private ListView listView9 = null;
    private LegislatorsMain legislatorsMain = null;
    private Map<String, Integer> mapIndex;
    private LinearLayout index4;

    public FragmentNine() {
        // empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nine, container, false);
        listView9 = (ListView) view.findViewById(R.id.listView9);
        index4 = (LinearLayout) view.findViewById(R.id.index4);
        new FragmentNine.JSONTask_legislators().execute(LEGISLATORS_PATH);
        return view;
    }

    private void setMapIndex(String[] indexWords) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < indexWords.length; i++) {
            String indexWord = indexWords[i];
            String indexLetter = indexWord.substring(0, 1);
            if (mapIndex.get(indexLetter) == null)
                mapIndex.put(indexLetter, i);
        }
    }

    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        listView9.setSelection(mapIndex.get(selectedIndex.getText()));
    }

    private void displayIndex() {
        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater(null).inflate(R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            index4.addView(textView);
        }
    }

    public class JSONTask_legislators extends AsyncTask<String, Integer, String> {
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
            legislatorsMain = gson.fromJson(result, LegislatorsMain.class);
            final List<Map<String, Object>> legislatorsInfo = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < legislatorsMain.getCount(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                String photoURL = "http://theunitedstates.io/images/congress/225x275/" + legislatorsMain.getResults()[i].getBioguide_id() + ".jpg";
                map.put("photo", photoURL);
                map.put("line1", legislatorsMain.getResults()[i].getLast_name() + ", " + legislatorsMain.getResults()[i].getFirst_name());
                String line2 = "(" + legislatorsMain.getResults()[i].getParty() + ")";
                line2 = line2 + legislatorsMain.getResults()[i].getState_name() + " - ";
                if (legislatorsMain.getResults()[i].getDistrict() != null) {
                    line2 = line2 + "District " + legislatorsMain.getResults()[i].getDistrict();
                } else {
                    line2 = line2 + "District " + "0";
                }
                map.put("line2", line2);
                map.put("id", legislatorsMain.getResults()[i].getBioguide_id());
                map.put("sorter", legislatorsMain.getResults()[i].getLast_name());
                if (!MyTools.getKV(FragmentNine.this.getActivity(), (String) map.get("id")).equals("*EMPTY-KV")) {
                    legislatorsInfo.add(map);
                }
            }
            MyTools.sortListMapByString(legislatorsInfo);
            MyAdapter adapter = new MyAdapter(FragmentNine.this.getActivity(), legislatorsInfo, R.layout.legislators_item, new String[] {"line1", "line2"}, new int[] {R.id.textView_legislators_1, R.id.textView_legislators_2});
            listView9.setAdapter(adapter);
            String[] indexWords = new String[legislatorsInfo.size()];
            for (int i = 0; i < legislatorsInfo.size(); i++) {
                indexWords[i] = (String) legislatorsInfo.get(i).get("sorter");
            }
            setMapIndex(indexWords);
            displayIndex();
            listView9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MainActivity.currentPage_f = 0;
                    Intent setDetails = new Intent(view.getContext(), LegislatorsDetails.class);
                    String id = (String) legislatorsInfo.get(i).get("id");
                    setDetails.putExtra("id", id);
                    int j;
                    for (j = 0; j < legislatorsMain.getCount(); j++) {
                        if (legislatorsMain.getResults()[j].getBioguide_id().equals(id)) {
                            break;
                        }
                    }

                    String name = legislatorsMain.getResults()[j].getTitle() + ". " + legislatorsMain.getResults()[j].getLast_name() + ", " + legislatorsMain.getResults()[j].getFirst_name();
                    setDetails.putExtra("name", name);

                    String photoURL = "http://theunitedstates.io/images/congress/225x275/" + legislatorsMain.getResults()[j].getBioguide_id() + ".jpg";
                    setDetails.putExtra("photo", photoURL);

                    String party = legislatorsMain.getResults()[j].getParty();
                    setDetails.putExtra("party", party);

                    String email = legislatorsMain.getResults()[j].getOc_email();
                    setDetails.putExtra("email", email);

                    String chamber = legislatorsMain.getResults()[j].getChamber();
                    if (chamber.equals("house")) {
                        setDetails.putExtra("chamber", "House");
                    } else {
                        setDetails.putExtra("chamber", "Senate");
                    }

                    String contact = legislatorsMain.getResults()[j].getPhone();
                    setDetails.putExtra("contact", contact);

                    String start_term = legislatorsMain.getResults()[j].getTerm_start();
                    setDetails.putExtra("start_term", start_term);

                    String end_term = legislatorsMain.getResults()[j].getTerm_end();
                    setDetails.putExtra("end_term", end_term);

                    String office = "N.A";
                    if (legislatorsMain.getResults()[j].getOffice() != null) {
                        office = legislatorsMain.getResults()[j].getOffice();
                    }
                    setDetails.putExtra("office", office);

                    String state = legislatorsMain.getResults()[j].getState();
                    setDetails.putExtra("state", state);

                    String fax = "N.A";
                    if (legislatorsMain.getResults()[j].getFax() != null) {
                        fax = legislatorsMain.getResults()[j].getFax();
                    }
                    setDetails.putExtra("fax", fax);

                    String birthday = legislatorsMain.getResults()[j].getBirthday();
                    setDetails.putExtra("birthday", birthday);

                    String facebook_id = "N.A";
                    if (legislatorsMain.getResults()[j].getFacebook_id() != null) {
                        facebook_id = legislatorsMain.getResults()[j].getFacebook_id();
                    }
                    setDetails.putExtra("facebook_id", facebook_id);

                    String twitter_id = "N.A";
                    if (legislatorsMain.getResults()[j].getTwitter_id() != null) {
                        twitter_id = legislatorsMain.getResults()[j].getTwitter_id();
                    }
                    setDetails.putExtra("twitter_id", twitter_id);

                    String website = "N.A";
                    if (legislatorsMain.getResults()[j].getWebsite() != null) {
                        website = legislatorsMain.getResults()[j].getWebsite();
                    }
                    setDetails.putExtra("website", website);

                    startActivity(setDetails);
                }
            });
        }
    }
}