package com.depex.eatasmuch.user.screens;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import com.depex.eatasmuch.user.R;
import com.depex.eatasmuch.user.adapter.CreatePinAdapterFromRestaurants;
import com.depex.eatasmuch.user.model.Restaurant;
import com.depex.eatasmuch.user.receivers.NetworkReceiver;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.depex.eatasmuch.user.utils.UtilMethods.registerNetworkReceiver;
import static com.depex.eatasmuch.user.utils.UtilMethods.unregisterNetworkReceiver;
public class HomeActivity extends AppCompatActivity implements View.OnTouchListener {


    @BindView(R.id.auto_pin_complete_textview)
    AutoCompleteTextView findAutoTextView;

    @BindView(R.id.network_info)
    TextView networkInfo;
    NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        final List<Restaurant> restaurants=new ArrayList<>();

        //register network receiver to check Network
        receiver=new NetworkReceiver(findAutoTextView);
        registerNetworkReceiver(receiver, this);

        /*for (int i=110000;i<110099;i++){
            Restaurant restaurant=new Restaurant();
            restaurant.setPin(String.valueOf(i));
            restaurants.add(restaurant);
        }*/
        Restaurant restaurant=new Restaurant();
        restaurant.setPin("201301");
        restaurants.add(restaurant);



        CreatePinAdapterFromRestaurants pinAdapterFromRestaurants=new CreatePinAdapterFromRestaurants( restaurants, this);
        ArrayAdapter arrayAdapter=pinAdapterFromRestaurants.getAdapterFromPinCode();
        findAutoTextView.setAdapter(arrayAdapter);

        findAutoTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HomeActivity.this,restaurants.get(i).getPin(), Toast.LENGTH_LONG).show();
            }
        });

        findAutoTextView.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
                HomeActivity.super.onTouchEvent(motionEvent);
               /* final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;*/
                final int DRAWABLE_RIGHT = 2;
//                final int DRAWABLE_BOTTOM = 3;

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(motionEvent.getRawX() >= (findAutoTextView.getRight() - findAutoTextView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        //Toast.makeText(HomeActivity.this, findAutoTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                        Bundle bundle=new Bundle();
                        bundle.putString("pincode", findAutoTextView.getText().toString());
                        startNavigationActivity(bundle);
                        return true;
                    }
                }
                return false;
    }


    private void startNavigationActivity(Bundle bundle) {
        Intent intent=new Intent(this, NavigationActivity.class);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        unregisterNetworkReceiver(this, receiver);
        super.onDestroy();
    }
}