package android.congressinfo;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class MyTools extends AppCompatActivity {

    public static String stringFromURL(String inputURL) {
        URL url = null;
        HttpURLConnection connection = null;
        InputStream stream = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;
        try {
            url = new URL(inputURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            buffer = new StringBuffer();
            String line= "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    public static Bitmap bitMapFromURL(String inputURL) {
        URL url = null;
        Bitmap bitmap = null;
        try {
            url = new URL(inputURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream stream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, "photo.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public static void sortListMapByString(List<Map<String, Object>> inputList) {
        Collections.sort(inputList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String sorter1 = (String) o1.get("sorter");
                String sorter2 = (String) o2.get("sorter");
                return sorter1.compareTo(sorter2);
            }
        });
    }

    public static void sortListMapByString_reverse(List<Map<String, Object>> inputList) {
        Collections.sort(inputList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String sorter1 = (String) o1.get("sorter");
                String sorter2 = (String) o2.get("sorter");
                return -(sorter1.compareTo(sorter2));
            }
        });
    }

    public static String formatDate(String iso8601) {
        String[] MM = new String[] {"Jan.", "Feb.", "Mar.", "Apr.", "May.", "Jun.", "Jul.", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."};
        String[] dateSegments = iso8601.split("-");
        dateSegments[1] = MM[Integer.parseInt(dateSegments[1]) - 1];
        String desiredDate = dateSegments[1] + dateSegments[2] + ", " + dateSegments[0];
        return desiredDate;
    }

    public static int getPercentageLeft(Date start, Date end) {
        long now = System.currentTimeMillis();
        long s = start.getTime();
        long e = end.getTime();
        if (s >= e || now >= e) {
            return 0;
        }
        if (now <= s) {
            return 100;
        }
        return (int) ((e - now) * 100 / (e - s));
    }

    public static String saveKV(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("mytoolsFav", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
        return ("*YOU-SAVED-KV: " + key + "=>" + value);
    }

    public static String getKV(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("mytoolsFav", Context.MODE_PRIVATE);
        return sp.getString(key, "*EMPTY-KV");
    }

    public static String deleteKV(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("mytoolsFav", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
        return "*YOU-DELETED-KV: " + key;
    }
}
