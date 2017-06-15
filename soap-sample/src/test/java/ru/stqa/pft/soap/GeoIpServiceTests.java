package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


/**
 * Created by Даниил on 14.06.2017.
 */

public class GeoIpServiceTests{
  @Test
  public void testMyIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("46.138.200.159");
    assertEquals(geoIP.getCountryCode(),"RUS");
  }
  @Test(enabled = true)
  public void testInvalidIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("46.138.200.xxx");
    assertEquals(geoIP.getCountryCode(),"RUS");
  }

}
