package bizverse.lab.healthylifestyle.utils

/*
* Created by ducnguyen on 31/05/2022.
*/
class Constants {
    companion object {
        // Default value
        const val TIME_DELAY_DEFAULT = 1500L
        const val LIMIT_PAGE = 50
        const val MINUTE_PER_SLEEP_DATA = 5
        const val H_PERCENT = 100
        const val MAX_BATCH = 450
        const val CODE_SUCCESS = 200
        const val CODE_REGISTRATION = 201

        val DEEP_SLEEP = 0..10
        val LIGHT_SLEEP = 11..40
        val REM_SLEEP = 41..300

        val POOR_QUALITY = 0..59
        val GENERAL_QUALITY = 60..69
        val GOOD_QUALITY = 70..84
        val EXCELLENT_QUALITY = 85..100

        const val LIMIT_WAKE_TIME = 20

        const val INTERVAL_SYNC = 5 // in minute
        const val MAX_DAY_SYNC = 7 // in day

        const val TOTAL_START_RECOMMEND = 5.5f
        const val TOTAL_END_RECOMMEND = 9.0f
        const val DEEP_START_RECOMMEND = 1.5f
        const val DEEP_END_RECOMMEND = 2.5f
        const val LIGHT_START_RECOMMEND = 2.5f
        const val LIGHT_END_RECOMMEND = 4.8f
        const val REM_START_RECOMMEND = 3.2f
        const val REM_END_RECOMMEND = 4.2f

        // Date pattern
        const val YYYY_MM_DD_HH_MM_SS = "yyyy.MM.dd HH:mm:ss"
        const val YYYY_MM_DD_HH_MM_SS_T_Z = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'"
        const val HH_MM_SS = "HH:mm:ss"
        const val HH_MM = "HH:mm"
        const val YYYY_MM_DD = "yyyy.MM.dd"
        const val MMM_DD_YYYY = "MMM dd, YYYY"
        const val MMM_YYYY = "MMM YYYY"
        const val H2D_M2D = "%02d:%02d"
        const val m_s = "%02d\'%02d\'\'"
        const val YYYY = "YYYY"
        const val DD_MM_YYYY = "DD/MM/YYYY"
        const val MMM_DD_YYYY_HH_MM_SS = "MMM dd, YYYY HH:mm:ss"
        const val DD_MM = "dd/MM"

        // permission
        const val REQUEST_ENABLED_BLUETOOTH = 111
        const val REQUEST_BLUETOOTH = 123
        const val REQUEST_LOCATION = 999
        const val REQUEST_CAMERA = 1000
        const val REQUEST_GALLERY = 1001

        // device filter
        const val SERVICE_DATA = "0000fff0-0000-1000-8000-00805f9b34fb"
        const val FILTER_MASK = "0000FFFF-0000-0000-0000-000000000000"

        // device services
        const val NOTIFY = "00002902-0000-1000-8000-00805f9b34fb"
        const val DATA_CHARACTERISTIC = "0000fff6-0000-1000-8000-00805f9b34fb"
        const val NOTIFY_CHARACTERISTIC = "0000fff7-0000-1000-8000-00805f9b34fb"

        // Characteristic Data key
        const val KEY_CHARACTERISTIC = "KEY_CHARACTERISTIC"

        // Key getData
        const val DATA_TYPE = "dataType"
        const val DATA = "dicData"
        const val END = "dataEnd"

        // get data mode
        const val MODE_ENABLE = 9999
        const val MODE_DISABLE = 1111

        //Validate Email & Pass
        const val REGEX_EMAIL =
            "^([_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]).{2})$"
        const val REGEX_PASS = "^(?=.*[0-9])(?=.*[a-z]).{6,32}$"
        const val REGEX_PHONE_NUMBER = "(84|0[3|5|7|8|9])+([0-9]{8})\\b"

        // Dynamic link firebase
        const val IS_RESET_PASS = "is_reset_pass"
        const val OOB_CODE = "oob_code"

        // Data Key
        const val BATTERY_LEVEL = "batteryLevel"
        const val DEVICE_VERSION = "deviceVersion"
        const val ARRAY_SLEEP_QUALITY = "arraySleepQuality"
        const val ARRAY_STEPS = "arraySteps"
        const val CALORIES = "calories"
        const val DISTANCE = "distance"
        const val SPORT_MODEL = "sportModel"
        const val DETAIL_MINTER_STEP = "detailMinterStep"
        const val ARRAY_DYNAMIC_HR = "arrayDynamicHR"
        const val BLOOD_OXYGEN = "Blood_oxygen"
        const val ONCE_HEART_VALUE = "onceHeartValue"
        const val EXERCISE_MINUTES = "exerciseMinutes"
        const val EXERCISE_TIME = "ExerciseTime"
        const val TEMP_DATA = "TempData"
        const val GOAL = "goal"
        const val STEP = "step"
        const val DATE = "date"
        const val HEART_RATE = "heartRate"
        const val HIGH_BP = "highBP"
        const val LOW_BP = "lowBP"
        const val HRV = "hrv"
        const val VASCULAR_AGING = "vascularAging"
        const val STRESS = "stress"
        const val SPORT_MODEL_SPEED = "sportModelSpeed"
        const val WORK_MODEL = "workModel"
        const val INTERVAL_TIME = "intervalTime"
        const val HEART_START_MINTER = "heartStartMinter"
        const val HEART_END_HOUR = "heartEndHour"
        const val HEART_END_MINTER = "heartEndMinter"
        const val HEART_START_HOUR = "heartStartHour"
        const val WEEK_VALUE = "weekValue"
        const val TIME_UNIT = "timeUnit"
        const val DISTANCE_UNIT = "distancUnit"
        const val WEATHER = "Weather"
        const val HANDLE_SIGN = "handleSign"
        const val DIAL_SWITCH = "DialSwitch"
        const val BASE_HEART_RATE = "baseHeartRate"
        const val STATUS_OF_THE_RAISED_HAND_ON_SCREEN = "Status_of_the_raised_hand_on_screen"
        const val IS_HORIZONTAL_SCREEN = "isHorizontalScreen"

        //Firebase
        const val URL_FIREBASE = "https://healthy-app-b06ec.firebaseapp.com"

        // Unit Weight Height
        const val VALUE_POUND = 2.205f
        const val VALUE_FEET = 30.48f
        const val VALUE_INCHES = 2.54
        const val VALUE_MILE = 0.621371192
        const val UNIT_DISTANCE_KM = "km"
        const val UNIT_DISTANCE_MILE = "mile"
        const val BMI_CONTENT =
            "The BMI index is the Body Mass Index (BMI), which is the value obtained by dividing the weight in kilograms by the square of the height in meters (BMI = weight (KG) / height (m) 2). A commonly used standard for measuring the fatness and health of the human body. BMI is a neutral and reliable indicator when we need to compare and analyze the health effects of a person's weight on people of different heights. \n" +
                    "BMI classification WHO Standard The risk of related diseases\n" +
                    "Underweight BMI<18.5 low (but increased risk of other diseases)\n" +
                    "Normal range 18.5<BMI<25 average\n" +
                    "Overweight BMI>25 increase\n" +
                    "Pre-obesity 25<BMI<30 increase\n" +
                    "I degree obesity 30<BMI<35 moderate increase\n" +
                    "II degree obesity 35<BMI<40 serious increase\n" +
                    "III degree obesity BMI240.0 very serious increase\n"

        // Location Manager
        const val SENDING_HEART_PACKAGE_WORKER_UNIQUE_NAME = "SENDING_HEART_PACKAGE_WORKER_UNIQUE_NAME"

        // Exercise
        const val MIN_TIME = 30 // second
        const val MIN_DISTANCE = 100 // meter
    }

