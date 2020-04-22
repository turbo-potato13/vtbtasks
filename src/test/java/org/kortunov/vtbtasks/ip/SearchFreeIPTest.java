package org.kortunov.vtbtasks.ip;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kortunov.vtbtasks.sum.SumAllNumbers;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class SearchFreeIPTest {

    @Test
    @SneakyThrows
    public void testGetFreeIp(){
        Set<InetAddress> ipAll = new HashSet<>();
        Set<InetAddress> ipBusy = new HashSet<>();

        ipAll.add(InetAddress.getByName("192.168.153.0"));
        ipAll.add(InetAddress.getByName("192.168.153.1"));
        ipAll.add(InetAddress.getByName("192.168.153.2"));
        ipAll.add(InetAddress.getByName("192.168.153.3"));

        ipBusy.add(InetAddress.getByName("192.168.153.1"));
        ipBusy.add(InetAddress.getByName("192.168.153.2"));
        ipBusy.add(InetAddress.getByName("192.168.153.3"));

        Assertions.assertEquals(SearchFreeIP.getFreeIp(ipAll,ipBusy).toString(), "[/192.168.153.0]");
    }
}
