package android.com.myfirstkeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodSubtype;

public class MyFirstInputMethodService extends InputMethodService {

    private static final String TAG = "MyFirstIMS";
    private static final int KEYBOARD_TOTAL_HEIGHT_DP = 230;

    private KeyboardView mKeyboardView;
    private Keyboard mKeyboard;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        final DisplayMetrics dm = getResources().getDisplayMetrics();
        final int keyboardWidth = dm.widthPixels;
        final int keyboardHeight = (int) (dm.density * KEYBOARD_TOTAL_HEIGHT_DP + 0.5f);
        final int mode = 0;

        mKeyboard = new Keyboard(this, R.xml.my_first_english_keyboard, mode, keyboardWidth, keyboardHeight);
    }

    @Override
    public View onCreateInputView() {
        Log.d(TAG, "onCreateInputView");

        final View view = getLayoutInflater().inflate(R.layout.layout_keyboard, null);
        mKeyboardView = view.findViewById(R.id.keyboardView);
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView.setOnKeyboardActionListener(mKeyboardAction);

        return view;
    }

    @Override
    public View onCreateCandidatesView() {
        Log.d(TAG, "onCreateCandidatesView");

        return super.onCreateCandidatesView();
    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);
        Log.d(TAG, "onStartInputView");
    }

    @Override
    protected void onCurrentInputMethodSubtypeChanged(InputMethodSubtype newSubtype) {
        super.onCurrentInputMethodSubtypeChanged(newSubtype);
        Log.d(TAG, "onCurrentInputMethodSubtypeChanged");
    }

    @Override
    public void onFinishInput() {
        super.onFinishInput();
        Log.d(TAG, "onFinishInput");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private final KeyboardView.OnKeyboardActionListener mKeyboardAction = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            final InputConnection ic = getCurrentInputConnection();

            switch (primaryCode) {
                //删除
                case Keyboard.KEYCODE_DELETE:
                    ic.deleteSurroundingText(1, 0);
                    break;
                //完成
                case Keyboard.KEYCODE_DONE:
                    ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                    break;
                // 大小写切换
                case Keyboard.KEYCODE_SHIFT:
                    break;
                //一般文本
                default:
                    final char inputChar = (char) primaryCode;
                    ic.commitText(String.valueOf(inputChar), 1);
                    break;
            }

        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };
}
