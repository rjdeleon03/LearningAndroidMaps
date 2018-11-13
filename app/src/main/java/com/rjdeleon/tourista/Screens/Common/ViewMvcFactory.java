package com.rjdeleon.tourista.Screens.Common;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rjdeleon.tourista.Screens.DestinationList.DestinationListViewMvc;
import com.rjdeleon.tourista.Screens.DestinationList.DestinationListViewMvcImpl;

public class ViewMvcFactory {

    private final LayoutInflater _layoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        _layoutInflater = layoutInflater;
    }

    public <T extends ViewMvc> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container) {

        ViewMvc viewMvc;

        if (mvcViewClass == DestinationListViewMvc.class) {
            viewMvc = new DestinationListViewMvcImpl(_layoutInflater, container);
        } else {
            throw new IllegalArgumentException("Unsupported MVC view class " + mvcViewClass);
        }

        //noinspection unchecked
        return (T) viewMvc;
    }
}