    object Extra {
        // key
        const val KEY_MOVE_TO = "KEY_MOVE_TO"
        const val START_DATE = "START_DATE"
        const val DISTANCE = "SEND_DISTANCE"
        const val PACE = "SEND_PACE"
        const val RSSI = "SEND_RSSI"
        const val SPORT_MODE = "SPORT_MODE"

        // value
        const val RUNNING_FRAGMENT = "RUNNING_FRAGMENT"
        const val CYCLING_FRAGMENT = "CYCLING_FRAGMENT"
        const val FOOTBALL_FRAGMENT = "FOOTBALL_FRAGMENT"
        const val YOGA_FRAGMENT = "YOGA_FRAGMENT"
    }

    object Firebase {
        const val USERS = "users"
        const val DEVICES = "devices"
        const val CONFIGS = "configs"
        const val SYNC_CONFIG = "syncConfig"
        const val DATE = "date"
        const val DEFAULT_DOCUMENT = "DefaultDocument"
        const val EXERCISE_PHONE = "exercisePhones"
        const val APP_VERSION = "appVersion"
        const val SUMMARY = "summary"

        // DEVICE Field
        const val IS_PAIR = "isPair"
        const val NAME = "name"
        const val LATEST_CONNECTION = "latestConnection"
        const val VERSION = "version"
        const val LATEST_SYNC = "latestSync"

        // SLEEP DETAIL field
        const val GO_TO_SLEEP_TIME = "gotoSleepTime"

        // DEVICE collections
        const val DETAIL_SLEEP = "detailSleep"
        const val DETAIL_ACTIVITY = "detailActivity"
        const val DYNAMIC_HEART_RATE = "dynamicHeartRate"
        const val STATIC_HEART_RATE = "staticHeartRate"
        const val HRV = "hrv"
        const val SPO2 = "spo2"
        const val TEMPERATURE = "temperature"
        const val TOTAL_ACTIVITY = "totalActivity"
        const val EXERCISE = "exercise"

        //Heart rate
        const val DEFAULT_MHR = 220
        val ZONE_WARM_UP = 50..59
        val ZONE_FAT_BURN = 60..69
        val ZONE_AEROBIC = 70..79
        val ZONE_ANAEROBIC = 80..89
        val ZONE_HIGH = 90
    }
    object APIs{
        const val DF_TIMEZONE = 0.0f
        const val DF_SET_TIMEZONE = 3600000.0
        const val PER_PAGE = 10
        const val HMAC_HTT_DAILY_REWARD = "u7o7J@2Kp@Uo"
    }
}
