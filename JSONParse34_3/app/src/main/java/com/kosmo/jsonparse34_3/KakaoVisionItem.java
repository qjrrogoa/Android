package com.kosmo.jsonparse34_3;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "rid",
            "result"
    })

    class KakaoVisionItem {

        @JsonProperty("rid")
        public String rid;
        @JsonProperty("result")
        public Result result;

        public void setRid(String rid) {
            this.rid = rid;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public String getRid() {
            return rid;
        }

        public Result getResult() {
            return result;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({
                "width",
                "objects",
                "height"
        })

        public static class Result {
            @JsonProperty("width")
            public int width;
            @JsonProperty("objects")
            public List<Item> objects = null;
            @JsonProperty("height")
            public int height;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<Item> getObjects() {
                return objects;
            }

            public void setObjects(List<Item> objects) {
                this.objects = objects;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonPropertyOrder({
                    "y2",
                    "x2",
                    "score",
                    "y1",
                    "x1",
                    "class"
            })
            static class Item {
                @JsonProperty("y2")
                public float y2;
                @JsonProperty("x2")
                public float x2;
                @JsonProperty("score")
                public float score;
                @JsonProperty("y1")
                public float y1;
                @JsonProperty("x1")
                public float x1;
                @JsonProperty("class")
                public String productName;

                public float getY2() {
                    return y2;
                }

                public float getX2() {
                    return x2;
                }

                public float getScore() {
                    return score;
                }

                public float getY1() {
                    return y1;
                }

                public float getX1() {
                    return x1;
                }

                public void setY2(float y2) {
                    this.y2 = y2;
                }

                public void setX2(float x2) {
                    this.x2 = x2;
                }

                public void setScore(float score) {
                    this.score = score;
                }

                public void setY1(float y1) {
                    this.y1 = y1;
                }

                public void setX1(float x1) {
                    this.x1 = x1;
                }

                public void setProductName(String productName) {
                    this.productName = productName;
                }

                public String getProductName() {
                    return productName;
                }
            }////Item
        }////Result


}////KakaoVisionIte