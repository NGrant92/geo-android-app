package app.geo.fragments;

import app.geo.R;
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

  public CachesFragment(){}

  public static CachesFragment newInstance(){
    return new CachesFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    listAdapter = new CacheListAdapter(getActivity(), CacheStore.caches);
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
  public void onListItemClick(ListView list, View view, int position, long id)
  {
    Bundle activityInfo = new Bundle();
    activityInfo.putInt("cache_id", view.getId());

    Intent goEdit = new Intent(getActivity(), EditCache.class);
    goEdit.putExtras(activityInfo);
    getActivity().startActivity(goEdit);
  }

  @Override
  public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

  }

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    return false;
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    return false;
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {

  }

  @Override
  public void onClick(View v) {

  }
}