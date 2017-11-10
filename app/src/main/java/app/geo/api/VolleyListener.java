package app.geo.api;

import android.app.Fragment;

import java.util.List;


public interface VolleyListener {
    void setList(List list);
    void updateUI(Fragment fragment);
}
