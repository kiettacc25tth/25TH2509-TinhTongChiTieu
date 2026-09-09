package tiil.edu.a25th2509_tinhtongchitieu;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtSoTien, edtGhiChu;
    private Button btnThem;
    private TextView tvTongTien;
    private ListView lvChiTieu;

    private ArrayList<String> danhSachHienThi = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private double tongTien = 0;
    private DecimalFormat dinhDangTien = new DecimalFormat("#,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSoTien = findViewById(R.id.edtSoTien);
        edtGhiChu = findViewById(R.id.edtGhiChu);
        btnThem = findViewById(R.id.btnThem);
        tvTongTien = findViewById(R.id.tvTongTien);
        lvChiTieu = findViewById(R.id.lvChiTieu);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, danhSachHienThi);
        lvChiTieu.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themKhoanChi();
            }
        });
    }

    private void themKhoanChi() {
        String chuoiSoTien = edtSoTien.getText().toString().trim();
        String ghiChu = edtGhiChu.getText().toString().trim();

        if (chuoiSoTien.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số tiền", Toast.LENGTH_SHORT).show();
            return;
        }

        double soTien;
        try {
            soTien = Double.parseDouble(chuoiSoTien);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Số tiền không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ghiChu.isEmpty()) {
            ghiChu = "(không ghi chú)";
        }
        
        tongTien += soTien;

        String dong = dinhDangTien.format(soTien) + " đ - " + ghiChu;
        danhSachHienThi.add(0, dong);
        adapter.notifyDataSetChanged();

        tvTongTien.setText("Tổng chi tiêu: " + dinhDangTien.format(tongTien) + " đ");

        edtSoTien.setText("");
        edtGhiChu.setText("");
        edtSoTien.requestFocus();
    }
}