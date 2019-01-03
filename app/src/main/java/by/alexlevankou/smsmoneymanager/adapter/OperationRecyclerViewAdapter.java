package by.alexlevankou.smsmoneymanager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.alexlevankou.smsmoneymanager.R;
import by.alexlevankou.smsmoneymanager.model.Operation;
import by.alexlevankou.smsmoneymanager.view.OperationListFragment.OnListFragmentInteractionListener;


public class OperationRecyclerViewAdapter extends RecyclerView.Adapter<OperationRecyclerViewAdapter.ViewHolder> {

    private List<Operation> mValues;
    private final OnListFragmentInteractionListener mListener;

    public OperationRecyclerViewAdapter(List<Operation> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public void setItems(List<Operation> items)
    {
        mValues = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.operation_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int id = mValues.get(position).getId();
        String name = mValues.get(position).getName();

        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(id));
        holder.mNameView.setText(name == null ? "" : name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mNameView;
        Operation mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mNameView = view.findViewById(R.id.operation_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
