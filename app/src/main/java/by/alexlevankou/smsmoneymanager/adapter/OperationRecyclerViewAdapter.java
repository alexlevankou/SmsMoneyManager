package by.alexlevankou.smsmoneymanager.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import by.alexlevankou.smsmoneymanager.R;
import by.alexlevankou.smsmoneymanager.model.Category;
import by.alexlevankou.smsmoneymanager.model.Operation;
import by.alexlevankou.smsmoneymanager.view.OperationListFragment.OnListFragmentInteractionListener;


public class OperationRecyclerViewAdapter extends RecyclerView.Adapter<OperationRecyclerViewAdapter.ViewHolder> {

    private List<Operation> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final TypedArray mCategoryResources;

    public OperationRecyclerViewAdapter(Context context, List<Operation> items, OnListFragmentInteractionListener listener) {
        mCategoryResources = context.getResources().obtainTypedArray(R.array.category_array);
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
        holder.mItem = mValues.get(position);

        Category category = holder.mItem.getCategory();
        holder.mCategoryImage.setImageResource(mCategoryResources.getResourceId(category.getValue(), 0));

        String price = holder.mItem.getPrice().toString();
        holder.mPriceView.setText(price == null ? "" : price);

        String name = holder.mItem.getName();
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
        final ImageView mCategoryImage;
        final TextView mPriceView;
        final TextView mNameView;
        Operation mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mCategoryImage = view.findViewById(R.id.category);
            mPriceView = view.findViewById(R.id.price);
            mNameView = view.findViewById(R.id.operation_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
