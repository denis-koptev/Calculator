package com.developer.denis.calculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.pm.ActivityInfo.*;

public class MainActivity extends AppCompatActivity {

    private boolean start, last_sign, eq_pressed, point_set;
    private TextView result_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);

        result_text = (TextView)findViewById(R.id.result_text);
        /*Flags*/
        last_sign = false;
        eq_pressed = false;
        start = true;
        point_set = false;

        Button bdel = (Button)findViewById(R.id.del_button);
        bdel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                bclrclick(v);
                return false;
            }
        });

        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
    }

    public void num_clicked(View view) {
        if (result_text.getText().length() >= 14) {
            return;
        }
        start = false;
        if (eq_pressed) {
            result_text.setText("");
        }
        eq_pressed = false;
        Button btn = (Button)view;
        String num = btn.getText().toString();
        result_text.setText(result_text.getText() + num);
        last_sign = false;
        eq_pressed = false;
    }

    public void bmultclick(View view) {
        if (result_text.getText().length() >= 14 || start) {
            return;
        }
        start = false;
        eq_pressed = false;
        if (!last_sign) {
            result_text.setText(result_text.getText() + "×");
        }
        last_sign = true;
        eq_pressed = false;
        point_set = false;
    }

    public void bdivclick(View view) {
        if (result_text.getText().length() >= 14 || start) {
            return;
        }
        start = false;
        eq_pressed = false;
        if (!last_sign) {
            result_text.setText(result_text.getText() + "/");
        }
        last_sign = true;
        eq_pressed = false;
        point_set = false;
    }

    public void bsubclick(View view) {
        if (result_text.getText().length() >= 14) {
            return;
        }
        eq_pressed = false;
        if (last_sign && !start) {
            if (result_text.getText().charAt(result_text.getText().length() - 1) != '-') {
                result_text.setText(result_text.getText() + "-");
            }
        } else {
            result_text.setText(result_text.getText() + "-");
        }
        last_sign = true;
        eq_pressed = false;
        start = false;
        point_set = false;
    }

    public void bplusclick(View view) {
        if (result_text.getText().length() >= 14 || start) {
            return;
        }
        start = false;
        eq_pressed = false;
        if (!last_sign) {
            result_text.setText(result_text.getText() + "+");
        }
        last_sign = true;
        eq_pressed = false;
        point_set = false;
    }

    public void beqclick(View view) throws ParserException {
        if (result_text.getText().length() == 0) {
            return;
        }
        Parser my_parser = new Parser();
        double result;
        try {
            result = my_parser.evaluate(result_text.getText().toString());
            String s_result = Double.toString(Math.round(result * 10000000.0) / 10000000.0);
            if (s_result.length() >= 14 && !s_result.contains("E")) {
                result_text.setText(s_result.substring(0, 14));
            } else if (result - Math.round(result) == 0) {
                s_result = Integer.toString((int)Math.round(result));
                result_text.setText(s_result);
            } else {
                result_text.setText(s_result);
            }
        } catch (ParserException e) {
            result_text.setText("Error");
        }
        eq_pressed = true;
        start = false;
        point_set = false;
    }

    public void bclrclick(View view) {
        last_sign = true;
        result_text.setText("");
        start = true;
        point_set = false;
    }

    public void bpointclick(View view) {
        if (!point_set && result_text.getText().length() != 0) {
            result_text.setText(result_text.getText() + ".");
            point_set = true;
        }
    }

    public void binfoclick(View view) {
        msgbox("Information", "Calculator\n1.2 Dark Ice\nDenis Koptev, SPbSTU, 2016");
    }

    private void msgbox(String title, String message) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setTitle(title);
        dlgAlert.setMessage(message);
        dlgAlert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // finish();
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public void bpowerclick(View view) {
        if (!start && !last_sign) {
            result_text.setText(result_text.getText() + "^");
            eq_pressed = false;
        }
    }

    public void rbracketclick(View view) {
        result_text.setText(result_text.getText() + ")");
    }

    public void lbracketclick(View view) {
        result_text.setText(result_text.getText() + "(");
    }

    public void delclick(View view) {
        if (result_text.getText().length() > 0) {
            result_text.setText(result_text.getText().toString().substring(0, result_text.getText().length() - 1));
        }
    }

}
