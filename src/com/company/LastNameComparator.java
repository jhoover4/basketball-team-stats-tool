package com.company;

import java.util.Comparator;

public class LastNameComparator implements Comparator<String> {
    public int compare(String b1, String b2) {
        return b1.split(" ")[1].compareTo(b2.split(" ")[1]);
    }
}
