package com.hhwt.travelplanner.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.adapter.Base64;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeyavijay on 31/03/16.
 */
public class ShowGroupimage extends AppCompatActivity implements View.OnClickListener{

    private static String file;
    private ImageView image1;
    Bitmap bitmap;

    private static final String IMAGEUPDATE = "http://www.rgmobiles.com/dating_webservices/get_image_url.php";

    public static final String KEY_USERNAME = "fileToUpload";

    int imagestatus;

    String imagemsg,imageurl;
    ProgressDialog progressDialog;;

    Button subimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagechoseinnotes);
        image1 = (ImageView) findViewById(R.id.addis);
        subimage = (Button) findViewById(R.id.imagesub);

        subimage.setOnClickListener(this);


        image1.setOnClickListener(this);
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ShowGroupimage.this.RESULT_OK) {
            Uri targetUri = data.getData();
            // textTargetUri.setText(targetUri.toString());

            try {
                bitmap = BitmapFactory.decodeStream(ShowGroupimage.this.getContentResolver().openInputStream(targetUri));
                Log.d("imagebitmap",""+bitmap);


                    image1.setImageBitmap(bitmap);
                    Log.d("image1",""+bitmap);









                String path2 = Environment.getExternalStorageDirectory().toString();

                File fn;
                try {  // Try to Save #1
                    fn = new File("/mnt/sdcard/out0.png");
                    FileOutputStream out = new FileOutputStream(fn);
                    Toast.makeText(getApplicationContext(), "In Save",
                            Toast.LENGTH_LONG).show();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();


                    file = fn.toString();
                    Log.d("filestring",""+file);

                    Log.d("fileformater",""+fn);
                  /*  Toast.makeText(getApplicationContext(),
                            "File is Saved in  " + fn, 10000).show();*/
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }




    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v == image1){

            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);
        }

        else if (v == subimage){


            Imageupload();

        }
    }

















    private void Imageupload() {

        final String username = file;
        Log.d("imageparameter", "" + username);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, IMAGEUPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            imagestatus = userObject.getInt("status");
                            Log.d("imagestatus", "" + imagestatus);
                            imagemsg = userObject.getString("msg");
                            Log.d("changepinmsg", "" + imagemsg);
                            imageurl = userObject.getString("image");

                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (imagestatus == 1) {
                            progressDialog.dismiss();
                            Toast.makeText(ShowGroupimage.this, imagemsg, Toast.LENGTH_SHORT).show();
                        } else if (imagestatus == 0) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(ShowGroupimage.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){

                String image = getStringImage(bitmap);
                Log.d("imagemethod",""+image);

                Map<String, String> params = new HashMap<>();
                Log.d("Mapparams",""+params);
               // params.put(KEY_USERNAME,username);

                params.put(KEY_USERNAME,image);
                Log.d("parameterparssing",""+params);
                //  params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShowGroupimage.this);

        Log.d("Result array",""+requestQueue);
        Log.d("Result final",""+stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(ShowGroupimage.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }








   /* private void Imageupload() {

        final String username = file;
        Log.d("imageparameter", "" + username);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, IMAGEUPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            imagestatus = userObject.getInt("status");
                            Log.d("imagestatus", "" + imagestatus);
                            imagemsg = userObject.getString("msg");
                            Log.d("changepinmsg", "" + imagemsg);
                            imageurl = userObject.getString("image");

                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (imagestatus == 1) {
                            progressDialog.dismiss();
                            Toast.makeText(ShowGroupimage.this, imagemsg, Toast.LENGTH_SHORT).show();
                        } else if (imagestatus == 0) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(ShowGroupimage.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<File, File> getParams(){

                String image = getStringImage(bitmap);
                Log.d("imagemethod",""+image);

                Map<String, String> params = new HashMap<>();
                Log.d("Mapparams",""+params);
                // params.put(KEY_USERNAME,username);

                params.put(KEY_USERNAME,image);
                Log.d("parameterparssing",""+params);
                //  params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShowGroupimage.this);

        Log.d("Result array",""+requestQueue);
        Log.d("Result final",""+stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(ShowGroupimage.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }
*/









}
