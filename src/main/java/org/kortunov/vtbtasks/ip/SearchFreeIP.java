package org.kortunov.vtbtasks.ip;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class SearchFreeIP {

    public static Set<InetAddress> getFreeIp(Set<InetAddress> allIp, Set<InetAddress> busyIp){
        final var freeIps = new HashSet<>(allIp);
        freeIps.removeAll(busyIp);
        return freeIps;
    }

}
