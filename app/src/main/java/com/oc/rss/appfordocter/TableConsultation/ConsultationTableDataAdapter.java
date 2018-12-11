package com.oc.rss.appfordocter.TableConsultation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Consultation;
import com.oc.rss.appfordocter.Model.Medicament;

import java.text.NumberFormat;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;

/**
 * Created by Ghassen on 04/05/2017.
 */

public class ConsultationTableDataAdapter extends LongPressAwareTableDataAdapter<Consultation> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();

    public ConsultationTableDataAdapter(Context context, List<Consultation> data, final TableView<Consultation> tableView) {
        super(context, data,tableView);
    }



    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Consultation consultation = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderDate(consultation);

                break;
            case 1: renderedView = renderRaison(consultation);

                break;
            case 2:
                renderedView = renderEtablissement(consultation);
                break;

        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        return null;
    }

    private View renderConsultationId(final Consultation consultation) {
        return renderString(String.valueOf(consultation.GetCondultationId()));
    }


    private View renderConsultationDossierId(final Consultation consultation) {
        return renderString(String.valueOf(consultation.GetDossierId()));
    }

    private View renderEtablissement(final Consultation consultation) {
        return renderString(String.valueOf(consultation.GetEtabmissement()));
    }


    private View renderDate(final Consultation consultation) {
        return renderString(String.valueOf(consultation.GetDate().substring(0,10)));
    }
    private View renderRaison(final Consultation consultation) {
        return renderString(consultation.GetRaison());
    }





    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

}
