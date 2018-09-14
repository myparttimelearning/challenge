package com.rumodigi.fanduelmobilechallenge.presentation.view.fragments;


import android.app.Fragment;
import android.widget.Toast;

import com.rumodigi.fanduelmobilechallenge.presentation.di.ContainsComponent;

public class BaseFragment extends Fragment {
    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((ContainsComponent<C>) getActivity()).getComponent());
    }
}
