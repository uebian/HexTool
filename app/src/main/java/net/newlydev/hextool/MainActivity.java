package net.newlydev.hextool;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.nio.*;
import java.util.*;
import java.lang.reflect.*;

public class MainActivity extends Activity 
{
	private EditText tv_input,tv_output;
	private Spinner sp_method;
	private ArrayList<Method> methods=new ArrayList<Method>();
	private ArrayList<String> methodstr=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		tv_input = findViewById(R.id.tv_input);
		tv_output = findViewById(R.id.tv_output);
		sp_method = findViewById(R.id.sp_method);
		for (Method m:Utils.class.getMethods())
		{
			if (Modifier.toString(m.getModifiers()).indexOf("static") != -1)
			{
				methods.add(m);
				methodstr.add(m.getName());
			}
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, methodstr);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_method.setAdapter(adapter);
    }
	public void action(View view)
	{
		try
		{
			Method method=methods.get(sp_method.getSelectedItemPosition());
			Object ret=null;
			Class cpt=method.getParameterTypes()[0];
			if (cpt.equals(byte[].class))
			{
				ret = method.invoke(null, Utils.hexstr2Bytes(tv_input.getText().toString()));
			}else if(cpt.equals(long.class))
			{
				ret = method.invoke(null, Long.parseLong(tv_input.getText().toString()));
			}
			else if (cpt.equals(int.class))
			{

				ret = method.invoke(null, Integer.parseInt(tv_input.getText().toString()));
			}
			else if (cpt.equals(short.class))
			{
				ret = method.invoke(null, Short.parseShort(tv_input.getText().toString()));
			}else if(cpt.equals(String.class))
			{
				ret = method.invoke(null, tv_input.getText().toString());
			}
			if (ret instanceof byte[])
			{
				tv_output.setText(Utils.bytes2Hexstr((byte[])ret));
			}
			else
			{
				tv_output.setText(ret.toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void change(View v)
	{
		String output=tv_output.getText().toString();
		tv_output.setText(tv_input.getText().toString());
		tv_input.setText(output);
	}
	//tv_output.setText(Utils.bytes2hexstr(Utils.int2bytes(Integer.parseInt(tv_input.getText().toString()))));
}
