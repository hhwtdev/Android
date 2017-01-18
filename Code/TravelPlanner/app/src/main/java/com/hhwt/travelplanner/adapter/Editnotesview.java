package com.hhwt.travelplanner.adapter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.activity.MytripCategories_Fragment;
import com.hhwt.travelplanner.activity.Newnavigationbottom;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeyavijay on 18/04/16.
 */
public class Editnotesview extends Fragment implements View.OnClickListener{
    ProgressDialog progressDialog;
    private ImageView image1;
    EditText notesenter;
    Button submit;
    String  editstring;
    Bitmap bitmap;
    private static String file;
    private static final String IMAGEUPDATE = "http://www.rgmobiles.com/hhwt_webservice/image_url.php";
    private static final String NOTESUPDATE = "http://www.hhwt.tech/hhwt_webservice/updatenotes.php?";
    int imagestatus;
    int notesupdatestatus;
    String notesmsg,imagemsg,imageurl;
    public static final String KEY_USERNAME = "sno";
    public static final String KEY_PASSWORD = "notes";
TextView apptitle;
    ImageView backclick;

    String notesurl,alreadyotes, allreadyimage;

LinearLayout linearvalbottom;
    public static final String KEY_IMAGE = "image1";
    String image;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.editprofileview, container, false);


        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Trips");


        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);
        backclick.setOnClickListener(this);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);


        notesenter = (EditText) v.findViewById(R.id.notestype);
        submit = (Button) v.findViewById(R.id.imagesub);

        image1 = (ImageView) v.findViewById(R.id.addis);


        notesurl = Sessiondata.getInstance().getAlreadynotes();
        String CurrentString = notesurl;
        String[] separated = CurrentString.split("-");


        alreadyotes = separated[0];

        Log.d("Alreadynotes", "" + alreadyotes);
        allreadyimage = separated[1];
        Sessiondata.getInstance().setNotesimgfull(allreadyimage);


        notesenter.setEnabled(true);


        notesenter.setCursorVisible(true);


        if (allreadyimage.equals("null")) {
            Log.d("Alreadyimage", "" + allreadyimage);
            notesenter.setText("" + alreadyotes);
            image1.setEnabled(false);
        } else {
            Log.d("Alreadyimage", "" + allreadyimage);

            notesenter.setText("" + alreadyotes);


            if (allreadyimage.length() > 0) {
                try {
                    final String s = allreadyimage;

                    final int height = image1.getHeight();

                    //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);


                    Picasso.with(getActivity()).load(s).resize(200, 200)
                            .centerCrop().networkPolicy(NetworkPolicy.OFFLINE).into(image1, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(getActivity())
                                    .load(s)
                                    .resize(200, 200)
                                    .centerCrop()
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(image1, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {
                                            //  vs.Icon.setImageResource(R.drawable.groupchat_icon_48dp);
                                        }
                                    });
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


            image1.setOnClickListener(this);
            submit.setOnClickListener(this);
            return v;
        }


    @Override
    public void onClick(View v) {

        if(v == image1){
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);
        }

        else if(v == submit) {
            editstring = notesenter.getText().toString();
            Log.d("editstring", "" + editstring);
            Imageupload();
            //Notesupdate();




        }
        else if (v == backclick){
            getFragmentManager().popBackStack();
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            Uri targetUri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                Log.d("imagebitmap",""+bitmap);
                image1.setImageBitmap(bitmap);
                Log.d("",""+bitmap);

                String path2 = Environment.getExternalStorageDirectory().toString();

                File fn;
                try {  // Try to Save #1
                    fn = new File("/mnt/sdcard/out5.png");
                    FileOutputStream out = new FileOutputStream(fn);
                   /* Toast.makeText(getActivity().getApplicationContext(), "Save",
                            Toast.LENGTH_LONG).show();*/
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();
                    file = fn.toString();
                    Log.d("filestring",""+file);
                    Log.d("fileformater",""+fn);
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






    private void Imageupload() {
        if(bitmap != null){
            image = getStringImage(bitmap);
            Log.d("imagemethod", "" + image);



        }
        else {
            Toast.makeText(getActivity(), "Select your image", Toast.LENGTH_SHORT).show();
            image = "";
            Notesupdate();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST,IMAGEUPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            imageurl = userObject.getString("image1");
                            Log.d("arjunimage",""+imageurl);
                            Sessiondata.getInstance().setNotesimage(imageurl);
                            imagestatus = userObject.getInt("status");
                            Log.d("imagestatus", "" + imagestatus);
                            imagemsg = userObject.getString("msg");
                            Log.d("changepinmsg", "" + imagemsg);


                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (imagestatus == 1) {
                            progressDialog.dismiss();
                           Notesupdate();
                        } else if (imagestatus == 0) {
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),volleyError.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                Log.d("Mapparams",""+params);
                // params.put(KEY_USERNAME,username);
                params.put(KEY_IMAGE,image);
                Log.d("parameterparssing",""+params);
                //  params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        Log.d("Result array",""+requestQueue);
        Log.d("Result final",""+stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }


    private void Notesupdate() {
        final  String imalurl = Sessiondata.getInstance().getNotesimage();
        Log.d("imalurl", "" + imalurl);
        final String username = Sessiondata.getInstance().getSnonotes();
        final String notesupdate = editstring+"-"+imalurl;
        Log.d("Sno", "" + username);
        Log.d("Notes", "" + notesupdate);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NOTESUPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Vollyarjunresponse", "" + response);
                        //parse json data
                        try {
                            JSONObject userObject = new JSONObject(response);
                            notesupdatestatus = userObject.getInt("status");
                            Log.d("changepinstatus", "" + notesupdatestatus);
                            notesmsg = userObject.getString("msg");
                            Log.d("changepinmsg", "" + notesmsg);
                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (notesupdatestatus == 1) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), notesmsg, Toast.LENGTH_SHORT).show();
                            Fragment fr = new MytripCategories_Fragment();
                            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                            fc.replaceFragment(fr);
                            //  getFragmentManager().popBackStack();
                        } else if (notesupdatestatus == 0) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),volleyError.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                Log.d("Mapparams",""+params);
                params.put(KEY_PASSWORD,notesupdate);
                params.put(KEY_USERNAME,username);
                Log.d("parameterparssing",""+params);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Log.d("Result array",""+requestQueue);
        Log.d("Result final",""+stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }
}
