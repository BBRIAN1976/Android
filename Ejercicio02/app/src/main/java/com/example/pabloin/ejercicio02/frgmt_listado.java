package com.example.pabloin.ejercicio02;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabloin.ejercicio02.core.Producto;
import com.example.pabloin.ejercicio02.core.ProductoAdapter;
import com.example.pabloin.ejercicio02.core.ProductoFactory;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frgmt_listado.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frgmt_listado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgmt_listado extends Fragment {

    private ListView listaProductos;

    private Callback callback; // Es el context de esta clase, y también el MainActivity

    // Callback que obligo a implementar en MainActivity
    public interface Callback {
        public void onProductoSelected (Producto producto);
    }

    @Override
    public void onStart() {
        super.onStart();

        MainActivity mainActivity = (MainActivity) super.getActivity();

        // mainActivity.cambiarTextos();

        /*
        // Modificamos el texto (Fragmento I):
        TextView tv = (TextView)  mainActivity.findViewById(R.id.texto_hello);
        tv.setText("Hola Texto Fragmento 1 modificado");

        // Modificamos el texto (Fragmento II):
        TextView tv2 = (TextView)  mainActivity.findViewById(R.id.texto_detalle);
        tv2.setText("Hola Texto Fragmento 2 modificado");
        */


        // Recuperamos el adapter Java:
        final List<Producto> productos = ProductoFactory.getInstance();

        ProductoAdapter adapter = new ProductoAdapter(productos);


        // Recuperamos el ListView

        listaProductos = (ListView) mainActivity.findViewById(R.id.lista_productos);
        listaProductos.setAdapter(adapter);

        // Atach del evento OnClick
        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Producto productoSelected = (Producto) listaProductos.getItemAtPosition(position);

                callback.onProductoSelected(productoSelected);

//                Toast.makeText(parent.getContext(),
//                            "Producto Seleccionado: " + productoSelected.getNombre(),
//                             Toast.LENGTH_SHORT).show();


            }

        });

    }






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgmt_listado.
     */
    // TODO: Rename and change types and number of parameters
    public static frgmt_listado newInstance(String param1, String param2) {
        frgmt_listado fragment = new frgmt_listado();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public frgmt_listado() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frgmt_listado, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callback = (Callback) activity;

        /*
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
