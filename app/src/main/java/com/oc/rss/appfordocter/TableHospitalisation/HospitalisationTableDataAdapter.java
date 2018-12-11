package com.oc.rss.appfordocter.TableHospitalisation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Consultation;
import com.oc.rss.appfordocter.Model.Hospitalisation;

import java.text.NumberFormat;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;

/**
 * Created by Ghassen on 06/05/2017.
 */

public class HospitalisationTableDataAdapter extends LongPressAwareTableDataAdapter<Hospitalisation> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();

    public HospitalisationTableDataAdapter(Context context, List<Hospitalisation> data, final TableView<Hospitalisation> tableView) {
        super(context, data,tableView);
    }



    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Hospitalisation hospitalisation = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderDateEntree(hospitalisation);

                break;
            case 1: renderedView = renderDateSortie(hospitalisation);

                break;
            case 2:
                renderedView = renderEtablissement(hospitalisation);
                break;

        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        return null;
    }

    private View renderHospitalisationId(final Hospitalisation hospitalisation) {
        return renderString(String.valueOf(hospitalisation.GetHospitalisationId()));
    }


    private View renderDossierId(final Hospitalisation hospitalisation) {
        return renderString(String.valueOf(hospitalisation.GetDossierId()));
    }

    private View renderEtablissement(final Hospitalisation hospitalisation) {
        return renderString(String.valueOf(hospitalisation.GetEtablissement()));
    }


    private View renderDateEntree(final Hospitalisation hospitalisation) {
        return renderString(String.valueOf(hospitalisation.GetDateEntree().substring(0,10)));
    }

    private View renderDateSortie(final Hospitalisation hospitalisation) {
        return renderString(String.valueOf(hospitalisation.GetDateSortie().substring(0,10)));
    }





    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
}
