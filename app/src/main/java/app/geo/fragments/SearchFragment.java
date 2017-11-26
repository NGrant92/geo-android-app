package app.geo.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import app.geo.adapters.CacheFilter;
import app.geo.fragments.CachesFragment;

import app.geo.R;

/**
 * Created by niall on 26/11/17.
 */

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener, TextWatcher {
  public SearchFragment() {}

  public static SearchFragment newInstance() {
    return new SearchFragment();
  }

  @Override
  public void onAttach(Context c) {
    super.onAttach(c);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.fragment_search, container, false);

    ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.cacheTypes, android.R.layout.simple_spinner_item);

    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    ListView listView = (ListView) view.findViewById(R.id.searchCacheList);
    setListView(listView);

    return view;
  }

  private void checkSelected(String selected)
  {
    if (selected != null) {
      CacheFilter cacheFilter = null;
      if (selected.equals("All Types")) {
        cacheFilter.setFilter("all");
      } else if (selected.equals("Favourites")) {
        cacheFilter.setFilter("favourites");
      }

      String filterText = ((EditText)getActivity().findViewById(R.id.searchCacheNameEditText)).getText().toString();

      if(filterText.length() > 0)
        cacheFilter.filter(filterText);
      else
        cacheFilter.filter("");
    }
  }

  public void setListView(ListView listview) {

    listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    listview.setMultiChoiceModeListener((AbsListView.MultiChoiceModeListener) getActivity());
    ListAdapter listAdapter = null;
    listview.setAdapter (listAdapter);
    listview.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
    listview.setEmptyView(getActivity().findViewById(R.id.empty_list_view));
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {

  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
