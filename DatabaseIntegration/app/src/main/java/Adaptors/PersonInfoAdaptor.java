package Adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arbabkhan.databaseintegration.R;

import java.util.List;

import DTOs.StudentInfo;

/**
 * Created by ArbabKhan on 11/06/2018.
 */

public class PersonInfoAdaptor extends RecyclerView.Adapter<PersonInfoAdaptor.PersonInfoHolder> {

    List list_Source;
    Context context;

    public PersonInfoAdaptor(List infoList, Context referenceContext) {
        this.list_Source = infoList;
        this.context = referenceContext;
    }

    @Override
    public PersonInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View infoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_info_layout_cell, parent, false);
        return new PersonInfoHolder(infoView);
    }

    @Override
    public void onBindViewHolder(PersonInfoHolder holder, int position) {
        final StudentInfo studentInfo = (StudentInfo) this.list_Source.get(position);
        holder.txt_UserFirstName.setText(studentInfo.strUserName);
        holder.txt_UserSurName.setText(studentInfo.strUserSurname);
        holder.txt_MarksObtained.setText(studentInfo.strObtainedMarks);
    }

    @Override
    public int getItemCount() {
        return this.list_Source.size();
    }

    public class PersonInfoHolder extends RecyclerView.ViewHolder {
        TextView txt_UserFirstName;
        TextView txt_UserSurName;
        TextView txt_MarksObtained;

        public PersonInfoHolder(View itemView) {
            super(itemView);
            txt_UserFirstName = (TextView) itemView.findViewById(R.id.txt_FirstName);
            txt_UserSurName = (TextView) itemView.findViewById(R.id.txt_SurName);
            txt_MarksObtained = (TextView) itemView.findViewById(R.id.txt_ObtainedMarks);
        }
    }

}
