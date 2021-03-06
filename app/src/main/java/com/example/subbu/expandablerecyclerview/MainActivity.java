package com.example.subbu.expandablerecyclerview;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends LifecycleActivity {
    Button button;
    TextView textView;
    ScoreViewModel viewModel;
    int count=0;
    RecyclerView recyclerView;
    ScanAdapter scanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
       /* button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);*/
      //  viewModel= ViewModelProviders.of(this).get(ScanViewModel.class);
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=count+1;
                viewModel.setCount(viewModel.getCount()+1);
                settext(viewModel.getCount());
            }
        });*/
       /* settext(viewModel.getCount());*/
        ScanViewModel scanViewModel=ViewModelProviders.of(this).get(ScanViewModel.class);
//        Log.e("scan",""+scanViewModel.getScanobservsble().getValue().getResult());
        observable(scanViewModel);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        scanAdapter=new ScanAdapter(MainActivity.this,null);
        recyclerView.setAdapter(scanAdapter);
    }

    private void observable(ScanViewModel scanViewModel) {
        scanViewModel.getScanobservsble().observe(this, new Observer<Exampl>() {
            @Override
            public void onChanged(@Nullable Exampl example) {
             //   scanAdapter.updateData(example.getResult().get(0));
                scanAdapter=new ScanAdapter(MainActivity.this,example.getResult().get(0));
                recyclerView.setAdapter(scanAdapter);
               // recyclerView.setAdapter(scanAdapter);
                Log.e("scand0",""+example.getResult().get(0));
                for (int i=0;i<example.getResult().size();i++){
                    Log.e("scanafterchange",""+example.getResult().get(0).get(i).getScanId());

                }
            }
        });
    }

    private void settext(int i) {
        textView.setText(String.valueOf(i));
    }
}
