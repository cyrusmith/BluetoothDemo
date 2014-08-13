package interosite.ru.bluetoothdemo;

import android.bluetooth.BluetoothSocket;

/**
 * Created by cyrusmith
 * All rights reserved
 * http://interosite.ru
 * info@interosite.ru
 */
interface CommunicatorService {
    Communicator createCommunicatorThread(BluetoothSocket socket);
}
