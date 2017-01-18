/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hhwt.travelplanner.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.AlertUtils;
import com.hhwt.travelplanner.Utills.Date_utill;
import com.hhwt.travelplanner.activity.Sessiondata;
import com.hhwt.travelplanner.model.Categorylistmodelmytrip;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.hhwt.travelplanner.model.foodclassification;
import com.hhwt.travelplanner.model.photoss;
import com.hhwt.travelplanner.widgets.SquareImageView;
import com.hhwt.travelplanner.widgets.font.RobotoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCategorylistadapter extends RecyclerView.Adapter<MyCategorylistadapter.ViewHolder> implements View.OnClickListener {

    private static Context mContexts;
    private List<Categorylistmodelmytrip> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    Context mContext;
    private static final String NOTESUPDATE = "http://www.hhwt.tech/hhwt_webservice/updatenotes.php?";
    private static final String NOTESALREADY = "http://www.hhwt.tech/hhwt_webservice/insertnotes.php?";
    public static final String KEY_USERNAME = "sno";
    public static final String KEY_PASSWORD = "notes";

    List<photoss> photoitems = new ArrayList<>();
    List<foodclassification> fooditems = new ArrayList<>();
    int notesupdatestatus;
    String notesmsg;
    String parmetersno;
    String newsno;
    String empty;
    String notescontent;
    String snno;
    private int lastPosition = -1;
    ProgressDialog progressDialog;
    static ImageView image1;
    private int daycount = 1;
    String emptydate = "";


    private Context context;

    public MyCategorylistadapter(Context context, List<Categorylistmodelmytrip> items) {
        daycount = 1;
        emptydate = "";
        this.context = context;
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mycategorylist, parent, false);
        mContext = parent.getContext();
        //Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(mContext));
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


  /*      for (int i =0; i <items.size(); i++){*/

        newsno = items.get(position).getSno();
        // Sessiondata.getInstance().setSnonotes(newsno);
        Log.d("newsno", "" + newsno);

        //  }

        final Categorylistmodelmytrip item = items.get(position);
        if (position == 0) {
            daycount = 1;
        }
        snno = item.getSno();

        AlertUtils.RatingColorGreen(mContext, holder.item_ratingBar);

        Log.d("create notes", "" + snno);
        try {
            Float rate = Float.parseFloat(item.getRating());
            holder.item_ratingBar.setRating(rate);
            holder.item_ratingBar.setEnabled(false);
        } catch (NumberFormatException e) {
            holder.item_ratingBar.setRating(0);
            holder.item_ratingBar.setEnabled(false);
        }
        holder.title.setText(item.getName());
        holder.subtitle.setText(item.getActivity());
        holder.address.setText(item.getDescription());

        String yourdate = Date_utill.Dateformatchangeforall(item.getDate());
        if (emptydate.equalsIgnoreCase(yourdate)) {
            //     holder.Dateis.setText("(Day " + daycount + ") " + yourdate);
            holder.Dateis.setText(yourdate);
        } else {
            emptydate = yourdate;
            //      holder.Dateis.setText("(Day " + daycount + ") " + yourdate);
            holder.Dateis.setText(yourdate);
            ++daycount;
        }
        final ViewHolder vh = holder;
        if (item.photos.size() > 0) {
            try {
                final String s = item.getPhotos().get(0).getPhotourl();
                //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                Picasso.with(mContext).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(mContext)
                                .load(s)
                                .error(R.drawable.background_default)
                                .placeholder(R.drawable.loading)
                                .into(vh.image, new Callback() {
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


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.cd.startAnimation(animation);
        lastPosition = position;
        holder.itemView.setTag(item);
        holder.todeleteitem.findViewById(R.id.todeleteitem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (onItemClickListener != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onItemClickListener.onDeleteitemClick(v, (Categorylistmodelmytrip) holder.itemView.getTag());
                        }
                    }, 200);
                }
            }
        });

       /* holder.editmenu.findViewById(R.id.editmenu);

        holder.editmenu.findViewById(R.id.editmenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Fragment fr = new newnotes();
                FragmentChangeListener fc = (FragmentChangeListener) context;
                fc.replaceFragment(fr, context.getString(R.string.title_explore));





              *//*  Intent intents = new Intent(context, ShowGroupimage.class);
                ((Activity) context).startActivity(intents);*//*


            }
        });*/


        holder.notes.findViewById(R.id.addnotes);


        //   -------------------------------------------------

        notescontent = Sessiondata.getInstance().getNotesno().get(position).getNotes();

        Log.d("Notes", "" + notescontent);

        if (notescontent.equals("0")) {
            holder.notes.setText("Add Notes");

            holder.notes.setBackgroundColor(context.getResources().getColor(R.color.bgyellow));
        } else {
            holder.notes.setText("View Notes");
            holder.notes.setBackgroundColor(context.getResources().getColor(R.color.orangedrak));
            //  ---------------------------------------
        }

        holder.notes.findViewById(R.id.addnotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("Arraylistvaluessno", "" + Sessiondata.getInstance().getNotesno().get(position).getSno());
                parmetersno = Sessiondata.getInstance().getNotesno().get(position).getIdnumber();
                Log.d("Name", "" + Sessiondata.getInstance().getNotesno().get(position).getName());
                Log.d("Arjun", "" + parmetersno);
                Sessiondata.getInstance().setSnonotes(parmetersno);

                Notesalredyhave();

                // showInputDialog();


            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onFavItemClick(v, (Categorylistmodelmytrip) v.getTag());
                }
            }, 200);
        }


    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public RobotoTextView title, subtitle, address, Dateis;
        public RatingBar item_ratingBar;
        public SquareImageView image;
        public CardView cd;
        ImageView todeleteitem;
        Button notes;

        public ViewHolder(View itemView) {
            super(itemView);
            cd = (CardView) itemView.findViewById(R.id.topcard);
            image = (SquareImageView) itemView.findViewById(R.id.image);
            title = (RobotoTextView) itemView.findViewById(R.id.title);
            subtitle = (RobotoTextView) itemView.findViewById(R.id.subtitle);
            address = (RobotoTextView) itemView.findViewById(R.id.address);
            item_ratingBar = (RatingBar) itemView.findViewById(R.id.item_ratingBar);
            Dateis = (RobotoTextView) itemView.findViewById(R.id.Dateis);
            todeleteitem = (ImageView) itemView.findViewById(R.id.todeleteitem);
            //   editmenu = (ImageView) itemView.findViewById(R.id.editmenu);
            notes = (Button) itemView.findViewById(R.id.addnotes);


        }
    }


   /* private void showInputDialog() {
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View promptView = layoutInflater.inflate(R.layout.chang_notes, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.notestype);
        Log.d("viewnotes", "" + Sessiondata.getInstance().getAlreadynotes());
editText.setText(Sessiondata.getInstance().getAlreadynotes());


        empty = editText.getText().toString();


        *//* if (empty == "") {

        } else {*//*
            // setup a dialog window
            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            empty = editText.getText().toString();

                            Log.d("exm", "" + empty);
                            Notesupdate();
                            *//*if (empty.equals("Enter the notes")) {


                            }*//*
                            *//*else{
                                Notesalredyhave();

                                editText.setText(Sessiondata.getInstance().getAlreadynotes());
                            }*//*


                        }

                    })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();


                                }
                            });
            // create an alert dialog
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();
        }

*/


    //}


   /* private void Notesupdate() {

        final String username = snno;
        final String notesupdate = empty;
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
                           Toast.makeText(mContext, notesmsg, Toast.LENGTH_SHORT).show();
                        } else if (notesupdatestatus == 0) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(mContext,volleyError.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                Log.d("Mapparams",""+params);
                params.put(KEY_PASSWORD,notesupdate);
                params.put(KEY_USERNAME,username);
                Log.d("parameterparssing",""+params);
                //  params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        Log.d("Result array",""+requestQueue);
        Log.d("Result final",""+stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }

*/


    private void Notesalredyhave() {

        final String username = parmetersno;
        Log.d("INPUTSNO", "" + username);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NOTESALREADY,
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
                            if (notesmsg != "") {
                                Sessiondata.getInstance().setAlreadynotes(notesmsg);
                                Log.d("changepinmsg", "" + notesmsg);
                            } else {
                                Sessiondata.getInstance().setAlreadynotes(notesmsg + "Enter the notes");

                            }
                        } catch (Exception ex) {
                            //don't forget this
                        }
                        if (notesupdatestatus == 1) {
                            progressDialog.dismiss();


                            Log.d("alreadymsg", "" + Sessiondata.getInstance().getAlreadynotes());


                            Fragment fr = new newnotes();
                            FragmentChangeListener fc = (FragmentChangeListener) context;
                            fc.replaceFragment(fr);
                            // Toast.makeText(mContext, notesmsg, Toast.LENGTH_SHORT).show();
                        } else if (notesupdatestatus == 0) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(mContext, volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Mapparams", "" + params);
                params.put(KEY_USERNAME, username);
                Log.d("parameterparssing", "" + params);
                //  params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        Log.d("Result array", "" + requestQueue);
        Log.d("Result final", "" + stringRequest);
        requestQueue.add(stringRequest);
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
    }


    public interface OnItemClickListener {

        void onFavItemClick(View view, Categorylistmodelmytrip viewModel);

        void onDeleteitemClick(View view, Categorylistmodelmytrip viewModel);

    }
}
