package com.rommansabbir.speechreader;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import java.util.HashMap;
import java.util.Locale;

public class SpeechReader {
    private static final String TAG = "SpeechReader";
    private Context context;
    private TextToSpeech textToSpeech;
    private static String errorMessage = "The Language is not supported!";
    private static String successMessage = "SpeechReader Initialization successful";
    private static String failureMessage = "SpeechReader Initialization failed!";
    private static String parsingError = "Error in converting Text to Speech!";
    private SpeechReaderInterface readerInterface;

    /**
     * Get the context from parent activity
     * Get language for reading speech
     * @param context
     * @param language
     */
    public SpeechReader(Context context, Locale language) {
        this.context = context;
        readerInterface = (SpeechReaderInterface) context;
        initialize(language);
    }
    /**
     * Initialize SpeechReader
     * @param language
     */
    private void initialize(final Locale language){
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(language);
                    /**
                     * Notify interface if any error occur
                     */
                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        readerInterface.onSpeechReaderFailure(errorMessage);
                    }
                    /**
                     * On init success, notify interface
                     */
                    readerInterface.onSpeechReaderInitSuccess(successMessage);
                } else {
                    /**
                     * On init failure, notify interface
                     */
                    readerInterface.onSpeechReaderFailure(failureMessage);
                }
            }
        });
    }
    /**
     * This method is responsible for reading your text
     * Pass the speech/text that you want to read a loud
     * Pass params, if not necessary pass "null"
     * Pass pitch rate for SpeechReader
     * Pass speed rate for SpeechReader
     * @param speech
     * @param params
     * @param pitchRate
     * @param speedRate
     */
    public void reader(String speech,HashMap<String, String> params , Double pitchRate, Double speedRate){
        textToSpeech.setPitch(Float.valueOf(String.valueOf(pitchRate)));
        textToSpeech.setSpeechRate(Float.valueOf(String.valueOf(speedRate)));
        int speechStatus = textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, params);
        /**
         * If error occur, notify interface
         */
        if (speechStatus == TextToSpeech.ERROR) {
            readerInterface.onSpeechReaderFailure(parsingError);
        }

    }
    /**
     * Destroy callback after it usages for fail safe
     */
    public void destroyCallback(){
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        textToSpeech = null;
        context = null;
    }

    public interface SpeechReaderInterface{
        void onSpeechReaderInitSuccess(String successMessage);
        void onSpeechReaderFailure(String errorMessage);
    }
}
