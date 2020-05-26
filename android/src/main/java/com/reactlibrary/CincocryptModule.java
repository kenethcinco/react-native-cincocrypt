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

    
    private char charEncrypt(char ch,int startAt,int endAt,int key){
        int mod = endAt - startAt + 1;
        return (char)((ch+key-startAt)%mod+startAt);
    }
    private char charDecrypt(char ch,int startAt,int endAt,int key){
        int mod = endAt - startAt + 1;
        return (char)((ch-key-endAt)%mod+endAt);
    }
   


    @ReactMethod
    public void encrypt(ReadableMap options, final Callback callback) {
        try{

            String plaintext = options.hasKey("plaintext") ? options.getString("plaintext") : "";
            String keyword = options.hasKey("keyword")? options.getString("keyword"):"";
            int startAt = options.hasKey("startAt")? options.getInt("startAt"):0;
            int endAt = options.hasKey("endAt")? options.getInt("endAt"):127;
            String f_cipherRes = "";
            
            //vigenere cipher algorithm
            for(int i=0,j=0;i<plaintext.length();i++,j++){

                if(j == keyword.length())
                    j=0;

                f_cipherRes  = f_cipherRes + charEncrypt(plaintext.charAt(i),startAt,endAt,keyword.charAt(j));
            }
            
            //enhanced caesar algorithm
            int keyword2 = ((plaintext.split(" "))[1]).length();
            for(int i = 0;i<f_cipherRes.length();i++){
                s_cipherRes = s_cipherRes + charEncrypt(f_cipherRes.charAt(i),startAt,endAt,keyword2);
            }s_cipherRes = s_cipherRes + keyword2;


        }catch(Exception e){
            callback.invoke("kulang ang option na imo gebutang"); 
        }
        callback.invoke(s_cipherRes);
        s_cipherRes = "";//resett
    }
    @ReactMethod
    public void decrypt(ReadableMap options, final Callback callback) {
        try{

            String cipherText = options.hasKey("ciphertext") ? options.getString("ciphertext") : "";
            String keyword2 = options.hasKey("keyword")? options.getString("keyword"):"";
            int startAt = options.hasKey("startAt")? options.getInt("startAt"):0;
            int endAt = options.hasKey("endAt")? options.getInt("endAt"):127;
            int keyword = Character.getNumericValue(cipherText.charAt(cipherText.length()-1));
            String f_plaintext = "";


            for(int i=0;i<cipherText.length()-1;i++) {
                f_plaintext = f_plaintext + charDecrypt(cipherText.charAt(i),startAt,endAt,keyword);
            }

            for(int i =0, j=0;i<f_plaintext.length();i++,j++){
            
                if(j == keyword2.length())
                    j=0;
                s_plainText = s_plainText + charDecrypt(f_plaintext.charAt(i),startAt,endAt,keyword2.charAt(j));
            }

          
        
        }catch(Exception e){            
              callback.invoke("kulang ang option na imo gebutang");  
        }

        callback.invoke(s_plainText);
        s_plainText = ""; //reset
    }
}
