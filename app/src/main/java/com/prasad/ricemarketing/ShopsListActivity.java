package com.prasad.ricemarketing;

/**
 * Created by Prasad on 11/27/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ShopsListActivity extends AppCompatActivity {
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";
    public static String server,FCM_token,token;
    private ListView listView;
    DbHelper dbHelper;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FrameLayout frame;
    private ViewPager pager;
    int limitNumberOfPages = 10;
    private Toolbar toolbar;
    String result_responce="";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ImageView refresh;
    TextView emptyView;
    static ArrayList<Map<String,String>> items;
    SimpleAdapter cursorAdapter;
    FloatingActionButton add_shop;
    TextView displayname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoplist_main);

        frame = (FrameLayout) findViewById(R.id.frame);
        pager = (ViewPager) findViewById(R.id.pager);
        emptyView = (TextView)findViewById(R.id.empty_view);
        add_shop=(FloatingActionButton)findViewById(R.id.add_shop);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Total Shops");
        drawer();




       //inserting data in db
        dbHelper = new DbHelper(this);

        final Cursor cursor = dbHelper.getAllPersons();


        String[] columns = new String[] {
                DbHelper.shop_name,
                DbHelper.PhoneNumber,
                DbHelper.Gst_No,
                DbHelper.Shop_Aria

        };

        int [] widgets = new int[] {
                R.id.shopname,
                R.id.phnnumber,
                R.id.gstno,
                R.id.address
        };

        add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AddShopeActivity.class);

                startActivity(intent);
            }
        });

       /* DownloadVideosAdapter cursorAdapter = new DownloadVideosAdapter(this, R.layout.shopslist_item,
                cursor, columns, widgets);
        listView = (ListView)findViewById(R.id.playlist);
        listView.setAdapter(cursorAdapter);*/
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                Cursor itemCursor = (Cursor) ShopsListActivity.this.listView.getItemAtPosition(position);
                int personID = itemCursor.getInt(itemCursor.getColumnIndex(DbHelper.Video_ID));
                String path = itemCursor.getString(itemCursor.getColumnIndex(DbHelper.Video_Path));
                String name = itemCursor.getString(itemCursor.getColumnIndex(DbHelper.Video_Name));
                String time = itemCursor.getString(itemCursor.getColumnIndex(DbHelper.Video_Time));

                Intent intent = new Intent(getApplicationContext(), Downloaded_VideoView.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, personID);
                intent.putExtra("path", path);
                intent.putExtra("name", name);
                intent.putExtra("time", time);
                startActivity(intent);


            }
  public static final String ShopsList_TABLE = "Shops";
    public static final String Id = "id";
    public static final String shop_name = "shop_name";
    public static final String Gst_No = "Gst_No";
    public static final String PhoneNumber = "PhoneNumber";
    public static final String Shop_Aria = "Shop_Aria";



        });*/
        //getting sqlite db values and storeing data in simple cursor adapter
        items = new ArrayList<Map<String,String>>();
        Map<String, String> item;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
