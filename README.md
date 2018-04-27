# zxinglib
封装的二维码条形码扫描,二维码生成，识别图片中二维码的类库


二维码在生活中非常的普遍，这个库基于最新的Google ZXing 3.3.2jar包来实现


使用方法:



1.在项目的project 目录下的build.gradle 中的allprojects加上 maven { url 'https://jitpack.io' }



allprojects {
    repositories {
        jcenter()
        maven { url 'https://maven.google.com' }
        maven { url 'https://jitpack.io' }
    }
}



2.在自己项目的module中的build.gradle目录下，加上 compile 'com.github.doctorji:zxinglib:V1.0'




public class MainActivity extends BaseActivity {
    private TextView resultTextView;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private CheckBox mCheckBox;

    @Override
    public void saveInstance(Bundle savedInstanceState) {

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void doBusiness(final Context mContext) {
        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        mCheckBox = (CheckBox) findViewById(R.id.logo);

        TextView tvscanBarCode = (TextView) this.findViewById(R.id.tv_scan_barcode);
        tvscanBarCode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                requestPermissions(mContext, new String[]{"android.permission.CAMERA", "android.permission.READ_CONTACTS", "android.permission.WRITE_EXTERNAL_STORAGE"}, new RequestPermissionCallBack() {
                    @Override
                    public void granted() {
                        Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
                        startActivityForResult(openCameraIntent, 0);
                    }

                    @Override
                    public void denied() {
                        Toast.makeText(mContext, "请同意以上授权请求", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        TextView generateQRCodeButton = (TextView) this.findViewById(R.id.tv_add_qrcode);
        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String contentString = qrStrEditText.getText().toString();
                if (!contentString.equals("")) {
                    //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350,
                            mCheckBox.isChecked() ?
                                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) :
                                    null);
                    qrImgImageView.setImageBitmap(qrCodeBitmap);
                } else {
                    Toast.makeText(MainActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            resultTextView.setText(scanResult);
        }
    }
}



主要是在调用类库里面的CaptureActivity.class 扫描条形码和二维码的功能


生成二维码 封装成了一个util EncodingUtil



具体的请参考实例Demo


实例demo


https://github.com/doctorji/ZXingDemo
