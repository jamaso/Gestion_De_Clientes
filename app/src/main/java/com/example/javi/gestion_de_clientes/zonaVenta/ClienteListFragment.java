package com.example.javi.gestion_de_clientes.zonaVenta;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.javi.gestion_de_clientes.R;
import com.example.javi.gestion_de_clientes.constantes.G;
import com.example.javi.gestion_de_clientes.proveedor.ClientesProveedor;
import com.example.javi.gestion_de_clientes.proveedor.Contrato;

import static com.example.javi.gestion_de_clientes.R.id.textview_cliente_list_item_telefono;

public class ClienteListFragment extends ListFragment
		implements LoaderManager.LoaderCallbacks<Cursor> {
	
	//private static final String LOGTAG = "Tiburcio - ZonaVentaListFragment";

	CicloCursorAdapter mAdapter;
	LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    int idZona;
    int idCliente;
	ActionMode mActionMode;
	View viewSeleccionado;


	public static ClienteListFragment newInstance(int idZona) {
		ClienteListFragment f = new ClienteListFragment();
		Bundle bdl = new Bundle();
		bdl.putInt("IDZONA" , idZona);
		f.setArguments(bdl);
		return f;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		/*Intent intent = new Intent(getActivity(), ClientesActivity.class);
		/*intent.putExtra("ID", (Integer) v.getTag());*/
		/*startActivity(intent);*/
	}

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("tiburcio", "onCreate de ZonaVentaListFragment");

		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


		MenuItem menuItem = menu.add(Menu.NONE, G.INSERTAR,Menu.NONE,"Insertar");
		menuItem.setIcon(R.drawable.ic_nuevo_registro);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case G.INSERTAR:
				Intent intent = new Intent(getActivity(), ClienteDetalleActivity.class);
				intent.putExtra("idZona", idZona);
				startActivity(intent);

				break;

		}


		return super.onOptionsItemSelected(item);

	}

	/**
	 * The Fragment's UI is just a simple text view showing its
	 * instance number.
	 */


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		//Log.i(LOGTAG, "onCreateView");
		View v = inflater.inflate(R.layout.fragment_cliente_list, container, false);
		idZona =getArguments().getInt("IDZONA");

		mAdapter = new CicloCursorAdapter(getActivity());
		setListAdapter(mAdapter);

		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//Log.i(LOGTAG, "onActivityCreated");

		mCallbacks = this;

		getLoaderManager().initLoader(0, null, mCallbacks);

		getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

				if(mActionMode != null){
					return false;
				}
				mActionMode = getActivity().startActionMode(mActionModeCallback);
				view.setSelected(true);
				viewSeleccionado = view;
				return true;
			}
		});


	}
	ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.menu_contextual, menu);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()){

				case  R.id.menu_borrar:
					int clienteId = (Integer) viewSeleccionado.getTag();
					ClientesProveedor.delete(getActivity().getContentResolver(),clienteId);
					mActionMode.finish();
					break;

				case 	R.id.menu_editar:


					break;
			}

			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			mActionMode=null;

		}
	};

	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// This is called when a new Loader needs to be created.  This
		// sample only has one Loader, so we don't care about the ID.
		// First, pick the base URI to use depending on whether we are
		// currently filtering.
		String columns[] = new String[] {Contrato.Cliente._ID,
										  Contrato.Cliente.NOMBRE,
				                          Contrato.Cliente.APELLIDO,
				                          Contrato.Cliente.TELEFONO,
				                          Contrato.Cliente.ID_ZONA

										};

		Uri baseUri = Contrato.Cliente.CONTENT_URI;

		// Now create and return a CursorLoader that will take care of
		// creating a Cursor for the data being displayed.

		String selection = Contrato.Cliente.ID_ZONA+ " = " +idZona ;

		return new CursorLoader(getActivity(), baseUri,
				columns, selection, null, null);
	}

	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// Swap the new cursor in.  (The framework will take care of closing the
		// old cursor once we return.)

		Uri laUriBase = Uri.parse("content://"+Contrato.AUTHORITY+"/Cliente");
		data.setNotificationUri(getActivity().getContentResolver(), laUriBase);
		
		mAdapter.swapCursor(data);
	}

	public void onLoaderReset(Loader<Cursor> loader) {
		// This is called when the last Cursor provided to onLoadFinished()
		// above is about to be closed.  We need to make sure we are no
		// longer using it.
		mAdapter.swapCursor(null);
	}

	public class CicloCursorAdapter extends CursorAdapter {
		public CicloCursorAdapter(Context context) {
			super(context, null, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			int ID = cursor.getInt(cursor.getColumnIndex(Contrato.Cliente._ID));
			String nombre = cursor.getString(cursor.getColumnIndex(Contrato.Cliente.NOMBRE));
			String apellido = cursor.getString(cursor.getColumnIndex(Contrato.Cliente.APELLIDO));
			String telefono = cursor.getString(cursor.getColumnIndex(Contrato.Cliente.TELEFONO));

/*
			TextView textViewId = (TextView) view.findViewById(R.id.textView_Id_Clliente) ;
			textViewId.setText(ID);
	*/
			TextView textviewNombre = (TextView) view.findViewById(R.id.textview_cliente_list_item_nombre);
			textviewNombre.setText(nombre);

			TextView textviewApellido = (TextView) view.findViewById(R.id.textview_cliente_list_item_apellido);
			textviewApellido.setText(apellido);

			TextView textviewTelefono = (TextView) view.findViewById(textview_cliente_list_item_telefono);
			textviewTelefono.setText(telefono);



			ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
			int color = generator.getColor(apellido); //Genera un color según el nombre
			TextDrawable drawable = TextDrawable.builder()
					.buildRound(apellido.substring(0,1), color);

			ImageView image = (ImageView) view.findViewById(R.id.image_view);
			image.setImageDrawable(drawable);

			view.setTag(ID);

		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.cliente_list_item, parent, false);
			bindView(v, context, cursor);
			return v;
		}
	}
}
