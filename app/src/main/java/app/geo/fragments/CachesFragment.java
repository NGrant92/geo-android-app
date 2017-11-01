package app.geo.fragments;

import app.geo.R;
import app.geo.activities.CacheInfo;
import app.geo.activities.MyCache;
import app.geo.adapters.CacheFilter;
import app.geo.main.GeoApp;
import app.geo.models.Cache;
import app.geo.activities.Base;
import app.geo.activities.EditCache;
import app.geo.adapters.CacheListAdapter;
import app.geo.models.CacheStore;

import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

public class CachesFragment extends ListFragment implements View.OnClickListener, AbsListView.MultiChoiceModeListener {
  public Base activity;
  public CacheListAdapter listAdapter;
  public ListView listView;
  public CacheStore cacheStore;
  public CacheFilter cacheFilter;

  public CachesFragment(){}

  public static CachesFragment newInstance(){
    return new CachesFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    cacheStore = GeoApp.getInstance().cacheStore;
    setListAdapter(listAdapter);
  }

  @Override
  public void onResume(){
    super.onResume();

    listAdapter = new CacheListAdapter(getActivity(), cacheStore.caches);
    cacheFilter = new CacheFilter(cacheStore.caches, "all", listAdapter);

    if(getActivity() instanceof MyCache){
      cacheFilter.setFilter("mycache");
      //filtering data but without a prefix
      cacheFilter.filter(null);
      //updating adapter
      listAdapter.notifyDataSetChanged();
    }
    setListAdapter(listAdapter);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
    View view = super.onCreateView(inflater, parent, savedInstanceState);

    listView = (ListView) view.findViewById(android.R.id.list);
    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    listView.setMultiChoiceModeListener(this);

    return view;
  }

  @Override
  public void onAttach(Context context){
    super.onAttach(context);
    this.activity = (Base) context;
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onListItemClick(ListView list, View view, int position, long id) {
    Bundle activityInfo = new Bundle();
    activityInfo.putInt("cache_id", view.getId());

    if(getActivity() instanceof MyCache){
      Intent goEdit = new Intent(getActivity(), EditCache.class);
      goEdit.putExtras(activityInfo);
      getActivity().startActivity(goEdit);
    }
    else{
      Intent goInfo = new Intent(getActivity(), CacheInfo.class);
      goInfo.putExtras(activityInfo);
      getActivity().startActivity(goInfo);
    }
  }

  @Override
  public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {}

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {

    if(getActivity() instanceof EditCache){
      MenuInflater inflater = mode.getMenuInflater();
      inflater.inflate(R.menu.delete_list_context, menu);
      return true;
    }
    else{
      return false;
    }

  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

    switch (item.getItemId()) {
      case R.id.menu_item_delete_cache:
        deleteCaches(mode);
        return true;
      default:
        return false;
    }
  }

  private void deleteCaches(ActionMode mode) {
    for (int i = listAdapter.getCount() - 1; i >= 0; i--) {
      if (listView.isItemChecked(i)) {
        cacheStore.caches.remove(listAdapter.getItem(i));
        cacheStore.saveCaches();
      }
    }
    mode.finish();
    listAdapter.notifyDataSetChanged();
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {

  }

  @Override
  public void onClick(View v) {

  }
}
