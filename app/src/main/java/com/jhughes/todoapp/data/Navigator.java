package com.jhughes.todoapp.data;

import android.os.Bundle;

public class Navigator {

    private final Class<?> startActivity;
    private final Bundle bundle;
    private final int requestCode;
    private final boolean finishActivity;
    private final String key;
    private final boolean dismiss;

    private Navigator(Builder builder) {
        startActivity = builder.startActivity;
        bundle = builder.bundle;
        requestCode = builder.requestCode;
        finishActivity = builder.finishActivity;
        key = builder.key;
        dismiss = builder.dismiss;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Class<?> getStartActivity() {
        return startActivity;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public boolean isFinishActivity() {
        return finishActivity;
    }

    public String getKey() {
        return key;
    }


    public static final class Builder {
        private Class<?> startActivity;
        private Bundle bundle;
        private int requestCode;
        private boolean finishActivity;
        private String key;
        private boolean dismiss;

        public Builder withActivity(Class<?> activity) {
            this.startActivity = activity;
            return this;
        }

        public Builder withBundle(Bundle bundle){
            this.bundle = bundle;
            return this;
        }

        public Builder withRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public Builder withFinish() {
            this.finishActivity = true;
            return this;
        }

        public Builder withKey(String key) {
            this.key = key;
            return this;
        }

        public Builder dismiss() {
            this.dismiss = true;
            return this;
        }

        public Navigator build() {
            return new Navigator(this);
        }
    }
}
