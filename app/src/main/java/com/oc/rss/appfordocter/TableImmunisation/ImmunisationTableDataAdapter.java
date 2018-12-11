package com.oc.rss.appfordocter.TableImmunisation;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Immunisation;
import com.oc.rss.appfordocter.R;

import java.text.NumberFormat;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;

/**
 * Created by Ghassen on 01/05/2017.
 */

public class ImmunisationTableDataAdapter extends LongPressAwareTableDataAdapter<Immunisation> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();

    public ImmunisationTableDataAdapter(Context context, List<Immunisation> data,final TableView<Immunisation> tableView) {
        super(context, data,tableView);
    }



    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        Immunisation immunisation = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderImmunisationDossierId(immunisation);
                break;
            case 1:
                  renderedView = renderImmunisationType(immunisation);
                break;
            case 2:
                renderedView = renderImmunisationDate(immunisation);
                break;
            case 3:
                renderedView = renderImmunisationNombre(immunisation);
                break;
        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        return null;
    }
/*
    private View renderEditableCatName(final immunisation immunisation) {
        final EditText editText = new EditText(getContext());
        editText.setText(immunisation.GetDossierId());
        editText.setPadding(20, 10, 20, 10);
        editText.setTextSize(TEXT_SIZE);
        editText.setSingleLine();
        editText.addTextChangedListener(new CarNameUpdater(immunisation));
        return editText;
    }*/

    private View renderPrice(final Immunisation immunisation) {
        final String Type = immunisation.GetType();

        final TextView textView = new TextView(getContext());
        textView.setText(Type);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);

            textView.setTextColor(0xFF2E7D32);


        return textView;
    }

    /*
    private View renderPower(final immunisation immunisation, final ViewGroup parentView) {
        final View view = getLayoutInflater().inflate(R.layout.table_cell_power, parentView, false);
        final TextView kwView = (TextView) view.findViewById(R.id.kw_view);
        final TextView psView = (TextView) view.findViewById(R.id.ps_view);

        kwView.setText(format(Locale.ENGLISH, "%d %s", car.getKw(), getContext().getString(R.string.kw)));
        psView.setText(format(Locale.ENGLISH, "%d %s", car.getPs(), getContext().getString(R.string.ps)));

        return view;
    }*/

    private View renderImmunisationType(final Immunisation immunisation) {
        return renderString(immunisation.GetType());
    }

    private View renderImmunisationDossierId(final Immunisation immunisation) {
        return renderString(String.valueOf(immunisation.GetDossierId()));
    }

    private View renderImmunisationDate(final Immunisation immunisation) {
        return renderString(String.valueOf(immunisation.GetDate().substring(0,10)));
    }
    private View renderImmunisationNombre(final Immunisation immunisation) {
        return renderString(String.valueOf(immunisation.GetNombre()));
    }


   /* private View renderProducerLogo(final Car car, final ViewGroup parentView) {
        final View view = getLayoutInflater().inflate(R.layout.table_cell_image, parentView, false);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(car.getProducer().getLogo());
        return view;
    }*/

    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
/*
    private static class CarNameUpdater implements TextWatcher {

        private Car carToUpdate;

        public CarNameUpdater(Car carToUpdate) {
            this.carToUpdate = carToUpdate;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // no used
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // not used
        }

        @Override
        public void afterTextChanged(Editable s) {
            carToUpdate.setName(s.toString());
        }
    }
*/




}
