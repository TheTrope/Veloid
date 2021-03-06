package com.mti.veloid;

/**
 * Created by TheTrope on 22/05/2017.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Filter;
        import android.widget.Filterable;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class WSAdapter extends RecyclerView.Adapter<WSAdapter.ViewHolder> implements Filterable{
    private ArrayList<VelibStation> mArrayList;
    private ArrayList<VelibStation> mFilteredList;

    public WSAdapter(ArrayList<VelibStation> arrayList) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
        Stations.getInstance().setmArrayList(mArrayList);
    }

    @Override
    public WSAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_list_station, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WSAdapter.ViewHolder viewHolder, int i) {
        viewHolder.getTv_name().setText(mFilteredList.get(i).getFields().getName());
        if (mFilteredList.get(i).getFields().getStatus().equals("CLOSED"))
            viewHolder.getIv_status().setImageResource(android.R.drawable.presence_busy);
        else if (mFilteredList.get(i).getFields().getAvailable_bikes() == 0)
            viewHolder.getIv_status().setImageResource(android.R.drawable.presence_away);
        viewHolder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<VelibStation> filteredList = new ArrayList<>();

                    if (mArrayList != null) {
                        for (VelibStation velib : mArrayList) {
                            if (velib.getFields().getAddress().toLowerCase().contains(charString)) {
                                filteredList.add(velib);
                            }
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<VelibStation>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private ImageView iv_status;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            iv_status = (ImageView)view.findViewById(R.id.iv_status);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    VelibStation velib = mFilteredList.get(position);
                    int realPos = Stations.getInstance().getmArrayList().indexOf(velib);

                    Context context = v.getContext();
                    Intent intent = new Intent(context, StationTab.class);
                    intent.putExtra("position", realPos);

                    context.startActivity(intent);
                }
            });

        }

        public TextView getTv_name() {
            return tv_name;
        }

        public void setTv_name(TextView tv_name) {
            this.tv_name = tv_name;
        }

        public ImageView getIv_status() {
            return iv_status;
        }

        public void setIv_status(ImageView iv_status) {
            this.iv_status = iv_status;
        }

    }

}