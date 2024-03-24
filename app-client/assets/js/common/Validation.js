jQuery.validator.addMethod("notBlank", function (value, element) {
    return value.trim().length > 0;
}, "No space please and don't leave it empty")

jQuery.validator.addMethod("passwordRule", function (value, element) {
    return value.trim().length >= 8;
}, "Password must be 8 characters or more")

jQuery.validator.addMethod("studentCode", (v, e) => v.trim().length >= 6, "Student Code must be 6 characters or more")