package com.oc.rss.appfordocter.TableMedicament;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Immunisation;
import com.oc.rss.appfordocter.Model.Medicament;

import java.text.NumberFormat;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;

/**
 * Created by Ghassen on 03/05/2017.
 */

public class MedicamentTableDataAdapter extends LongPressAwareTableDataAdapter<Medicament> {
    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();

    public MedicamentTableDataAdapter(Context context, List<Medicament> data, final TableView<Medicament> tableView) {
        super(context, data,tableView);
    }



    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Medicament medicament = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderNomMedicament(medicament);

                break;
            case 1: renderedView = renderOrdonnance(medicament);

                break;
            case 2:
                renderedView = renderMedicamentDate(medicament);
                break;
            case 3:
                renderedView = renderOrdonnance(medicament);
                break;
        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        return null;
    }


    private View renderMedicamentDossierId(final Medicament medicament) {
        return renderString(String.valueOf(medicament.GetDossierId()));
    }

    private View renderMedicamentTId(final Medicament medicament) {
        return renderString(String.valueOf(medicament.GetMedicamentId()));
    }


    private View renderMedicamentDate(final Medicament medicament) {
        return renderString(String.valueOf(medicament.GetDate().substring(0,10)));
    }
    private View renderNomMedicament(final Medicament medicament) {
        return renderString(medicament.GetNomMedicament());
    }

    private View renderOrdonnance(final Medicament medicament) {
        return renderString(medicament.GetOrdonnance());
    }




    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }



}
