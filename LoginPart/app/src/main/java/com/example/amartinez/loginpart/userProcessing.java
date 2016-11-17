package com.example.amartinez.loginpart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class userProcessing extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;
    String type;
    private String actionToPerform;

    userProcessing(Context ctx) {
        context = ctx;
    }


    public String doInBackground (String... params) {
        type = params[0];
        String register_url = "http://www.cs.loyola.edu/~amartine/insert.php";
        String check_login_url = "http://www.cs.loyola.edu/~amartine/checkUserLogin.php";

        if(type.equals("register"))
        {
            try {

                String username = params[1];
                String password = params[2];
                String firstName = params[3];
                String lastName = params[4];
                String confirmPassword = params[5];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("FirstName", "UTF-8")+"="+URLEncoder.encode(firstName, "UTF-8")+"&"
                        +URLEncoder.encode("LastName", "UTF-8")+"="+URLEncoder.encode(lastName, "UTF-8")+"&"
                        +URLEncoder.encode("Username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        +URLEncoder.encode("ConfirmPassword", "UTF-8")+"="+URLEncoder.encode(confirmPassword, "UTF-8")+"&"
                        +URLEncoder.encode("Password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream, "iso-8859-1")));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) !=null)
                {
                    result += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                setActionToPerform(result);
                System.out.println(result + " HERE");
                return result;
            }


            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }//register

        else if (type.equals("login"))
        {
            try {

                String username = params[1];
                String password = params[2];
                URL url = new URL(check_login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        +URLEncoder.encode("Password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream, "iso-8859-1")));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) !=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                setActionToPerform(result);
                return result;
            }


            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }//end login

        return null;
    }// end background


    public String fetchStatus() {

        System.out.println(actionToPerform);
        return actionToPerform;
    }

    @Override
    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(context).create();

    }

    @Override
    protected void onPostExecute(String result){
        actionToPerform = fetchStatus();
       if(type.equals("register")) {
           if(actionToPerform.equalsIgnoreCase("Successfully added")){
               alertDialog.setMessage("Registration successful");

               alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK" , new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {


                       context.startActivity(new Intent(context, MainActivity.class));

                   }
               });
               alertDialog.show();

           }
           else if(actionToPerform.equalsIgnoreCase("exists")){
               alertDialog.setMessage("This username is already in use, please select a different one.");
               alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       alertDialog.dismiss();
                   }
               });
               alertDialog.show();
           }

           else {
               alertDialog.setMessage("Passwords don't match.");
               alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       alertDialog.dismiss();
                   }
               });
               alertDialog.show();
           }


       }
        else if(type.equalsIgnoreCase("login")){
           if(actionToPerform.equalsIgnoreCase("Login Successful")){
               alertDialog.setMessage("Login Successful");
               alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       context.startActivity(new Intent(context, afterLoginHome.class));

                   }
               });
               alertDialog.show();
           }
           else{
               alertDialog.setMessage("Invalid username or password");
               alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       alertDialog.dismiss();
                   }
               });
               alertDialog.show();
           }

       }
    }

    public void setActionToPerform(String actionToPerform) {
        this.actionToPerform = actionToPerform;
    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }


}
