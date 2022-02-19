package com.touchizen.drawerwithbottomnavigation.io.responses;

import java.util.List;

public class ApiResponseError {


        private String type;
        private String title;
        private Integer status;
        private String path;
        private String message;
        private List<FieldError> fieldErrors = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }

        public void setFieldErrors(List<FieldError> fieldErrors) {
            this.fieldErrors = fieldErrors;
        }

    }



