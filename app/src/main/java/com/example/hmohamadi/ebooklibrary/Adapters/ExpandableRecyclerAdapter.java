package com.example.hmohamadi.ebooklibrary.Adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hmohamadi.ebooklibrary.Models.ExpandableItemModel;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.example.hmohamadi.ebooklibrary.R;

import java.util.List;

public class ExpandableRecyclerAdapter  extends RecyclerView.Adapter<ExpandableRecyclerAdapter.ViewHolder> {

    private List<ExpandableItemModel> _data;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public ExpandableRecyclerAdapter(final List<ExpandableItemModel> data) {
        this._data = data;
        for (int i = 0; i < data.size(); i++) {
            expandState.append(i, false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_list_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ExpandableItemModel item = _data.get(position);
        holder.setIsRecyclable(false);
        holder.txtTitle.setText(item.getTitle());
        holder.txtDescription.setText(item.getDescription());

        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

        holder.expandableLayout.setInRecyclerView(true);
        holder.expandableLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryLight));
        holder.expandableLayout.setInterpolator(item.getInterpolator());
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.buttonLayout, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.buttonLayout, 180f, 0f).start();
                expandState.put(position, false);
            }
        });

        holder.buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(holder.expandableLayout);
            }
        });
    }

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtDescription;
        public RelativeLayout buttonLayout;
        /**
         * You must use the ExpandableLinearLayout in the recycler view.
         * The ExpandableRelativeLayout doesn't work.
         */
        public ExpandableLinearLayout expandableLayout;
        public ConstraintLayout container;
        public ViewHolder(View v) {
            super(v);
            txtTitle = v.findViewById(R.id.txtTitle_expand);
            txtDescription = v.findViewById(R.id.txtDescription_expand);
            buttonLayout = v.findViewById(R.id.btnExpand);
            expandableLayout = v.findViewById(R.id.expand_Row);
            container = v.findViewById(R.id.expand_row_container);
        }
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }
}
