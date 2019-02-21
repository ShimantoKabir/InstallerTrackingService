package com.installertrackingws.installertrackingws;

public class AppInfo {

    // public static String serverIp = "52.37.3.109";
    public static String serverIp = "192.168.0.3";
    // public static String clientIp = "52.37.3.109";
    public static String clientIp = "192.168.0.3";
    public static String clientIpForWebSocket = "192.168.0.3";
    public static String serverPort = "3307";
    public static String clientPort = "3308";
    public static String name = "Installer tracking ws";

    public AppInfo() {}

    public static String getClientIpForWebSocket() {
        return clientIpForWebSocket;
    }

    public static void setClientIpForWebSocket(String clientIpForWebSocket) {
        AppInfo.clientIpForWebSocket = clientIpForWebSocket;
    }

    public static String getServerIp() {
        return serverIp;
    }

    public static void setServerIp(String serverIp) {
        AppInfo.serverIp = serverIp;
    }

    public static String getClientIp() {
        return clientIp;
    }

    public static void setClientIp(String clientIp) {
        AppInfo.clientIp = clientIp;
    }

    public static String getServerPort() {
        return serverPort;
    }

    public static void setServerPort(String serverPort) {
        AppInfo.serverPort = serverPort;
    }

    public static String getClientPort() {
        return clientPort;
    }

    public static void setClientPort(String clientPort) {
        AppInfo.clientPort = clientPort;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        AppInfo.name = name;
    }

}
