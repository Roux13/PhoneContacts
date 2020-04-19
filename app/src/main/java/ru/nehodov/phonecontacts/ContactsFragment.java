package ru.nehodov.phonecontacts;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private RecyclerView recycler;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;
    private List<String> contacts = new ArrayList<>();
    private String searchText;

    public ContactsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_layout, container, false);

        SearchView searchView = view.findViewById(R.id.search);
        searchView.setBackgroundColor(getContext().getResources().getColor(R.color.textColorWhite));
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchText = query;
                searchInDic(contacts);
                updateUI();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText = newText;
                searchInDic(contacts);
                updateUI();
                return true;
            }
        });
        searchView.setOnCloseListener(() -> {
            searchText = null;
            loadDic(contacts);
            updateUI();
            return true;
        });

        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);

        updateUI();
        loadDic(contacts);
        return view;
    }

    private void updateUI() {
        adapter = new ContactsAdapter(contacts);
        recycler.setAdapter(adapter);
    }

    private void loadDic(List<String> contacts) {
        contacts.clear();
        makeQuery(contacts, null);
        updateUI();
    }

    private void searchInDic(List<String> contacts) {
        if (TextUtils.isEmpty(searchText)) {
            loadDic(contacts);
        } else {
            contacts.clear();
            updateUI();
            makeQuery(
                    contacts,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                            + " like '%"
                            + searchText
                            + "%'"
            );
        }
    }

    private void makeQuery(List<String> contacts, String selection) {
        Cursor cursor = getContext().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                },
                selection, null, null
        );
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(name + " " + number);
            }
        } finally {
            cursor.close();
        }
    }

    private class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final List<String> contacts;

        public ContactsAdapter(List<String> contacts) {
            this.contacts = contacts;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(
                    getLayoutInflater()
                            .inflate(R.layout.contatc_item, parent, false)
            ) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView nameTv = holder.itemView.findViewById(R.id.name);
            nameTv.setText(contacts.get(position));
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }
    }

}
