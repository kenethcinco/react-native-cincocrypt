package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

public class CincocryptModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private String s_cipherRes = "";
    private String s_plainText = "";
    public CincocryptModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Cincocrypt";
    }


    @ReactMethod
    public void encrypt(ReadableMap options, final Callback callback) {
        try{

            String plaintext = options.hasKey("plaintext") ? options.getString("plaintext") : "";
            String keyword = options.hasKey("keyword")? options.getString("keyword"):"";
            String f_cipherRes = "";
            
            //vigenere cipher algorithm
            for(int i=0,j=0;i<plaintext.length();i++,j++){

                if(plaintext.charAt(i)==' '){
                    i++; 
                    f_cipherRes  = f_cipherRes + ' ';
                }

                if(j == keyword.length())
                    j=0;

                if(!Character.isDigit(plaintext.charAt(i))){
                    if(Character.isUpperCase(plaintext.charAt(i))){

                        f_cipherRes  = f_cipherRes + (char)((plaintext.charAt(i)+keyword.charAt(j)-65)%26 + 65 );
                    }else{
                        f_cipherRes  = f_cipherRes + (char)((plaintext.charAt(i)+keyword.charAt(j)-97)%26 + 97 );
                    }
                }else{
                    f_cipherRes  = f_cipherRes + (char)((plaintext.charAt(i)+keyword.charAt(j)-48)%10 + 48);   
                }
            }
            
            String keyword2 = (plaintext.split(" "))[1];

            //enhanced caesar algorithm
            for(int i = 0;i<f_cipherRes.length();i++){
                if(f_cipherRes.charAt(i)==' '){
                    i++;
                    s_cipherRes = s_cipherRes + ' ';
                }

                if(!Character.isDigit(f_cipherRes.charAt(i))){
                    if(Character.isUpperCase(f_cipherRes.charAt(i))){
                        s_cipherRes = s_cipherRes + (char)((f_cipherRes.charAt(i) + (char) keyword2.length()-65)%26+65);
                    }else{
                        s_cipherRes = s_cipherRes + (char)((f_cipherRes.charAt(i) + (char) keyword2.length()-97)%26+97);
                    }
                }else{
                    s_cipherRes = s_cipherRes + (char)((f_cipherRes.charAt(i) + (char) keyword2.length()-48)%10+48);
                }

            }s_cipherRes = (s_cipherRes + keyword2.length());


        }catch(Exception e){
        }
        callback.invoke(s_cipherRes);
        s_cipherRes = "";//resett
    }
    @ReactMethod
    public void decrypt(ReadableMap options, final Callback callback) {
        try{

            String cipherText = options.hasKey("ciphertext") ? options.getString("ciphertext") : "";
            String keyword2 = options.hasKey("keyword")? options.getString("keyword"):"";
            int keyword = Character.getNumericValue(cipherText.charAt(cipherText.length()-1));
            String f_plaintext = "";


            for(int i=0;i<cipherText.length()-1;i++) {

                if(cipherText.charAt(i)==' '){
                    i++;
                    f_plaintext = f_plaintext + ' ';
                }
                if(!Character.isDigit(cipherText.charAt(i))){
                    if(Character.isUpperCase(cipherText.charAt(i))){
                        f_plaintext = f_plaintext + (char)((cipherText.charAt(i) - (char) keyword-90)%26+90);
                    }else{
                        f_plaintext = f_plaintext + (char)((cipherText.charAt(i) - (char) keyword-122)%26+122);
                    }
                }else{
                    f_plaintext = f_plaintext + (char)((cipherText.charAt(i) - (char) keyword-57)%10+57);
                }
            }

            for(int i =0, j=0;i<f_plaintext.length();i++,j++){
                if(f_plaintext.charAt(i)==' '){
                    i++;
                    s_plainText = s_plainText + ' ';
                }
                if(j == keyword2.length())
                    j=0;

                if(!Character.isDigit(f_plaintext.charAt(i))){
                    if(Character.isUpperCase(f_plaintext.charAt(i))){
                       s_plainText = s_plainText + (char) ((f_plaintext.charAt(i)-keyword2.charAt(j)-90)%26+90);
                    }else{
                       s_plainText = s_plainText + (char) ((f_plaintext.charAt(i)-keyword2.charAt(j)-122)%26+122);
                    }
                }else{
                    s_plainText = s_plainText + (char) ((f_plaintext.charAt(i)-keyword2.charAt(j)-57)%10+57);
                }
             
                
            }

          
        
        }catch(Exception e){            
              callback.invoke("kulang ang option na imo gebutang");  
        }

        callback.invoke(s_plainText);
        s_plainText = ""; //reset
    }
}
