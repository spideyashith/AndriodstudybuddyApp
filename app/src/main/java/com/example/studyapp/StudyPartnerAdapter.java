package com.example.studyapp;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudyPartnerAdapter extends RecyclerView.Adapter<StudyPartnerAdapter.PartnerViewHolder> {

    private final Context context;
    private final List<StudyPartner> partners;
    private final OnPartnerClickListener listener;

    // Interface to handle button clicks
    public interface OnPartnerClickListener {
        void onRequestClick(StudyPartner partner);
    }

    public StudyPartnerAdapter(Context context, List<StudyPartner> partners, OnPartnerClickListener listener) {
        this.context = context;
        this.partners = partners;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PartnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_study_partner, parent, false);
        return new PartnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartnerViewHolder holder, int position) {
        StudyPartner partner = partners.get(position);
        holder.tvName.setText(partner.name);
        holder.tvCourse.setText(partner.course);

        holder.btnRequest.setOnClickListener(v -> listener.onRequestClick(partner));
    }

    @Override
    public int getItemCount() {
        return partners.size();
    }

    static class PartnerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCourse;
        Button btnRequest;

        public PartnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCourse = itemView.findViewById(R.id.tvCourse);
            btnRequest = itemView.findViewById(R.id.btnRequest);
        }
    }
}
