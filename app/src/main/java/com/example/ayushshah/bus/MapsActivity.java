    package com.example.ayushshah.bus;

    import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
    import android.util.Log;
    import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

    import java.util.ArrayList;
    import java.util.List;


    public class MapsActivity extends FragmentActivity implements LocationListener,
            GoogleMap.OnMapClickListener {

        private GoogleMap mMap; // Might be null if Google Play services APK is not available.
        List<Address> geocodeMatches = null;
        Double sLat, sLng, dLat, dLng;
        ArrayList<Double> Locdouble = new ArrayList<Double>();
        ArrayList<String> Locbus = new ArrayList<String>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           //Intent intent = getIntent();
            ArrayList<String> LocList = (ArrayList<String>) getIntent().getSerializableExtra("LocList");

           // Bundle b = getIntent().getExtras();
          /*  sLat = b.getDouble("sLat");
            Log.d("first",sLat.toString());
            sLng = b.getDouble("sLng");
            dLat = b.getDouble("dLat");
            dLng = b.getDouble("dLng");
*/
            //sLat = Double.parseDouble(intent.getStringExtra("srcLat"));
            //sLng = Double.parseDouble(intent.getStringExtra("srcLng"));
            //dLat = Double.parseDouble(intent.getStringExtra("dstLat"));
            //dLng = Double.parseDouble(intent.getStringExtra("dstLng"));
            sLat = Double.parseDouble(LocList.get(0));
            sLng = Double.parseDouble(LocList.get(1));
            dLat = Double.parseDouble(LocList.get(2));
            dLng = Double.parseDouble(LocList.get(3));
            for(int j=4;j<=LocList.size()-1;j+=3) {
                Locbus.add(LocList.get(j));
                Locdouble.add(Double.parseDouble(LocList.get(j+1)));
                Locdouble.add(Double.parseDouble(LocList.get(j+2)));
            }
            setContentView(R.layout.activity_maps);
            Toast.makeText(getApplicationContext(), "S " + sLat + " " + sLng + "D " + dLat + " " + dLng, Toast.LENGTH_LONG).show();

            try{
                setUpMapIfNeeded();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onResume() {
            super.onResume();
            setUpMapIfNeeded();
        }


        /**
         * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
         * installed) and the map has not already been instantiated.. This will ensure that we only ever
         * call {@link #setUpMap()} once when {@link #mMap} is not null.
         * <p/>
         * If it isn't installed {@link SupportMapFragment} (and
         * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
         * install/update the Google Play services APK on their device.
         * <p/>
         * A user can return to this FragmentActivity after following the prompt and correctly
         * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
         * have been completely destroyed during this process (it is likely that it would only be
         * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
         * method in {@link #onResume()} to guarantee that it will be called.
         */

        private void setUpMapIfNeeded() {
            // Do a null check to confirm that we have not already instantiated the map.
            if (mMap == null) {
                // Try to obtain the map from the SupportMapFragment.
                mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                        .getMap();
                // Check if we were successful in obtaining the map.
                if (mMap != null) {
                    setUpMap();
                }
            }
        }


        /**
         * This is where we can add markers or lines, add listeners or move the camera. In this case, we
         * just add a marker near Africa.
         * <p/>
         * This should only be called once and when we are sure that {@link #mMap} is not null.
         */
        public void setUpMap() {

            LatLng sLatLng = new LatLng(sLat, sLng);
            MarkerOptions src = new MarkerOptions()
                    .position(sLatLng)
                    .title("You are here!");
            src.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            mMap.addMarker(src);

            LatLng dLatLng = new LatLng(dLat, dLng);
            MarkerOptions dst = new MarkerOptions()
                    .position(dLatLng)
                    .title("Destination");
            mMap.addMarker(dst);

            for(int j=0,k=0;j<=Locdouble.size()-1;j+=2,k+=1)
            {
                LatLng tLng = new LatLng(Locdouble.get(j),Locdouble.get(j+1));
                MarkerOptions tst = new MarkerOptions()
                        .position(tLng)
                        .title("Bus No.: "+Locbus.get(k));
                tst.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(tst);
            }
            /*for(int j=4;j<=6;j+=3)
            {
                LatLng tLng = new LatLng(Double.parseDouble(LocList.get(j+1)), Double.parseDouble(LocList.get(j+2)));
                MarkerOptions tst = new MarkerOptions()
                        .position(tLng)
                        .title((String)LocList.get(j));
                mMap.addMarker(tst);
            }*/


            mMap.moveCamera(CameraUpdateFactory
                    .newLatLng(dLatLng));

            mMap.animateCamera(CameraUpdateFactory
                    .zoomTo(17));

        }



        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onMapClick(LatLng latLng) {

        }
    }
