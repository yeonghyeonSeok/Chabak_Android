package com.syh4834.chabak;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

public class PlaceDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private PlaceImagePageAdapter placeImagePageAdapter;
    private ViewPager vpPlaceImage;
    private TextView tvImageNum;
    private String pageNum;
    private ConstraintLayout clToolbar;
    private ScrollView svPlaceDetail;

    private MapView mapView;

//    private Marker place;
//    private Marker[] toilets;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        vpPlaceImage = (ViewPager) findViewById(R.id.vp_place_image);
        svPlaceDetail = findViewById(R.id.sv_place_detail);
        tvImageNum = findViewById(R.id.tv_image_num);
        clToolbar = findViewById(R.id.cl_toolbar);

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.setClipToOutline(true);

        mapView.getMapAsync(this::onMapReady);

        placeImagePageAdapter = new PlaceImagePageAdapter(this);
        vpPlaceImage.setAdapter(placeImagePageAdapter);
        int totalPage = placeImagePageAdapter.getCount();

        pageNum = 1 + " / " + totalPage;
        tvImageNum.setText(pageNum);

        svPlaceDetail.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = svPlaceDetail.getScrollY();
                if(scrollY > 120) {
                    clToolbar.setVisibility(View.VISIBLE);
                } else {
                    clToolbar.setVisibility(View.INVISIBLE);
                }
            }
        });

        vpPlaceImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageNum = position+1 + " / " + totalPage;
                tvImageNum.setText(pageNum);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        //현재 위치 버튼 안보이게 설정
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(false);
        uiSettings.setScaleBarEnabled(false);
        uiSettings.setZoomControlEnabled(false);

        naverMap.setCameraPosition(new CameraPosition(new LatLng(37.622799, 128.739682), 17));


        setMarker(naverMap);

    }

    protected void setMarker(NaverMap naverMap) {

        //통신 후에는 for문으로 수정
        Marker[] toilets = new Marker[2];
        toilets[0] = new Marker();
        toilets[0].setPosition(new LatLng(37.622729,128.7396937));
        toilets[0].setIcon(OverlayImage.fromResource(R.drawable.marker_toilet));
        toilets[0].setWidth(80);
        toilets[0].setHeight(80);
        toilets[0].setMap(naverMap);

        toilets[1] = new Marker();
        toilets[1].setPosition(new LatLng(37.622607, 128.739371));
        toilets[1].setIcon(OverlayImage.fromResource(R.drawable.marker_toilet));
        toilets[1].setWidth(80);
        toilets[1].setHeight(80);
        toilets[1].setMap(naverMap);

        Marker place = new Marker();
        place.setPosition(new LatLng(37.622799, 128.739682));
        place.setIcon(OverlayImage.fromResource(R.drawable.marker_place));
        place.setWidth(250);
        place.setHeight(250);
        place.setMap(naverMap);
    }
}
