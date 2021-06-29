public class LaunchScreen extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LaunchScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 5*600);
    }
}
