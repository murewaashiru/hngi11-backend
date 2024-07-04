package com.example.visitor.utils;

import com.google.code.geocoder.Geocoder;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.model.InsightsResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import jakarta.servlet.http.HttpServletRequest;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.*;
import java.net.InetAddress;
import java.security.InvalidKeyException;

public class HelperUtils {

    public static String getClientIp(HttpServletRequest request) {
        String clientIp = "";

        if (request != null) {
            clientIp = request.getHeader("X-FORWARDED-FOR");
        }

        if ((clientIp == null || clientIp.isEmpty()) && request != null) {
            clientIp = request.getRemoteAddr();
        }

        return clientIp;
    }



    public static String getClientCity(HttpServletRequest request) throws IOException, GeoIp2Exception, InvalidKeyException{
        String clientIp = getClientIp(request);
        File database = new File("src/main/resources/GeoLite2-City.mmdb");
        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        InetAddress ipAddress = InetAddress.getByName(clientIp);

        CityResponse response = reader.city(ipAddress);

        City city = response.getCity();

        return city.getName();
    }

}
