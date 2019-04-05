package summer.fun.http.message;

public enum UploadError {
    UPLOAD_ERR_OK(0),
    UPLOAD_ERR_CONFIG_SIZE(1),
    UPLOAD_ERR_FORM_SIZE(2),
    UPLOAD_ERR_PARTIAL(3),
    UPLOAD_ERR_NO_FILE(4),
    UPLOAD_ERR_NO_TMP_DIR(6),
    UPLOAD_ERR_CANT_WRITE(7);

    private final int value;

    UploadError(int value) {
        this.value = value;
    }
}
