package saurabh.digipodium.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    private PowerStatusReceiver receiver;
    private ImageView imgPower;
    private ConstraintLayout wrapper;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgPower = view.findViewById(R.id.imgPower);
        wrapper = view.findViewById(R.id.wrapper);

    }


    @Override
    public void onResume() {
        super.onResume();
        //registering receiver
        IntentFilter filter1 = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        IntentFilter filter2 = new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
        receiver = new PowerStatusReceiver();
        getActivity().registerReceiver(receiver, filter1);
        getActivity().registerReceiver(receiver, filter2);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    public void updateUI(int state) {
        switch (state) {
            case 1:
                imgPower.setImageResource(R.drawable.ic_baseline_power_24);
                wrapper.setBackgroundColor(Color.argb(100, 100, 255, 100));
                break;
            case 0:
                imgPower.setImageResource(R.drawable.ic_baseline_power_off_24);
                wrapper.setBackgroundColor(Color.argb(100, 255, 100, 100));
                break;
        }
    }

    class PowerStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Intent.ACTION_POWER_CONNECTED:
                    updateUI(1);
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    updateUI(0);
                    break;
            }
        }
    }
}