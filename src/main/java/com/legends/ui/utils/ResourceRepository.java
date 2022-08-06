package com.legends.ui.utils;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Locale;

public class ResourceRepository {

    private static final Hashtable<String, BufferedImage> images;

    static {
        images = new Hashtable<>();

        loadApp();
        loadIcons();
        loadUser();
        loadLegends();
    }

    public static BufferedImage getImage(String key) {
        return images.get(key);
    }

    private static void loadApp() {
        images.put("app_background", ImageLoader.load("ui/backgrounds/base_panel.jpg"));
        images.put("control_bar", ImageLoader.load("ui/backgrounds/control_bar.jpg"));
        images.put("home_panel", ImageLoader.load("ui/backgrounds/home_panel.jfif"));
        images.put("profile_panel", ImageLoader.load("ui/backgrounds/profile_panel.jpg"));
    }

    private static void loadIcons() {
        images.put("search_button", ImageLoader.load("ui/icons/search_button.png"));
        images.put("home_button", ImageLoader.load("ui/icons/home_button.png"));
        images.put("connected_icon", ImageLoader.load("ui/icons/connected_icon.png"));
        images.put("disconnected_icon", ImageLoader.load("ui/icons/disconnected_icon.png"));
    }

    private static void loadUser() {
        images.put("default_profile_picture", ImageLoader.load("ui/user/default_profile_picture.png"));
    }

    private static void loadLegends() {
        images.put("BLOODHOUND", ImageLoader.load("ui/legends/Bloodhound.png"));
        images.put("GIBRALTAR", ImageLoader.load("ui/legends/Gibraltar.png"));
        images.put("LIFELINE", ImageLoader.load("ui/legends/Lifeline.png"));
        images.put("PATHFINDER", ImageLoader.load("ui/legends/Pathfinder.png"));
        images.put("WRAITH", ImageLoader.load("ui/legends/Wraith.png"));
        images.put("BANGALORE", ImageLoader.load("ui/legends/Bangalore.png"));
        images.put("CAUSTIC", ImageLoader.load("ui/legends/Caustic.png"));
        images.put("MIRAGE", ImageLoader.load("ui/legends/Mirage.png"));
        images.put("OCTANE", ImageLoader.load("ui/legends/Octane.png"));
        images.put("WATTSON", ImageLoader.load("ui/legends/Wattson.png"));
        images.put("CRYPTO", ImageLoader.load("ui/legends/Crypto.png"));
        images.put("REVENANT", ImageLoader.load("ui/legends/Revenant.png"));
        images.put("LOBA", ImageLoader.load("ui/legends/Loba.png"));
        images.put("RAMPART", ImageLoader.load("ui/legends/Rampart.png"));
        images.put("HORIZON", ImageLoader.load("ui/legends/Horizon.png"));
        images.put("FUSE", ImageLoader.load("ui/legends/Fuse.png"));
        images.put("VALKYRIE", ImageLoader.load("ui/legends/Valkyrie.png"));
        images.put("SEER", ImageLoader.load("ui/legends/Seer.png"));
    }

    public static boolean isLegendSupported(String legend) {
        switch (legend.toUpperCase()) {
            case "BLOODHOUND", "GIBRALTAR", "LIFELINE", "PATHFINDER", "WRAITH", "BANGALORE", "CAUSTIC", "MIRAGE",
                 "OCTANE", "WATTSON", "CRYPTO", "REVENANT", "LOBA", "RAMPART", "HORIZON", "FUSE", "VALKYRIE", "SEER":
                return true;
        }
        return false;
    }
}
