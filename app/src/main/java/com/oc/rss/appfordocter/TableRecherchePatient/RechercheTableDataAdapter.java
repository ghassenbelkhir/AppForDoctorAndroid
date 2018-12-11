package com.oc.rss.appfordocter.TableRecherchePatient;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Consultation;
import com.oc.rss.appfordocter.Model.Patient;

import java.text.NumberFormat;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;

/**
 * Created by Ghassen on 08/05/2017.
 */

public class RechercheTableDataAdapter extends LongPressAwareTableDataAdapter<Patient> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();

    public RechercheTableDataAdapter(Context context, List<Patient> data, final TableView<Patient> tableView) {
        super(context, data,tableView);
    }



    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Patient patient = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderPatientId(patient);

                break;
            case 1: renderedView = renderPrenom(patient);

                break;
            case 2:
                renderedView = renderNom(patient);
                break;
            case 3:
                renderedView = renderDate(patient);
                break;


        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        return null;
    }

    private View renderPatientId(final Patient patient) {
        return renderString(String.valueOf(patient.GetPersonneId()));
    }


    private View renderNom(final Patient patient) {
        return renderString(String.valueOf(patient.GetNom()));
    }

    private View renderPrenom(final Patient patient) {
        return renderString(String.valueOf(patient.GetPrenom()));
    }


    private View renderDate(final Patient patient) {
        return renderString(String.valueOf(patient.GetDate().substring(0,10)));
    }





    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
}
