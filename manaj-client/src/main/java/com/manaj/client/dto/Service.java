package com.manaj.client.dto;

public record Service(
        int processID,
        String name,
        String status
) {

    public static final class ServiceBuilder {
        private int processID;
        private String name;
        private String status;

        private ServiceBuilder() {
        }

        public static ServiceBuilder builder() {
            return new ServiceBuilder();
        }

        public ServiceBuilder processID(int processID) {
            this.processID = processID;
            return this;
        }

        public ServiceBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ServiceBuilder status(String status) {
            this.status = status;
            return this;
        }

        public Service build() {
            return new Service(processID,
                    name,
                    status);
        }
    }
}
