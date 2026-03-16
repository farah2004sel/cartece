package com.mc.icmc.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacAddressUtil {

    /**
     * Récupère l’adresse IP réelle du client (même derrière un proxy).
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.contains(",")) {
            ip = ip.split(",")[0]; // si plusieurs IPs
        }

        //  Log console pour debug
        System.out.println("[DEBUG] IP détectée : " + ip);
        return ip;
    }

    /**
     * Récupère l’adresse MAC associée à une IP via la table ARP locale.
     */
    public static String getMacAddress(String ip) {
        String macAddress = null;
        String os = System.getProperty("os.name").toLowerCase();

        try {
            Process process;
            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("arp -a " + ip);
            } else {
                process = Runtime.getRuntime().exec("arp -n " + ip);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                Pattern macPattern = Pattern.compile("(([0-9A-Fa-f]{2}[:-]){5}[0-9A-Fa-f]{2})");

                while ((line = reader.readLine()) != null) {
                    Matcher matcher = macPattern.matcher(line);
                    if (matcher.find()) {
                        macAddress = matcher.group(1);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        macAddress = macAddress != null ? macAddress.toUpperCase() : "UNKNOWN";

        //  Log console pour debug
        System.out.println("[DEBUG] MAC trouvée pour IP " + ip + " : " + macAddress);

        return macAddress;    }

    /**
     * Combine les deux méthodes pour obtenir directement la MAC du client.
     */
    public static String getClientMac(HttpServletRequest request) {
        String ip = getClientIp(request);
        return getMacAddress(ip);
    }
}
