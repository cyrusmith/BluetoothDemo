package interosite.ru.bluetoothdemo;

import android.app.Activity;
import android.app.ListFragment;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyrusmith
 * All rights reserved
 * http://interosite.ru
 * info@interosite.ru
 */
public class DeviceListFragment extends ListFragment {

    public interface DeviceListFragmentCallback {
        void onSearchDevices();
    }

    private DeviceListFragmentCallback callback;

    public static DeviceListFragment newInstance() {
        return new DeviceListFragment();
    }

    private final List<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();

    private ArrayAdapter<BluetoothDevice> devicesAdapter;

    private final class ViewHolder {
        public final TextView titleText;

        private ViewHolder(TextView titleText) {
            this.titleText = titleText;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (DeviceListFragmentCallback) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        devicesAdapter = new ArrayAdapter<BluetoothDevice>(getActivity(), R.layout.devices_li, devices) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = null;
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.devices_li, null);
                    viewHolder = new ViewHolder((TextView) view.findViewById(R.id.device_title));
                    view.setTag(viewHolder);
                } else {
                    view = convertView;
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                final BluetoothDevice device = getItem(position);
                viewHolder.titleText.setText(device.getName());
                return view;
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.device_list, null);
        final Button button = (Button) view.findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSearchDevices();
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
