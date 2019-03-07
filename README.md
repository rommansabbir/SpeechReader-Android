# SpeechReader-Android
## Documentation

### Installation
---
Step 1. Add the JitPack repository to your build file 

```gradle
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```gradle
	dependencies {
	        implementation 'com.github.rommansabbir:SpeechReader-Android:Tag'
	}
```

---

### Version available

| Releases        
| ------------- |
| v1.0          |


# Usages

### For Java: 

```java
public class MainActivity extends AppCompatActivity implements SpeechReader.SpeechReaderInterface {
    private SpeechReader speechReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Instantiate SpeechReader
         * Pass the context of parent activity and language for speech reader
         * @param context
         * @param language
         */
        speechReader = new SpeechReader(this, Locale.US);
        
        /**
         * This method is responsible for convert your text to speech
         * Pass your text through parameter
         * Pass your parameters, if not mandatory then pass "null"
         * Pass your pitch rate for speech reader
         * Pass your speed rate for speech reader
         * @param speech
         * @param params
         * @param pitchRate
         * @param speedRate
         */
        speechReader.reader("YOUR_TEXT_HERE", null, 1.0, 1.0);
    }

    @Override
    public void onSpeechReaderInitSuccess(String successMessage) {
        //TODO Implement your logic here
    }

    @Override
    public void onSpeechReaderFailure(String errorMessage) {
        //TODO Implement your logic here
    }
}
```

### Contact me
[Portfolio](https://www.rommansabbir.com/) | [LinkedIn](https://www.linkedin.com/in/rommansabbir/) | [Twitter](https://www.twitter.com/itzrommansabbir/) | [Facebook](https://www.facebook.com/itzrommansabbir/)
