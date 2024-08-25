package com.manaj.client.dto;


public record Process(
        int processID,
        String name,
        int threads,
        String CPU,
        String VSZ,
        String RSS
) {

    public static final class ProcessBuilder {
        private int processID;
        private String name;
        private int threads;
        private String CPU;
        private String VSZ;
        private String RSS;

        private ProcessBuilder() {
        }

        public static ProcessBuilder builder() {
            return new ProcessBuilder();
        }

        public ProcessBuilder processID(int processID) {
            this.processID = processID;
            return this;
        }

        public ProcessBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProcessBuilder threads(int threads) {
            this.threads = threads;
            return this;
        }

        public ProcessBuilder CPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public ProcessBuilder VSZ(String VSZ) {
            this.VSZ = VSZ;
            return this;
        }

        public ProcessBuilder RSS(String RSS) {
            this.RSS = RSS;
            return this;
        }

        public Process build() {
            return new Process(processID,
                    name,
                    threads,
                    CPU,
                    VSZ,
                    RSS);
        }
    }
}
