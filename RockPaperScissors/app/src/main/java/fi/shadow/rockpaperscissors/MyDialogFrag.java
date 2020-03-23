package fi.shadow.rockpaperscissors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFrag extends DialogFragment {
    public interface MyDialog{
        void firstClick();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_frag_dialog, container);
        Button testButton = view.findViewById(R.id.myTestButton);
        testButton.setOnClickListener((v) -> {
            
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
