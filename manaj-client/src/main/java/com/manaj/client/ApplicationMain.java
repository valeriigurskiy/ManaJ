package com.manaj.client;

import com.manaj.client.service.Client;
import com.manaj.client.service.ClientProperties;

public class ApplicationMain {

    private static final String URL_REGEX = "^(http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";

    public static void main(String[] args) {
//        if (args.length != 0) {
//            int arg = 0;
//            Settings settings = Settings.INSTANCE;
//
//            String address = args[0];
//            if (address.matches(URL_REGEX)) {
//                boolean reachable = pingURL(address);
//
//                if (!reachable) {
//                    throw new UnsupportedOperationException(address + " unavailable");
//                }
//
//                settings.address(address);
//                arg++;
//            }
//
//            if (args.length > 1) {
//                int port = Integer.parseInt(args[arg]);
//
//                if (port < 0 || port > 65535) {
//                    throw new IllegalArgumentException("Port can't be less than 0 or greater than 65535");
//                }
//
//                settings.port(port);
//                arg++;
//            }
//
//            if (args.length > 2) {
//                String loggingParam = args[arg];
//                boolean logging = Boolean.parseBoolean(loggingParam);
//                settings.logging(logging);
//            }
//        }

        ClientProperties properties = new ClientProperties();
        properties.setIncludeEnvs(false);
        properties.setIncludeProcess(false);


        Client.createInstance(properties);
    }

//    private static boolean pingURL(String url) {
//        url = url.replaceFirst("^https", "http");
//
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setConnectTimeout(1000);
//            connection.setReadTimeout(1000);
//            connection.setRequestMethod("HEAD");
//            connection.disconnect();
//            return true;
//        } catch (IOException exception) {
//            return false;
//        }
//    }
}