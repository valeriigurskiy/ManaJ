package com.manaj.client.dto;

public record GraphicCard(
        String vRam,
        String deviceId,
        String vendor
) {

    public static final class GraphicCardBuilder {
        private String vRam;
        private String deviceId;
        private String vendor;

        private GraphicCardBuilder() {
        }

        public static GraphicCardBuilder builder() {
            return new GraphicCardBuilder();
        }

        public GraphicCardBuilder vRam(String vRam) {
            this.vRam = vRam;
            return this;
        }

        public GraphicCardBuilder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public GraphicCardBuilder vendor(String vendor) {
            this.vendor = vendor;
            return this;
        }

        public GraphicCard build() {
            return new GraphicCard(vRam,
                    deviceId,
                    vendor);
        }
    }
}
