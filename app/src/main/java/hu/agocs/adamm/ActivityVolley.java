package hu.agocs.adamm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityVolley extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private TextView mTextViewResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
        }
        });
    }

    private void jsonParse() {

        String url = "https://my-json-server.typicode.com/typicode/demo/comments";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final JSONArray jsonArray = response.getJSONArray("comment");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                final JSONObject comment = jsonArray.getJSONObject(i);


                                int id = comment.getInt("UserId");
                                System.out.println(id);

                                String body = comment.getString("Body");
                                System.out.println(body);
                                int postId = comment.getInt("ID");
                                System.out.println(postId);

                               mTextViewResult.append(id + ", " + String.valueOf(body) + ", " + postId + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
