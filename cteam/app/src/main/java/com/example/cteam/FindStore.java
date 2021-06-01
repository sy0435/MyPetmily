package com.example.cteam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Map;

import static com.example.cteam.R.id.map_hair;
import static com.example.cteam.R.id.map_hospital;
import static com.example.cteam.R.id.map_park;

public class FindStore extends AppCompatActivity implements OnMapReadyCallback{

    public GoogleMap mMap;

    //지도 보이기 (메인)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        checkDangerousPermissions();    //위험권한

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_find_store);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //바텀네비게이션 클릭이벤트 설정
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case map_hospital:
                        mMap.clear();
                        startLocationService();
                        onMap_hospital();
                        break;
                    case map_hair:
                        mMap.clear();
                        startLocationService();
                        onMap_hair();
                        break;
                    case map_park:
                        mMap.clear();
                        startLocationService();
                        onMap_park();
                        break;
                }
                return true;
            }
        });
    }

    //내 위치 찾기 메소드
    private void startLocationService() {
        // 위치관리자 객체 참조
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        try {
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,    //위성
                    minTime,
                    minDistance,
                    gpsListener
            );
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,    //기지국
                    minTime,
                    minDistance,
                    gpsListener
            );

            Location lastLocation = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (lastLocation != null){
                Double latitude = lastLocation.getLatitude();
                Double longitude = lastLocation.getLongitude();

                LatLng myLocation = new LatLng(latitude, longitude);
                MyLocation(myLocation);
            }

        }catch (SecurityException e){
            e.getMessage();
        }
    }

    //내위치 마커
    private void MyLocation(LatLng myLocation) {
        mMap.addMarker(new MarkerOptions()
            .position(myLocation)
            .title("내 위치")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_position)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
    }

    //위치정보 메소드
    private class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();       //위도
            Double longitube = location.getLongitude();     //경도
        }
    }

    //첫 실행장소 (광주)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(35.1605, 126.8793)));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
        mMap.animateCamera(zoom);

        //마커 상세설정
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }
            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.marker,null);

                TextView title = v.findViewById(R.id.title);
                title.setText(marker.getTitle());
                TextView snippet = v.findViewById(R.id.snippet);
                snippet.setText(marker.getSnippet());
                return v;
            }
        });

        onMap_hospital();
    }

    //마커 (병원)
    public void onMap_hospital() {
        final LatLng hospital1 = new LatLng(35.1883, 126.8138);
        final LatLng hospital2 = new LatLng(35.1521, 126.8522);
        final LatLng hospital3 = new LatLng(35.1616, 126.8839);
        final String number1 = "tel:062-945-1275";
        final String number2 = "tel:062-384-7975";
        final String number3 = "tel:0507-1326-8575";

        //정보창 클릭시 전화번호 화면 띄우기
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                LatLng position = marker.getPosition();
                Intent intent = null;

                if (position.toString().equals(hospital1.toString())) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number1));
                    startActivity(intent);
                }else if (position.toString().equals(hospital2.toString())) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number2));
                    startActivity(intent);
                }else if (position.toString().equals(hospital3.toString())) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number3));
                    startActivity(intent);
                }//if
            }
        });

        mMap.addMarker(new MarkerOptions()
            .position(hospital1)
            .title("치유동물병원")
            .snippet("☎ 062-945-1275\n광주 광산구 풍영로 189")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hospital1));

        mMap.addMarker(new MarkerOptions()
            .position(hospital2)
            .title("쿨펫 동물병원")
            .snippet("☎ 062-384-7975\n광주 서구 시청로 40 롯데마트 4층")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hospital2));

        mMap.addMarker(new MarkerOptions()
            .position(hospital3)
            .title("본펫동물병원")
            .snippet("☎ 0507-1326-8575\n광주 서구 무진대로 945-1 1층")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hospital3));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(35.1605, 126.8793)));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
        mMap.animateCamera(zoom);
    }

    //마커 (애견미용실)
    public void onMap_hair() {
        final LatLng hair1 = new LatLng(35.1922, 126.8139);
        final LatLng hair2 = new LatLng(35.1575, 126.8635);
        final LatLng hair3 = new LatLng(35.1264, 126.8779);
        final String number1 = "tel:062-953-0209";
        final String number2 = "tel:062-376-7576";
        final String number3 = "tel:0507-1312-2297";

        //정보창 클릭시 전화번호 화면 띄우기
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                LatLng position = marker.getPosition();
                Intent intent = null;

                if (position.toString().equals(hair1.toString())) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number1));
                    startActivity(intent);
                }else if (position.toString().equals(hair2.toString())) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number2));
                    startActivity(intent);
                }else if (position.toString().equals(hair3.toString())) {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number3));
                    startActivity(intent);
                }//if
            }
        });

        mMap.addMarker(new MarkerOptions()
            .position(hair1)
            .title("아띠살롱")
            .snippet("☎ 062-953-0209\n광주 광산구 풍영로 231")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hair1));

        mMap.addMarker(new MarkerOptions()
            .position(hair2)
            .title("붕가붕가샵")
            .snippet("☎ 062-376-7576\n광주 서구 상일로24번길 4 1층")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hair2));

        mMap.addMarker(new MarkerOptions()
            .position(hair3)
            .title("개바라기")
            .snippet("☎ 0507-1312-2297\n광주 서구 풍암1로 7 대주빌딩 105호")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hair3));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(35.1605, 126.8793)));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
        mMap.animateCamera(zoom);
    }

    //마커 (공원)
    public void onMap_park() {
        LatLng park1 = new LatLng(35.1467, 126.9088);
        LatLng park2 = new LatLng(35.1491, 126.8401);
        LatLng park3 = new LatLng(35.2228, 126.8433);

        mMap.addMarker(new MarkerOptions()
            .position(park1)
            .title("광주공원")
            .snippet("광주 남구 중앙로107번길 15")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(park1));

        mMap.addMarker(new MarkerOptions()
            .position(park2)
            .title("5.18자유공원")
            .snippet("광주 서구 상무평화로 13")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(park2));

        mMap.addMarker(new MarkerOptions()
            .position(park3)
            .title("쌍암공원")
            .snippet("광주 광산구 첨단중앙로182번길 39")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(park3));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(35.1605, 126.8793)));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
        mMap.animateCamera(zoom);
    }

    //위험권한
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    //위험권한
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}