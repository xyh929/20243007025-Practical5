package com.example.practicalfive;

/** Shared validation for both activities; no Android dependencies. */
public final class TextProcessor {
    public String processMunicipality(String municipality) {
        if (municipality == null) return null;
        String input = municipality.trim();
        for (String supported : new String[]{"Beijing", "Shanghai", "Chongqing", "Tianjin"}) {
            if (supported.equalsIgnoreCase(input)) return supported;
        }
        return null;
    }
}
