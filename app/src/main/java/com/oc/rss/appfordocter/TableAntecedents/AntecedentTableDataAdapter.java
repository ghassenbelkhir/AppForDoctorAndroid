package com.oc.rss.appfordocter.TableAntecedents;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Antecedents;
import com.oc.rss.appfordocter.Model.Consultation;

import java.text.NumberFormat;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;

/**
 * Created by Ghassen on 04/05/2017.
 */

public class AntecedentTableDataAdapter extends LongPressAwareTableDataAdapter<Antecedents> {
    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();

    public AntecedentTableDataAdapter(Context context, List<Antecedents> data, final TableView<Antecedents> tableView) {
        super(context, data,tableView);
    }



    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Antecedents antecedents = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderDiagnostic(antecedents);

                break;
            case 1: renderedView = renderEtat(antecedents);

                break;
            case 2:
                renderedView = renderDate(antecedents);
                break;

        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        return null;
    }

    private View renderDiagnostic(final Antecedents antecedents) {
        return renderString(String.valueOf(antecedents.GetDiagnostic()));
    }


    private View renderEtat(final Antecedents antecedents) {
        return renderString(String.valueOf(antecedents.GetDossierId()));
    }



    private View renderDate(final Antecedents antecedents) {
        return renderString(String.valueOf(antecedents.GetDate().substring(0,10)));
    }





    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
}
