package com.alasitaappdroid.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.alasitaappdroid.R;
import com.alasitaappdroid.controller.adapter.SectorAdapter;
import com.alasitaappdroid.model.Association;
import com.alasitaappdroid.model.Carnival;
import com.alasitaappdroid.model.Product;
import com.alasitaappdroid.model.Sector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Arun on 01/18/2015.
 */
public class SearchFragment extends Fragment {
    private ListView mListResult;
    private AutoCompleteTextView mTextQuery;
    private ArrayList<Sector> mSectors;
    private TextView mTextResults;
    private Carnival mCarnival;

    private static final String[] TAGS = new String[]{
            "joyeria", "tejido", "billetito", "billetes", "cuero", "billete", "bromas", "comida", "cuadro", "flores", "fruta", "tejido", "yesos", "masitas", "masita", "capuchino", "cereales", "churros", "confites", "helados", "palomitas", "pipocas", "refresco", "mermelada", "tallado", "piedra", "planta", "plantas", "tallado en piedra", "miniaturas", "gallito", "alcancia", "canchitas", "futbolines", "tiro al blanco", "suerte", "pronostico", "periodico", "periodiquito", "aluminio", "madera", "cuero", "bisuteria", "biyuteria", "churros", "hojalateria", "lampara", "carrito", "cochecito", "camion", "camioncito", "llavero", "bordado", "flores", "flor", "marmol", "ceramica", "plateria", "cafe", "chocolate", "instrumentos", "instrumento", "joyeria", "oleo", "pintura", "pinturas", "porcelana", "taraceado", "utencillos", "yesos", "alcancia", "ropa", "tapete", "titere", "titeres",
            "loteria", "juegos", "zapato", "tatuaje", "tatuajes", "salchipapa", "hamburguesa", "registro civil", "ruleta", "arco", "raspadillo", "magia", "mandiles", "mandil", "libros", "libro", "fantasia", "flan", "suerte sin blanca", "argollas", "futbolines", "canchitas", "futbolin", "tiro alblanco", "te", "cafe", "mate", "billar", "comida", "api",
            "comida", "api", "pollo", "plato paceño", "sajta", "api", "buñuelo", "vivadera", "suerte", "juegos", "futbolines", "cuadros", "refrigerios", "suerte sin blanca", "madera", "bisuteria", "churros", "comida", "confite", "hojalata", "carrito", "cochecito", "camion", "camioncito", "billetito", "fantasia", "cuadro",
            "ceramica", "madera", "miniatura", "bisuteria", "churros", "comida", "confites", "butbolines", "hojalata", "ropa", "suerte", "tejido", "suertes", "algodon", "artesanias", "canchitas", "carrusel", "comida", "juegos", "foto", "refrigerio", "miniatura", "cuadros", "anticuchos", "artesanias", "futbolines",
            "orfebreria", "orfebre", "tejido", "tejidos", "miniatura", "miniaturas", "fantasia", "juegos", "billetito", "billetitos", "juegos", "juegos", "futbolin", "futbolines", "magia", "pipocas", "pipoca", "joyeria", "joya", "tejidos", "juegos de mesa", "juego de mesa",
            "plantas", "planta", "macetas", "maceta", "carruseles", "carrusel", "futbolin", "futbolines", "vivandera", "vivanderas", "cafe", "te", "mate", "apis", "api", "pasteles", "pasteles", "instrumento", "instrumentos"
    };

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        if (getView() == null) {
            v = inflater.inflate(R.layout.fragment_search, container, false);
        } else {
            v = getView();
        }
        try {
            loadSectors();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mListResult = (ListView) v.findViewById(R.id.list_results);
        ImageButton buttonSearch = (ImageButton) v.findViewById(R.id.button_fragment_go);
        mTextQuery = (AutoCompleteTextView) v.findViewById(R.id.text_fragment_search);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, TAGS);
        mTextQuery.setAdapter(adapter);
        mTextResults = (TextView) v.findViewById(R.id.text_fragment_query);
        mTextResults.setVisibility(View.INVISIBLE);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mTextQuery.getText() + "";
                searchSectors(query);
                if (!mSectors.isEmpty()) {
                    SectorAdapter sectorAdapter = new SectorAdapter(getActivity(), mSectors);
                    mListResult.setAdapter(sectorAdapter);
                    mTextResults.setVisibility(View.VISIBLE);
                    mTextResults.setVisibility(View.VISIBLE);
                    mTextResults.setText("Resultados:");
                } else {
                    mTextResults.setText("No se encontro el producto");
                }
            }
        });
        mListResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SectorFragment sectorFragment = new SectorFragment();
                sectorFragment.setCurrentSector((Sector) parent.getItemAtPosition(position));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, sectorFragment).commit();
            }
        });
        mTextQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String query = mTextQuery.getText() + "";
                searchSectors(query);
                if (!mSectors.isEmpty()) {
                    SectorAdapter sectorAdapter = new SectorAdapter(getActivity(), mSectors);
                    mListResult.setAdapter(sectorAdapter);
                    mTextResults.setVisibility(View.VISIBLE);
                    mTextResults.setText("Resultados:");
                } else {
                    mTextResults.setText("No se encontro el producto");
                }
                return false;
            }
        });
        return v;

    }

    private void searchSectors(String query) {
        mSectors = new ArrayList<>();
        for (Sector sector : mCarnival.getCarnivalSectors()) {
            for (String tag : sector.getSectorTags()) {
                if (tag.equals(query)) {
                    mSectors.add(sector);
                    break;
                }
            }
        }
    }

    String loadJSON() {
        String json;
        try {
            InputStream inputStream = getActivity().getAssets().open("carnival.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer);
        } catch (Exception e) {
            Log.d("Json Error", e.getMessage());
            return null;
        }
        return json;
    }


    void loadSectors() throws JSONException {
        String json = loadJSON();
        mCarnival = new Carnival();

        JSONObject jsonObject = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));

        JSONArray array = jsonObject.getJSONArray("CarnivalSectors");
        ArrayList<Sector> SectorList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Sector sector = new Sector();
            JSONObject jsector = array.getJSONObject(i);
            String SectorName = jsector.getString("SectorName");
            String SectorKey = jsector.getString("SectorKey");
            String SectorDescription = jsector.getString("SectorDescription");
            String SectorMapImage = jsector.getString("SectorMapImage");
            JSONArray associationArray = jsector.getJSONArray("SectorAssociations");
            ArrayList<Association> AssociationList = new ArrayList<>();
            JSONArray InfoArray = jsector.getJSONArray("Tags");
            ArrayList<String> TagList = new ArrayList<>();
            for (int k = 0; k < InfoArray.length(); k++) {
                String Tag = InfoArray.getString(k);
                TagList.add(Tag);
            }
            for (int j = 0; j < associationArray.length(); j++) {
                Association association = new Association();
                JSONObject jAssociation = associationArray.getJSONObject(j);
                String AssociationName = jAssociation.getString("AssociationName");
                int AssociationKey = jAssociation.getInt("AssociationKey");
                String AssociationDescription = jAssociation.getString("AssociationDescription");
                int ExpoNumber = jAssociation.getInt("ExpoNumber");

                JSONArray ProductArray = jAssociation.getJSONArray("AssociationProducts");
                ArrayList<Product> ProductList = new ArrayList<>();
                for (int k = 0; k < ProductArray.length(); k++) {
                    Product product = new Product();
                    JSONObject jProduct = ProductArray.getJSONObject(k);
                    String ProductName = jProduct.getString("ProductName");
                    String ProductImage = jProduct.getString("ProductImage");

                    product.setProductName(ProductName);
                    product.setProductImage(ProductImage);
                    ProductList.add(product);
                }

                association.setAssociationName(AssociationName);
                association.setAssociationKey(AssociationKey);
                association.setAssociationDescription(AssociationDescription);
                association.setAssociationExpoNumber(ExpoNumber);
                association.setAssociationProducts(ProductList);
                AssociationList.add(association);
            }
            sector.setSectorTags(TagList);
            sector.setSectorAssociations(AssociationList);
            sector.setSectorName(SectorName);
            sector.setSectorKey(SectorKey);
            sector.setSectorDescription(SectorDescription);
            sector.setSectorMapImage(SectorMapImage);
            SectorList.add(i, sector);
        }
        mCarnival.setCarnivalName("CarnivalName");
        mCarnival.setCarnivalSectors(SectorList);
    }


}