//            String name = cursor.getString(cursor.getColumnIndex("title"));//column name
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String shop_name = cursor.getString(cursor.getColumnIndex("shop_name"));
            String Gst_No = cursor.getString(cursor.getColumnIndex("Gst_No"));
            String PhoneNumber = cursor.getString(cursor.getColumnIndex("PhoneNumber"));
            String Shop_Aria = cursor.getString(cursor.getColumnIndex("Shop_Aria"));//column name
            item = new HashMap<>();

            item.put("id", ""+id);
            item.put("shop_name", shop_name);
            item.put("Gst_No", Gst_No);
            item.put("PhoneNumber", PhoneNumber);
            item.put("Shop_Aria", Shop_Aria);
            items.add(item);
            cursor.moveToNext();
        }

         cursorAdapter = new SimpleAdapter(this,items, R.layout.shoplistitem,
                 columns, widgets){


            @Override

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                ViewHolder holder;
                if (v == null) {
                    holder = new ViewHolder();
                    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.shoplistitem, null);

                    holder.delete = (ImageView) v.findViewById(R.id.delete);
                    holder.share = (ImageView) v.findViewById(R.id.share);

                    holder.shopname = (TextView) v.findViewById(R.id.shopname);
                    holder.gstno = (TextView) v.findViewById(R.id.gstno);
                    holder.address = (TextView) v.findViewById(R.id.address);
                    holder.phnnumber = (TextView) v.findViewById(R.id.phnnumber);

                    holder.card_view = (CardView) v.findViewById(R.id.card_view);

                    v.setTag(holder);

                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            Map<String, String> item = items.get(pos);
                            int id = Integer.parseInt(item.get("id"));
                            //using this id delete the record from the database
                            dbHelper.deleteVideo(id);

                            Toast.makeText(ShopsListActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Log.i("TAG", "ID " + id);
                            cursorAdapter.notifyDataSetChanged();
                           Intent in=new Intent(ShopsListActivity.this,ShopsListActivity.class);
                            startActivity(in);

                        }
                    });

                    holder.share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            Map<String, String> item = items.get(pos);
                            int id = Integer.parseInt(item.get("id"));
                            String shop_name = item.get("shop_name");
                            String Gst_No = item.get("Gst_No");
                            String PhoneNumber = item.get("PhoneNumber");
                            String Shop_Aria = item.get("Shop_Aria");

                            String shareBody ="Shop Name :"+shop_name+ '\n' + "GST Number :"+Gst_No + '\n' + "Phone Number :"+PhoneNumber + '\n' + "Address :"+Shop_Aria;

                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Shop Details");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(sharingIntent, "Share Shop Details"));



                        }
                    });

                    holder.card_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            Map<String, String> item = items.get(pos);
                            String id = item.get("id");
                            String shop_name = item.get("shop_name");
                            String Gst_No = item.get("Gst_No");
                            String PhoneNumber = item.get("PhoneNumber");
                            String Shop_Aria = item.get("Shop_Aria");

                            Intent intent = new Intent(getApplicationContext(), DetailsShopeActivity.class);
                            Bundle params = new Bundle();
                            params.putString("Id", id);
                            params.putString("shop_name", shop_name);
                            params.putString("Gst_No",Gst_No);
                            params.putString("PhoneNumber", PhoneNumber);
                            params.putString("Shop_Aria",Shop_Aria);

                            intent.putExtras(params);
                            startActivity(intent);
                            /*String shareBody = shop_name+" "+Gst_No;
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Shop Deatils");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(sharingIntent, "Share Image"));*/



                        }
                    });
                }
                else {
                    holder = null;
                    try {
                        holder = (ViewHolder) v.getTag();
                    }
                    catch(Exception ex)
                    {

                    }
                }
                if (holder == null)
                    return v;

                Map<String, String> data = items.get(position);
                holder.shopname.setText(data.get("shop_name"));
                holder.gstno.setText(data.get("Gst_No"));
                holder.phnnumber.setText(data.get("PhoneNumber"));
                holder.address.setText(data.get("Shop_Aria"));

                holder.share.setTag(position);
                holder.delete.setTag(position);
                holder.card_view.setTag(position);
                return v;

            }


        };

        listView = (ListView)findViewById(R.id.playlist);
        listView.setAdapter(cursorAdapter);
        cursorAdapter.notifyDataSetChanged();

        if (items.isEmpty()) {

            listView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            listView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private class ViewHolder {
        ImageView delete,share;
        CardView card_view;
        TextView shopname,gstno,phnnumber,address;
    }
//end db data
    private void drawer() {
        // Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        View hView =  navigationView.getHeaderView(0);
        displayname=(TextView)hView.findViewById(R.id.username);
        displayname.setText(SignInActivity.uname);

        // Setting Navigation View Item Selected Listener to handle the item
        // click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu

            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Checking if the item is in checked state or not, if
                // not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                    onNavigationItemSelected(navigationView.getMenu().getItem(0));
                } else
                    menuItem.setChecked(true);
                // Closing drawer on item click
                drawerLayout.closeDrawers();
                frame.setVisibility(View.VISIBLE);
                pager.setVisibility(View.GONE);

                //drawer menu ites declaration
                switch (menuItem.getItemId()) {

                    case R.id.shops:

                        Toast.makeText(ShopsListActivity.this, "You are in Shops Page Only", Toast.LENGTH_SHORT).show();

                        return true;
                    case R.id.mills:

                        Intent Int = new Intent(ShopsListActivity.this, Mills_ListActivity.class);

                        startActivity(Int);

                        return true;
                    case R.id.logout:

                        SharedPreferences preferences =getSharedPreferences("marketing", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();

                        Intent Intent1 = new Intent(ShopsListActivity.this, SignInActivity.class);
                        startActivity(Intent1);

                        return true;



                    default:

                        return true;

                }
            }
        });



        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.openDrawer,
                R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        // Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        // calling sync state is necessay or else your hamburger icon wont show
        // up
        actionBarDrawerToggle.syncState();

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit  Application?");
        alertDialogBuilder
                // .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}