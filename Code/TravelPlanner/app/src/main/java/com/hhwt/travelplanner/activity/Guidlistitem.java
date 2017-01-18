package com.hhwt.travelplanner.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hhwt.travelplanner.R;
import com.hhwt.travelplanner.Utills.ACRA_Page;
import com.hhwt.travelplanner.model.CreatedTripModel;
import com.hhwt.travelplanner.interfaces.FragmentChangeListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by jeyavijay on 01/04/16.
 */
public class Guidlistitem extends Fragment implements View.OnClickListener{
    TextView guidesview;
    TextView des;
    TextView tips;
    ListView itemlistclick;
    ImageView guidclick;
    ArrayList<Guidimageurl> emphistorydetails;
    ArrayList<Guidimageurl> imageurl;
    String URL;
    Button listguid;
   // emphistoryAdapter emphistory;


    ImageView backclick;

    TextView apptitle;
    Hashtable connectFlags;
    LinearLayout linearvalbottom;
    public static final String EXTRAVIEWINFO = "Viewinfo";

    private CreatedTripModel ctm;


    public void showguideFragment(CreatedTripModel ctm) {
        Fragment fr = new listofguid();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRAVIEWINFO, ctm);
        fr.setArguments(bundle);
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }


    public Guidlistitem() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.guidlistitem, container, false);

                 guidesview = (TextView) v.findViewById(R.id.guides);


        Thread.setDefaultUncaughtExceptionHandler(new ACRA_Page(getActivity()));



        des = (TextView) v.findViewById(R.id.description);

        backclick = (ImageView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.backar);
        backclick.setVisibility(View.VISIBLE);

        backclick.setOnClickListener(this);


        linearvalbottom = (LinearLayout) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.btmhide);
        linearvalbottom.setVisibility(View.GONE);

       // Tapjoy.connect(getActivity().getApplicationContext(), "ZzFSQetbR7GGoSUozBZnmwECa1bI9VsOk8e0o7L1W2LxBBMQuAFMQ_oVqn1c", connectFlags, (TJConnectListener) getActivity());
       // Tapjoy.setDebugEnabled(true);

        apptitle = (TextView) ((Newnavigationbottom) this.getActivity()).findViewById(R.id.apptitle);

        apptitle.setText("Guides");


        tips = (TextView) v.findViewById(R.id.tips);

     //   itemlistclick = (ListView) v.findViewById(R.id.itemclicklist);

        guidclick = (ImageView) v.findViewById(R.id.itemclickimg);

        listguid = (Button) v.findViewById(R.id.guiditem);

        guidesview.setText(Sessiondata.getInstance().getGuiditemclickguid());

        des.setText(Sessiondata.getInstance().getGuiditemclickdescription());

        tips.setText(Sessiondata.getInstance().getGuiditemclicktips());


      //  emphistorydetails = Sessiondata.getInstance(). getGuidimagedetails();


        ctm = new CreatedTripModel();


        listguid.setOnClickListener(this);




        if (Sessiondata.getInstance().getGuiditemclickimagesmain().length() > 0) {
            try {
                final String s = Sessiondata.getInstance().getGuiditemclickimagesmain();
                //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                Picasso.with(getActivity()).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(guidclick, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(getActivity())
                                .load(s)
                                .error(R.drawable.background_default)
                                .placeholder(R.drawable.loading)
                                .into(guidclick, new Callback() {
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










/*
Log.d("seimage",""+Sessiondata.getInstance().getGuiditemcimage());



        String URLS = Sessiondata.getInstance().getGuiditemcimage();





        itemclickXMLTask task = new itemclickXMLTask();
        // Execute the task
        task.execute(new String[] {URLS});

*/









      /*  emphistory = new emphistoryAdapter(getActivity(), emphistorydetails);
        itemlistclick.setAdapter(emphistory);
*/



        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        if(v == listguid){
/*
            Fragment fr = new listofguid();
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            fc.replaceFragment(fr);*/


            showguideFragment(ctm);
        }

       else if (v == backclick){
            getFragmentManager().popBackStack();

        }
    }




    // called when Tapjoy connect call succeed

    public void onConnectSuccess() {
        Log.d("Sucess", "Tapjoy connect Succeeded");
    }
    // called when Tapjoy connect call failed

    public void onConnectFailure() {
        Log.d("Stoptap joy", "Tapjoy connect Failed");
    }
    //session start
    @Override
    public void onStart() {
        super.onStart();
      //  Tapjoy.onActivityStart(getActivity());
    }

    //session end
    @Override
    public void onStop() {
        //Tapjoy.onActivityStop(getActivity());
        super.onStop();
    }




   /* private class itemclickXMLTask extends AsyncTask<String, Void, Bitmap> {





        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            //  imageView.setImageBitmap(result);


            Log.d("Arjunimage",""+result);
            Sessiondata.getInstance().setGuidimage(result);
            guidclick.setImageBitmap(result);

           *//* Sessiondata.getInstance().setFirstname(pfirsrname);
            Sessiondata.getInstance().setLastname(plastname);*//*



        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }















    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {





        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            //  imageView.setImageBitmap(result);


            Log.d("Arjunimage",""+result);
            Sessiondata.getInstance().setFbimage(result);





        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }*/




/*
    private class emphistoryAdapter extends BaseAdapter {
        ViewHolder viewHolder;
        LayoutInflater mLayoutInflater = null;
        Context context;
        ArrayList<Guidimageurl> sing_in_datas;

        public emphistoryAdapter( Context con, ArrayList<Guidimageurl> singindatas) {
            this.context = con;
            this.sing_in_datas = singindatas;
            mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return sing_in_datas.size();
        }

        @Override
        public Object getItem(int position) {
            return sing_in_datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(null == view) {
                view = mLayoutInflater.inflate(R.layout.guidlistitemclicklist, null);
                viewHolder = new ViewHolder();


                viewHolder.name = (TextView) view.findViewById(R.id.namelist);

                viewHolder.imagelist = (ImageView) view.findViewById(R.id.guimagelist);
                viewHolder.acti = (TextView) view.findViewById(R.id.activitylist);


                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)view.getTag();
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();



            viewHolder.name.setText(sing_in_datas.get(position).getName());
            // viewHolder.imagelist.setImageBitmap(sing_in_datas.get(position).getComments());

            viewHolder.acti.setText(sing_in_datas.get(position).getActivity());

           *//* imageurl =   Sessiondata.getInstance().getGuidimagedetails();

            for(int i =0; i <imageurl.size();i++){
                URL = Sessiondata.getInstance().getGuidimagedetails().get(position).getPhoto();

            }


            GetXMLTask task = new GetXMLTask();
            // Execute the task
            task.execute(new String[] { URL });


            viewHolder.imagelist.setImageBitmap(Sessiondata.getInstance().getFbimage());

*//*


            Guidimageurl newsItem = (Guidimageurl) sing_in_datas.get(position);





            if (newsItem.getPhoto().length() > 0) {
                try {
                    final String s = newsItem.getPhoto();
                    //         PicassoCache.getPicassoInstance(mContext).load(s).into(holder.image);
                    Picasso.with(context).load(s).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.imagelist, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(context)
                                    .load(s)
                                    .error(R.drawable.background_default)
                                    .placeholder(R.drawable.loading)
                                    .into(viewHolder.imagelist, new Callback() {
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








            return view;
        }
    }

    static class ViewHolder {
        TextView name;
        ImageView imagelist;

        TextView acti;
    }*/
}
