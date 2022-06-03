package project.timetable_recommend.activity.controller;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

import project.timetable_recommend.model.dataTransferObject.SubjectListDTO;

/**
 * RequestQueue : Volley에서 사용하는 변수입니다.
 */
public class GsonThread extends Thread{
    static public SubjectListDTO subjectList;
    static RequestQueue          requestQueue;
    public GsonThread(Context context){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
        subjectList = new SubjectListDTO();
    }
    //이 함수는 Volley를 이용해서 url에 있는 정보를 가져옵니다.
    public void run() {
        makeRequest();
    }
    public void makeRequest() {
        String url = "http://:80/test/Android/android.jsp";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response+"");
                        processResponse(response);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.i("에러 발생 : ", error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        Log.i("요청보냄","성공!");
    }
    //이 함수는 gson을 이용해서 json객체를 파싱합니다.
    public void processResponse(String response){
        Gson gson   = new Gson();
        subjectList = gson.fromJson(response, SubjectListDTO.class);
        for(int i = 0 ; i<subjectList.getSubjects().size(); i++){
            subjectList.getSubjects().get(i).DivideTimetable();
        }
    }
    public SubjectListDTO getSubject(){
        return subjectList;
    }
}


