package fi.shadow.myfragmentstuff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ChooseSoundFragment extends Fragment {
    private ChosenSound callback;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_sound_fragment, container);
        ListView listView = view.findViewById(R.id.myList);
        ArrayList<SoundItem> myItems = new ArrayList<>();
        //myItems.add(new SoundItem(1, "First"));
        //myItems.add(new SoundItem(2, "Second"));
        //myItems.add(new SoundItem(3, "Third"));
        myItems.add(new SoundItem(R.raw.arrow_x, "Arrow"));
        myItems.add(new SoundItem(R.raw.bomb_x, "Bomb"));
        myItems.add(new SoundItem(R.raw.cheering, "Cheering"));
        ArrayAdapter<SoundItem> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_lay, myItems);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            //Log.d("OnItemClickListener", parent.getItemAtPosition(position).toString());
            callback.itemSelected(myItems.get(position));
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = (ChosenSound) getActivity();
    }
}
