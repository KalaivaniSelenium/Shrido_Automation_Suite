package com.thinktimetechno.utils;

import java.util.ArrayList;
import java.util.List;

public class FailedApiTracker {

    public static class FailedAPI {
        public final String name;
        public final String statusCode;

        public FailedAPI(String name, String statusCode) {
            this.name = name;
            this.statusCode = statusCode;
        }
    }

    private static final List<FailedAPI> failedApiList = new ArrayList<>();

    public static void logFailure(String name, String statusCode) {
        failedApiList.add(new FailedAPI(name, statusCode));
    }

    public static List<FailedAPI> getFailedApis() {
        return failedApiList;
    }

    public static void clearFailures() {
        failedApiList.clear();
    }
}
