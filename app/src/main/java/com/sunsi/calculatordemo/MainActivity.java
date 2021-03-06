package yesunss.calculatordemo;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends Activity implements View.OnClickListener{
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_plus;
    Button btn_minus;
    Button btn_multiply;
    Button btn_divide;
    Button btn_equal;
    Button btn_point;
    Button btn_clear;
    Button btn_del;
    EditText et_input;
    boolean clear_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_equal = (Button) findViewById(R.id.btn_equal);

        et_input = (EditText) findViewById(R.id.et_input);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();

        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if (clear_flag){
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button)v).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if (clear_flag){
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + " " + ((Button)v).getText() + " ");
                break;
            case R.id.btn_del:
                if (clear_flag){
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }else if(str!=null && !str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_clear:
                et_input.setText("");
                break;
            case R.id.btn_equal:
                getResult();
                break;

        }
    }

    private void getResult(){
        String str1 = et_input.getText().toString();
        double result = 0;
        if (clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;
        if(str1==null || str1.equals("")){
            return;
        }
        if(!str1.contains(" ")){
            return;
        } else{
            String strBeforeOperator = str1.substring(0, str1.indexOf(" "));
            String strOperator = str1.substring(str1.indexOf(" ")+1,str1.indexOf(" ")+2);
            String strAfterOperator = str1.substring(str1.indexOf(" ")+3);

            if(!strBeforeOperator.equals("") && !strAfterOperator.equals("")){
                double s1 = Double.parseDouble(strBeforeOperator);
                double s2 = Double.parseDouble(strAfterOperator);
                if(strOperator.equals("+")){
                    result = s1 + s2;
                } else if(strOperator.equals("-")){
                    result = s1 - s2;
                } else if(strOperator.equals("*")){
                    result = s1 * s2;
                } else if(strOperator.equals("/")){
                    if(s2==0){
                        et_input.setText("");
                    } else{
                        result = s1 / s2;
                    }
                }
                if (!strBeforeOperator.contains(".") && !strAfterOperator.contains(".") && !strOperator.equals("/")){
                    int r = (int) result;
                    et_input.setText(r+"");
                }else{
                    et_input.setText(result+"");
                }
            } else if(!strBeforeOperator.equals("") && strAfterOperator.equals("")){
                et_input.setText(str1);
            }else if(strBeforeOperator.equals("") && !strAfterOperator.equals("")){
                double s2 = Double.parseDouble(strAfterOperator);
                if(strOperator.equals("+")){
                    result = 0 + s2;
                } else if(strOperator.equals("-")){
                    result = 0 - s2;
                } else if(strOperator.equals("*")){
                    result = 0;
                } else if(strOperator.equals("/")){
                    if(s2==0){
                        et_input.setText("");
                    } else{
                        result = 0;
                    }
                }
                if (!strBeforeOperator.contains(".") && !strAfterOperator.contains(".") && !strOperator.equals("/")){
                    int r = (int) result;
                    et_input.setText(r+"");
                }else{
                    et_input.setText(result+"");
                }
            } else{
                et_input.setText("");
            }
        }
    }
}
