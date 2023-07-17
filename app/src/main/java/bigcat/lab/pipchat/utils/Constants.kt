package bizverse.lab.healthylifestyle.utils

class Constants {
    companion object {
        // Default value
        const val TIME_DELAY_DEFAULT = 1500

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

        //Validate Email & Pass
        const val REGEX_EMAIL =
            "^([_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]).{2})$"
        const val REGEX_PASS = "^(?=.*[0-9])(?=.*[a-z]).{6,32}$"
        const val REGEX_PHONE_NUMBER = "(84|0[3|5|7|8|9])+([0-9]{8})\\b"

        // Dynamic link firebase
        const val IS_RESET_PASS = "is_reset_pass"
        const val OOB_CODE = "oob_code"
        // Exercise
        const val MIN_TIME = 30 // second
        const val MIN_DISTANCE = 100 // meter
    }

    object Extra {
        // key
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
    }
    object APIs{
        const val DF_TIMEZONE = 0.0f
        const val DF_SET_TIMEZONE = 3600000.0
        const val PER_PAGE = 10
    }
}
