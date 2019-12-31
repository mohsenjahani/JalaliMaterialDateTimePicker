# Persian Jalali Material DateTime Picker

With this library you can pick a date or time for Android, with support Gregorian and Jalali calendar in the same time.
this library is based on the main library (https://github.com/wdullaer/MaterialDateTimePicker) and only the persian jalali calendar and custom font added to it.

### Setup
Just add this line to your `build.gradle` dependencies :
```groovy
dependencies {
    implementation 'ir.scriptestan.jalalimaterialdatetimepicker:library:0.1.2'
}
```

### Screenshots
Date Picker | Time Picker
--- | ---
![Date Picker Gregorian](https://raw.githubusercontent.com/mohsenjahani/JalaliMaterialDateTimePicker/master/screenshots/Screenshot_1577735860.png) | ![Date Picker Persian Jalali](https://raw.githubusercontent.com/mohsenjahani/JalaliMaterialDateTimePicker/master/screenshots/Screenshot_1577735871.png)
![Time Picker Gregorian](https://raw.githubusercontent.com/mohsenjahani/JalaliMaterialDateTimePicker/master/screenshots/Screenshot_1577735887.png) | ![Time Picker Persian Jalali](https://raw.githubusercontent.com/mohsenjahani/JalaliMaterialDateTimePicker/master/screenshots/Screenshot_1577735881.png)

### Simple usage
```kotlin
class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var dpd: DatePickerDialog? = null

    // Change this
    var calendarType: DatePickerDialog.Type = DatePickerDialog.Type.GREGORIAN // or DatePickerDialog.Type.GREGORIAN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val now: Calendar? = when (calendarType) {
                DatePickerDialog.Type.GREGORIAN -> Calendar.getInstance()
                DatePickerDialog.Type.JALALI -> JalaliCalendar.getInstance()
            }

            /**
             * Date picker setup
             */
            if (dpd == null) {
                dpd = DatePickerDialog.newInstance(
                    calendarType,
                    this,
                    now!!.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
                )
            } else {
                dpd!!.calendarType = calendarType
                dpd!!.initialize(
                    this,
                    now!!.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
                )
            }

            /**
             * For setting font
             *
            when (dpd?.calendarType) {
                DatePickerDialog.Type.GREGORIAN -> dpd!!.setFont(null)
                DatePickerDialog.Type.JALALI -> dpd!!.setFont(Typeface.createFromAsset(assets, "IRANSansMobile(FaNum).ttf"))
            }
            */

            /**
             * Showing date picker
             */
            dpd!!.show(supportFragmentManager, "DatePickerDialog")
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Toast.makeText(this,
            "Picked date: $dayOfMonth/${monthOfYear+1}/$year", Toast.LENGTH_LONG).show()
    }
}
```
for more options see [sample folder](https://github.com/mohsenjahani/JalaliMaterialDateTimePicker/tree/master/sample) or main library [readme](https://github.com/wdullaer/MaterialDateTimePicker)
